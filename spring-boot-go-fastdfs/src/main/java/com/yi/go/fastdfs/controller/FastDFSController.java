package com.yi.go.fastdfs.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.yi.go.fastdfs.config.ConstantProperties;
import com.yi.go.fastdfs.model.GoFast;
import com.yi.go.fastdfs.utils.MessageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 上传文件
 * @author YI
 * @date 2019-1-29 10:41:09
 */
@RestController
@RequestMapping("/fastdfs")
public class FastDFSController {
    /**
     * 文件上传
     * @param file 文件
     * @return
     */
    @RequestMapping("/upload")
    public MessageResult singleFileUpload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("output", "json");
        map.put("path", "");
        map.put("scene", "default");
        map.put("file", getFile(file));

        String post = HttpUtil.post(ConstantProperties.URL, map);

        GoFast goFast = JSONUtil.toBean(post, GoFast.class);

        return MessageResult.ok(goFast);
    }

    private static File getFile(MultipartFile multipartFile){
        File file = new File(multipartFile.getName());
        try {
            FileUtil.writeFromStream(multipartFile.getInputStream(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
