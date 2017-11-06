package com.corleone.file.controller;

import com.corleone.file.base.FileService;
import com.corleone.file.entity.FileInfo;
import com.corleone.file.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传接口
     *
     * @param file     上传的文件对象
     * @param request  servlet请求对象
     * @param response servlet响应对象
     * @return 上传信息
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "upload")
    public String uploadFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        FileInfo fileInfo = fileService.uploadFile(file, request);
        String fileId = fileService.saveFileRecord(fileInfo).toString();

        if (StringUtils.isEmpty(fileId)) {
            return JSONUtils.operateError("保存失败");
        }
        return JSONUtils.operateSuccess(fileInfo);
    }

    /**
     * 需要删除的文件路径保存filePath参数内
     *
     * @return 返回已删除的文件，删除错误的文件，不存在的文件。
     */
    @ResponseBody
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String deleteDocument(HttpServletRequest request) {
        String resourcesPath = request.getSession().getServletContext().getRealPath("/resources");
        List<String> removed = new LinkedList<String>();
        List<String> notRemoved = new LinkedList<String>();
        List<String> notExisted = new LinkedList<String>();

        String[] filePaths = request.getParameterValues("filePath");
        if (filePaths != null && filePaths.length > 0) {
            for (String filePath : filePaths) {
                File file = new File(resourcesPath + "/" + filePath);
                if (file.exists()) {
                    if (file.delete()) {
                        removed.add(filePath);
                    } else {
                        notRemoved.add(filePath);
                    }
                } else {
                    notExisted.add(filePath);
                }
            }
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("remvoed", removed);
        resultMap.put("notRemoved", notRemoved);
        resultMap.put("notExisted", notExisted);
        return JSONUtils.operateSuccess(resultMap);
    }
}
