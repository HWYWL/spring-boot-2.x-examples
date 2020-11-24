package com.yi.solr.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * SolrDocument 转换为bean
 * @author YI
 * @date 2018-8-21 17:05:58
 */
public class SolrDocBeanUtil {
    /**
     * 通用(Bean中不在包含其他Bean)将SolrDocument转换成Bean
     * @param doc 文档
     * @param clazz 字节码
     * @return
     */
    public static Object toBean(SolrDocument doc, Class clazz){
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        }

        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            Object value = doc.get(field.getName());
            try {
                BeanUtils.setProperty(obj, field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    /**
     * 通用(Bean中不在包含其他Bean)将SolrDocumentList转换成BeanList
     * @param documents 文档列表
     * @param clazz 字节码
     * @return
     */
    public static Object toBeanList(SolrDocumentList documents, Class clazz){
        List list = new ArrayList();

        for(SolrDocument doc : documents){
            list.add(toBean(doc, clazz));
        }

        return list;
    }
}
