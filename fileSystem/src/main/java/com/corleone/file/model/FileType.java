package com.corleone.file.model;

/**
 * 文件的类型
 * Created by jackie on
 * 2017/10/27.
 */
public enum FileType {

    picture("图片"),

    video("视频"),

    document("文档");

    // 枚举的中文信息
    private String message;

    FileType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
