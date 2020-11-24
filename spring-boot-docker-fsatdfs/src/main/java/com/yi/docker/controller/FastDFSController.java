package com.yi.docker.controller;

import cn.hutool.core.util.CharUtil;
import com.yi.docker.common.MessageResult;
import com.yi.docker.model.FastDFSFile;
import com.yi.docker.utils.FastDFSClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * FastDFS文件系统操作
 * @author YI
 * @date 2018-8-7 10:37:27
 */
@Controller
@RequestMapping("/fastdfs")
public class FastDFSController {
    private static Logger logger = LoggerFactory.getLogger(FastDFSController.class);

    /**
     * 文件上传
     * @param file 文件
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public MessageResult singleFileUpload(@RequestParam("file") MultipartFile file) {
        HashMap<Object, Object> map = new HashMap<>(16);
        MessageResult messageResult = MessageResult.ok();

        if (file.isEmpty()) {
            return MessageResult.errorMsg("请选择要上传的文件！");
        }

        try {
            String path = saveFile(file);
            map.put("data", path);
        } catch (Exception e) {
            logger.error("上传失败！", e);
            messageResult = MessageResult.errorMsg("上传失败！" + e.getMessage());
        }
        messageResult.setData(map);

        return messageResult;
    }

    /**
     * @param multipartFile 文件
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath={};
        byte[] file_buff = null;

        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        InputStream inputStream = multipartFile.getInputStream();

        if(inputStream != null){
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }

        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);

        try {
            fileAbsolutePath = FastDFSClientUtil.upload(file);
        } catch (Exception e) {
            logger.error("文件上传异常!", e);
        }

        if (fileAbsolutePath==null) {
            logger.error("上传文件失败，请再次上传!");
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append(FastDFSClientUtil.getTrackerUrl());
        buffer.append(fileAbsolutePath[0]);
        buffer.append(CharUtil.SLASH);
        buffer.append(fileAbsolutePath[1]);

        return buffer.toString();
    }
}
