package com.corleone.file.model;

/**
 * 存放文件的服务的位置名称
 * Created by jackie on
 * 2017/10/27.
 */
public enum FileLocation {

    oss("OSS服务"),

    fileSys("文件系统"),

    ftp("文件服务器");

    private String message;

    FileLocation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
