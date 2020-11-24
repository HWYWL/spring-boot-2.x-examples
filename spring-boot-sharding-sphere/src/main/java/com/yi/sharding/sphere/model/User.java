package com.yi.sharding.sphere.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import groovy.transform.EqualsAndHashCode;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户实体类
 *
 * @author YI
 * @date 2019年6月3日
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@Builder
@TableName("user")
public class User extends Model<User> {

    /**
     * 主键Id
     */
    private int id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private int age;
}
