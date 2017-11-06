package com.corleone.file.utils;

import com.corleone.file.bean.BeanUtils;
import com.corleone.file.bean.properties.OSSProperties;
import com.corleone.file.model.FileLocation;
import com.corleone.file.model.FileType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * Created by jackie on
 * 2017/10/25.
 */
public class FileUtils {

    private static FileUtils single;

    private OSSProperties ossProperties;

    public OSSProperties getOssProperties() {
        if (ossProperties == null) {
            ossProperties = BeanUtils.getBean(OSSProperties.class);
        }
        return ossProperties;
    }

    public void setOssProperties(OSSProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    public static FileUtils getSingle() {
        if (single == null) {
            single = new FileUtils();
        }
        return single;
    }

    public static void setSingle(FileUtils single) {
        FileUtils.single = single;
    }

    //////////////////////////////////////////////////////////////////////////////

    /**
     * 获取文件的后缀信息,文件的格式内容
     */
    public static String getSuffix(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 确定文件的存放路径信息
     * 存放服务名称／文件内容类型／当前日期／uuid.suffix
     */
    public static String getFilePath(MultipartFile file, FileLocation location) {
        String fileName = StringUtils.generateUUID().toUpperCase();
        String currentDate = DateUtils.getStringDate(new Date());

        String contentType = file.getContentType();
        String suffix = getSuffix(file);

        return location + "/" + contentType + "/" + currentDate + "/" + fileName + "." + suffix;
    }

    /**
     * 根据HttpServletReqest获取系统的请求URI
     *
     * @param request servlet请求对象
     * @return 系统URI
     */
    public static String getResourcesUrl(HttpServletRequest request) {
        return "http://" + request.getHeader("host") + request.getContextPath() + "/resources/";
    }

    /**
     * 获取文件系统的路径下的文件对象
     *
     * @param filePath 文件路径信息
     * @return 文件对象
     */
    public static File getFile(String filePath) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 获取文件类型信息枚举
     *
     * @param file 文件对象
     * @return 文件类型枚举
     */
    public static FileType getFileType(MultipartFile file) {
        String contentType = file.getContentType();
        if (StringUtils.isEmpty(contentType)) {
            return null;
        }
        if (contentType.startsWith("image")) {
            return FileType.picture;
        } else if (contentType.startsWith("video")) {
            return FileType.video;
        }
        return FileType.document;
    }


    //    public static String getAbsoluteFilePath(String filePath) {
//        if (StringUtils.isEmpty(filePath)) {
//            return null;
//        }
//        String location = filePath.substring(0, filePath.indexOf("/"));
//        if (FileLocation.oss.toString().equals(location)) {
//            /**
//             * 阿里云的OSS服务访问路径
//             */
//            StringBuilder absoluteFilePath = new StringBuilder();
//            absoluteFilePath.append("http://")
//                    .append(getSingle().getOssProperties().getBucketName())
//                    .append(".")
//                    .append(getSingle().getOssProperties().getEndpoint())
//                    .append("/")
//                    .append(filePath);
//            return absoluteFilePath.toString();
//        } else if (FileLocation.fileSys.toString().equals(location)) {
//            /**
//             * 文件系统的访问路径
//             */
//            return getResourcesUrl() + filePath;
//        }
//        return null;
//    }

}
