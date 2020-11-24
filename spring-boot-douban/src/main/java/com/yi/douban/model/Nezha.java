package com.yi.douban.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 *  哪吒之魔童降世影评表
 * @author YI 2019-12-27
 */
@Data
public class Nezha implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 影评
     */
    private String filmReview;

    public Nezha() {
    }

}
