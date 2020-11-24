package com.yi.version.controller;

import com.yi.version.annotations.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 版本号匹配
 *
 * @author huangwenyi
 */
@ApiVersion
@RestController
@RequestMapping("api/{version}/test")
public class TestController {

    /**
     * 只能匹配v1的版本
     *
     * @param version 版本号
     */
    @GetMapping
    public String test01(@PathVariable String version) {
        return "test01 : " + version;
    }

    /**
     * 匹配大于等于版本为2的版本，例如v2、v3
     *
     * @param version 版本号
     */
    @GetMapping
    @ApiVersion(2)
    public String test02(@PathVariable String version) {
        return "test02 : " + version;
    }
}
