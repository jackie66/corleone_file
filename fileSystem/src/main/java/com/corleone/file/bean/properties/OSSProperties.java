package com.corleone.file.bean.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by jackie on
 * 2017/11/2.
 */
@Component
public class OSSProperties {
    @Value("${application.oss.bucketName}")
    private String bucketName;

    @Value("${application.oss.endpoint}")
    private String endpoint;

    @Value("${application.oss.accessKeyID}")
    private String accessKeyId;

    @Value("${application.oss.accessKeySecret}")
    private String accessKeySecret;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
