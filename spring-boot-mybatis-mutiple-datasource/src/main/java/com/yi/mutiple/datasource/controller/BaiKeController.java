package com.yi.mutiple.datasource.controller;

import com.yi.mutiple.datasource.model.Baike;
import com.yi.mutiple.datasource.service.BaikeService;
import com.yi.mutiple.datasource.utils.MessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 百科
 * @author YI
 * @date 2018-11-29 15:28:03
 */
@RestController
@RequestMapping("/baike")
public class BaiKeController {
    private static final Logger log = LoggerFactory.getLogger(BaiKeController.class);

    @Autowired
    BaikeService baikeService;

    @RequestMapping("/findBaikeById")
    public MessageResult findBaikeById(){
        int id = 1;

        Baike baike = baikeService.findBaikeById(id);
        log.info(baike.toString());

        return MessageResult.ok(baike);
    }
}
