package com.yi.minio.controller;

import com.yi.minio.config.MiniOConfig;
import com.yi.minio.utils.MessageResult;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 上传文件
 *
 * @author YI
 * @date 2020-8-12 10:26:08
 */
@RestController
@RequestMapping("/minio")
public class MiniOController {
    @Autowired
    MiniOConfig miniOConfig;

    @Autowired
    MinioClient minioClient;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return
     */
    @RequestMapping("/upload")
    public MessageResult singleFileUpload(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        minioClient.putObject(miniOConfig.bucket, fileName, file.getInputStream(), file.getSize(), file.getContentType());

        Iterable<Result<Item>> results = minioClient.listObjects(miniOConfig.bucket);
        List<Item> files = new ArrayList<>();
        for (Result<Item> result : results) {
            files.add(result.get());
        }
        return MessageResult.ok(files);
    }
}
