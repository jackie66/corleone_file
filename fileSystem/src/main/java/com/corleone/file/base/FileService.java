package com.corleone.file.base;

import com.corleone.file.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by wenteng on
 * 2017/9/10.
 */
public interface FileService {

    /**
     * 上传文件接口
     *
     * @param file    文件对象
     * @param request servlet请求对象
     * @return 文件信息对象
     */
    FileInfo uploadFile(MultipartFile file, HttpServletRequest request);

    /**
     * 文件都统一上传到文件系统，之后由文件系统进行迁移处理
     *
     * @param fileInfo 文件对象
     * @return 保存的文件信息对象
     */
    public Serializable saveFileRecord(FileInfo fileInfo) throws Exception;
}
