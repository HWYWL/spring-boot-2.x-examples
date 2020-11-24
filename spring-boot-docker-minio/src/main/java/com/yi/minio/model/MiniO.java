package com.yi.minio.model;

import lombok.Data;

/**
 * 文件系统返回数据
 * @author YI
 * @date 2020-8-12 11:47:02
 */
@Data
public class MiniO {
    private String ETag;
    private String Key;
    private String Size;
}
