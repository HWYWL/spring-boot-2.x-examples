package com.yi.auto.code;

import com.zengtengpeng.autoCode.StartCode;

/**
 * 单表生成实例
 */
public class Demo1simple {
    public static void main(String[] args) {
        StartCode startCode=t->{};
        startCode.start(StartCode.saxYaml("auto-code_simple.yaml"));
    }
}
