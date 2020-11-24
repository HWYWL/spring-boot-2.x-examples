package com.yi.xxlconf.model;

import lombok.Data;

import java.util.List;

/**
 * @author YI
 * @date 2020/3/27 11:35
 */
@Data
public class ConfBean {
    private String accessToken;
    private String env;
    private List<String> keys;
}