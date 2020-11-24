package com.yi.pagehelper.controller;

import com.github.pagehelper.PageHelper;
import com.yi.pagehelper.dao.BaikeMapper;
import com.yi.pagehelper.model.Baike;
import com.yi.pagehelper.utils.MessageResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 视频控制层
 * @author YI
 * @date 2018-12-16 11:26:43
 */
@RestController
@RequestMapping("/baike")
public class BaiKeController {

    @Resource
    BaikeMapper baikeMapper;

    /**
     * 查找所有数据
     * @param page 当前页
     * @param size 页大小
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.POST)
    public MessageResult selectAll(@RequestParam(value = "page",defaultValue = "1")int page,
                                   @RequestParam(value = "size",defaultValue = "10")int size){

        // 分页查询
        PageHelper.startPage(page, size);
        List<Baike> videos = baikeMapper.selectAll();

        return MessageResult.ok(videos);
    }
}
