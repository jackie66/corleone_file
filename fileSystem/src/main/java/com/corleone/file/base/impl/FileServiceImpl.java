package com.corleone.file.base.impl;

import com.corleone.file.base.FileDao;
import com.corleone.file.base.FileService;
import com.corleone.file.entity.FileInfo;
import com.corleone.file.model.FileLocation;
import com.corleone.file.model.FileType;
import com.corleone.file.utils.FileUtils;
import com.corleone.file.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

/**
 * Created by wenteng on
 * 2017/9/10.
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FileDao fileDao;

    public FileInfo uploadFile(MultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();
        if (StringUtils.isEmpty(contentType)) {
            return null;
        }
        String filePath;
        if (contentType.startsWith("image")) {
            filePath = uploadImage(file, request);
        } else {
            filePath = uploadDocument(file, request);
        }

        /**
         * 生成文件记录信息
         */
        FileInfo fileInfoRecord = new FileInfo();

        fileInfoRecord.setFilePath(filePath);
        fileInfoRecord.setAbsoluteFilePath(FileUtils.getResourcesUrl(request) + filePath);
        fileInfoRecord.setContentType(file.getContentType());

        FileType fileType= FileUtils.getFileType(file);
        fileInfoRecord.setType(fileType == null ? null : fileType.toString());
        fileInfoRecord.setSize(file.getSize());
        fileInfoRecord.setSuffixName(FileUtils.getSuffix(file));
        fileInfoRecord.setLocation(FileLocation.fileSys.toString());

        return fileInfoRecord;
    }

    public Serializable saveFileRecord(FileInfo fileInfo) throws Exception {

        return getFileDao().save(fileInfo);
    }

    ////////////////////////////////////////////////////////////////////////////////

    /**
     * 一般文档文件的保存函数
     * 直接将文件保存到本地硬盘
     *
     * @param file    文件对象
     * @param request servlet请求对象
     * @return 文件路径信息
     */
    private String uploadDocument(MultipartFile file, HttpServletRequest request) {
        String resourcesPath = request.getSession().getServletContext().getRealPath("/resources");
        String filePath = FileUtils.getFilePath(file, FileLocation.fileSys);
        File document = FileUtils.getFile(resourcesPath + "/" + filePath);
        try {
            file.transferTo(document);
        } catch (Exception e) {
            return null;
        }
        return filePath;
    }

    /**
     * 图片保存函数,图片保存png格式内容
     *
     * @param file    文件对象
     * @param request servlet请求对象
     * @return 文件路径信息
     */
    private String uploadImage(MultipartFile file, HttpServletRequest request) {
        String resourcesPath = request.getSession().getServletContext().getRealPath("/resources");
        String filePath = FileUtils.getFilePath(file, FileLocation.fileSys);
        File imageFile = FileUtils.getFile(resourcesPath + "/" + filePath);

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            ImageIO.write(image, FileUtils.getSuffix(file), imageFile);
        } catch (Exception e) {
            return null;
        }
        return filePath;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public FileDao getFileDao() {
        return fileDao;
    }

    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
    }

//    public String uploadPDF(MultipartFile file, HttpServletRequest request) {
//        String resourcesPath = request.getSession().getServletContext().getRealPath("/resources");
//        String pdfRelativePath = getRelativePath("book/document", "pdf");
//        String coverRelativePath = getRelativePath("book/cover", "png");
//
//        FileInfo pdfFile = getFile(resourcesPath, pdfRelativePath);
//        FileInfo coverFile = getFile(resourcesPath, coverRelativePath);
//
//        PDDocument pdfDocument = null;
//        try {
//            pdfFile.createNewFile();
//            pdfDocument = PDDocument.load(file.getInputStream());
//            PDFRenderer pdfRenderer = new PDFRenderer(pdfDocument);
//            BufferedImage coverImage = pdfRenderer.renderImage(0);
//
//            ImageIO.write(coverImage, "PNG", coverFile);
//            file.transferTo(pdfFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return JSONUtils.operateError("文件上传失败");
//        }
//
//        logger.info("上传的pdf封面图片路径:" + coverRelativePath);
//        logger.info("上传的pdf文件路径:" + pdfRelativePath);
//
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        PDDocumentInformation informationDocument = pdfDocument.getDocumentInformation();
//        resultMap.put("filePath", pdfRelativePath);
//        resultMap.put("absoluteFilePath", getResourcesUrl(request, pdfRelativePath));
//        resultMap.put("coverFilePath", coverRelativePath);
//        resultMap.put("absoluteCoverFilePath", getResourcesUrl(request, coverRelativePath));
//        resultMap.put("creator", informationDocument.getCreator());
//        resultMap.put("title", informationDocument.getTitle());
//        resultMap.put("author", informationDocument.getAuthor());
//        resultMap.put("keywords", informationDocument.getKeywords());
//        resultMap.put("producer", informationDocument.getProducer());
//        resultMap.put("subject", informationDocument.getSubject());
//        resultMap.put("trapped", informationDocument.getTrapped());
//        resultMap.put("numberPage", pdfDocument.getNumberOfPages());
//        resultMap.put("publishTime", informationDocument.getCreationDate());
//
//        try {
//            pdfDocument.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return JSONUtils.operateSuccess(resultMap);
//    }
}
