package com.yi.auto.code;


import com.zengtengpeng.autoCode.StartCode;
import com.zengtengpeng.relation.utils.RelationUtils;

/**
 * 一对一生成实例 test_user 一个用户 对应 test_class 一个班级
 */
public class Demo2OneToOne {
    public static void main(String[] args) {
        RelationUtils.oneToOne(StartCode.saxYaml("auto-code_one-to-one.yaml"), t->{}, rt -> {});
    }
}
