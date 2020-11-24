package com.yi.docker.controller;

import cn.hutool.core.util.RandomUtil;
import com.yi.docker.common.MessageResult;
import com.yi.docker.utils.QiNiuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 七牛存储操作
 * @author YI
 * @date 2018-8-7 10:37:27
 */
@Controller
@RequestMapping("/qiniu")
public class QiNiuController {

    /**
     * 上传文件到七牛云存储
     * @param file 文件
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public MessageResult singleFileUpload(@RequestParam("file") MultipartFile file) {
        MessageResult result = MessageResult.ok();

        try {
            FileInputStream inputStream = (FileInputStream) file.getInputStream();
            result = QiNiuUtil.uploadQNImg(inputStream, RandomUtil.randomString(16));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
