package com.yi.docker.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.yi.docker.common.MessageResult;
import com.yi.docker.config.ConstantProperties;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 阿里云OSS文件系统操作工具类
 * @author YI
 * @date 2018-8-7 16:42:08
 */
public class AliyunOSSUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);


    public static MessageResult upload(File file) {
        logger.info("=========>OSS文件上传开始：" + file.getName());
        String endpoint = ConstantProperties.OSS_END_POINT;
        String accessKeyId = ConstantProperties.OSS_ACCESS_KEY_ID;
        String accessKeySecret = ConstantProperties.OSS_ACCESS_KEY_SECRET;
        String bucketName = ConstantProperties.OSS_BUCKET_NAME1;
        String fileHost = ConstantProperties.OSS_FILE_HOST;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        if (null == file) {
            return MessageResult.errorMsg("文件为空！");
        }

        OSS oss = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            //容器不存在，就创建
            if (!oss.doesBucketExist(bucketName)) {
                oss.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                oss.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            //上传文件
            PutObjectResult result = oss.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置权限 这里是公开读
            oss.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (null != result) {
                logger.info("==========>OSS文件上传成功,OSS地址：" + fileUrl);

                return MessageResult.ok(fileUrl);
            }
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getMessage());
        } finally {
            //关闭
            oss.shutdown();
        }

        return MessageResult.errorMsg("文件上传失败！");
    }
}
