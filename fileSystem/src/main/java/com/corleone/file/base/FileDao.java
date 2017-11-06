package com.corleone.file.base;


import com.corleone.file.entity.FileInfo;

import java.io.Serializable;

/**
 * Created by jackie on
 * 2017/11/5.
 */
public interface FileDao {

    /**
     * 保存文件对象
     *
     * @param fileInfo 文件对象
     * @return 文件的主键
     * @throws Exception
     */
    Serializable save(FileInfo fileInfo) throws Exception;
}
