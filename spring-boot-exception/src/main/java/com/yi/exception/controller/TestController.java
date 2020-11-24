package com.yi.exception.controller;

import com.yi.exception.constant.Status;
import com.yi.exception.exception.JsonException;
import com.yi.exception.exception.PageException;
import com.yi.exception.model.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试
 *
 * @author YI
 * @date 2020-7-22
 */
@Controller
public class TestController {

    /**
     * 统一异常返回接送
     *
     * @return json数据
     */
    @GetMapping("/json")
    @ResponseBody
    public ApiResponse jsonException() {
        throw new JsonException(Status.UNKNOWN_ERROR);
    }

    /**
     * 统一异常返回页面
     *
     * @return 页面
     */
    @GetMapping("/page")
    public ModelAndView pageException() {
        throw new PageException(Status.UNKNOWN_ERROR);
    }
}
