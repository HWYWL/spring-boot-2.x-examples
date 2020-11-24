package com.yi.docker.utils;

import cn.hutool.core.util.CharUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yi.docker.common.MessageResult;
import com.yi.docker.config.ConstantProperties;

import java.io.FileInputStream;

/**
 * 七牛云存储操作
 * @author YI
 * @date 2018-8-7 10:37:27
 */
public class QiNiuUtil {
    /**
     * 将图片上传到七牛云
     */
    public static MessageResult uploadQNImg(FileInputStream file, String key) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传

        try {
            Auth auth = Auth.create(ConstantProperties.QINIU_FILE_ACCESSKEY, ConstantProperties.QINIU_FILE_SECRETKEY);
            String upToken = auth.uploadToken(ConstantProperties.QINIU_FILE_BUCKET);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                StringBuffer buffer = new StringBuffer();
                buffer.append(ConstantProperties.QINIU_FILE_PATH);
                buffer.append(CharUtil.SLASH);
                buffer.append(putRet.key);

                return MessageResult.ok(buffer.toString());
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return MessageResult.errorMsg("文件上传失败！");
    }
}
