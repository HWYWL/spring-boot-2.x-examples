package com.yi.docker.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS配置信息
 * @author YI
 * @date 2018-8-7 16:34:25
 */
@Component
public class ConstantProperties implements InitializingBean{

    @Value("${oss.file.endpoint}")
    private String oss_file_endpoint;

    @Value("${oss.file.keyid}")
    private String oss_file_keyid;

    @Value("${oss.file.keysecret}")
    private String oss_file_keysecret;

    @Value("${oss.file.filehost}")
    private String oss_file_filehost;

    @Value("${oss.file.bucketname1}")
    private String oss_file_bucketname1;

    @Value("${qiniu.file.accessKey}")
    private String accessKey;

    @Value("${qiniu.file.secretKey}")
    private String secretKey;

    @Value("${qiniu.file.bucket}")
    private String bucket;

    @Value("${qiniu.file.path}")
    private String path;


    public static String OSS_END_POINT         ;
    public static String OSS_ACCESS_KEY_ID     ;
    public static String OSS_ACCESS_KEY_SECRET ;
    public static String OSS_BUCKET_NAME1      ;
    public static String OSS_FILE_HOST         ;

    public static String QINIU_FILE_ACCESSKEY  ;
    public static String QINIU_FILE_SECRETKEY  ;
    public static String QINIU_FILE_BUCKET     ;
    public static String QINIU_FILE_PATH       ;

    @Override
    public void afterPropertiesSet() {
        OSS_END_POINT = oss_file_endpoint;
        OSS_ACCESS_KEY_ID = oss_file_keyid;
        OSS_ACCESS_KEY_SECRET = oss_file_keysecret;
        OSS_FILE_HOST = oss_file_filehost;
        OSS_BUCKET_NAME1 = oss_file_bucketname1;

        QINIU_FILE_ACCESSKEY = accessKey;
        QINIU_FILE_SECRETKEY = secretKey;
        QINIU_FILE_BUCKET = bucket;
        QINIU_FILE_PATH = path;
    }

    public void setOss_file_endpoint(String oss_file_endpoint) {
        this.oss_file_endpoint = oss_file_endpoint;
    }

    public void setOss_file_keyid(String oss_file_keyid) {
        this.oss_file_keyid = oss_file_keyid;
    }

    public void setOss_file_keysecret(String oss_file_keysecret) {
        this.oss_file_keysecret = oss_file_keysecret;
    }

    public void setOss_file_filehost(String oss_file_filehost) {
        this.oss_file_filehost = oss_file_filehost;
    }

    public void setOss_file_bucketname1(String oss_file_bucketname1) {
        this.oss_file_bucketname1 = oss_file_bucketname1;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
