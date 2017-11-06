package com.corleone.file.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jackie on
 * 2017/11/5.
 */
@Entity
@Table(name = "FILE")
public class FileInfo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "ID")
    private String id;

    // 删除标志，1 : 已删除， 0,空 : 未删除
    @Column(name = "DELETETAG")
    private Integer deleteTag;

    // 数据的创建时间
    @Column(name = "CREATETIME")
    private Date createTime;

    // 更新操作的时间
    @Column(name = "UPDATETIME")
    private Date updateTime;

    // 文件的名称, 不含有后缀内容
    @Column(name = "NAME")
    private String name;

    // 文件的类型, 对应FileType枚举
    @Column(name = "TYPE")
    private String type;

    // 文件大小
    @Column(name = "SIZE")
    private Long size;

    // 文件存放的服务, 对应FileLocation枚举
    @Column(name = "location")
    private String location;

    // 文件文件后缀名, 文件格式
    @Column(name = "SUFFIXNAME")
    private String suffixName;

    // 文件文件的相对路径, 对于OSS服务，这也是key
    @Column(name = "FILEPATH")
    private String filePath;

    // 文件的内容格式
    @Column(name = "CONTENTTYPE")
    private String contentType;

    /**
     * 文件的绝对访问路径信息
     */
    @Column(name = "ABSOLUTEFILEPATH")
    private String absoluteFilePath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(Integer deleteTag) {
        this.deleteTag = deleteTag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }

    public void setAbsoluteFilePath(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
