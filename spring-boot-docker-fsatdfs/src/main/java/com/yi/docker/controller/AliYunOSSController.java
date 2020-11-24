package com.yi.docker.controller;

import com.yi.docker.common.MessageResult;
import com.yi.docker.utils.AliyunOSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

/**
 * OSS文件系统操作
 * @author YI
 * @date 2018-8-7 16:47:29
 */
@Controller
@RequestMapping("/oss")
public class AliYunOSSController {
    private static Logger logger = LoggerFactory.getLogger(AliYunOSSController.class);

    /**
     * 文件上传
     * @param file 文件
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public MessageResult singleFileUpload(@RequestParam("file") MultipartFile file) {
        HashMap<Object, Object> map = new HashMap<>(16);

        if (file.isEmpty()) {
            return MessageResult.errorMsg("请选择要上传的文件！");
        }

        MessageResult messageResult = MessageResult.ok();
        try {
            String filename = file.getOriginalFilename();
            File newFile = new File(filename);
            FileOutputStream os = new FileOutputStream(newFile);

            os.write(file.getBytes());
            os.close();
            file.transferTo(newFile);

            //上传到OSS
            messageResult = AliyunOSSUtil.upload(newFile);
        } catch (Exception e) {
            logger.error("上传失败！", e);
        }

        return messageResult;
    }

}
