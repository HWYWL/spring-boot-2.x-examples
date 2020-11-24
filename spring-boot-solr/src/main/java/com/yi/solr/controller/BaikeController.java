package com.yi.solr.controller;

import com.yi.solr.model.Baike;
import com.yi.solr.service.BaikeService;
import com.yi.solr.utils.MessageResult;
import com.yi.solr.utils.SolrDocBeanUtil;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * solr执行操作
 * @author YI
 * @date 2018-8-21 21:23:34
 */
@RestController
@RequestMapping("/solr")
public class BaikeController {
    @Autowired
    BaikeService baikeService;

    /**
     * 初始化数据,项目第一次启动的时候执行一下这个接口，插入数据到solr中
     * @return
     */
    @RequestMapping("/init")
    public MessageResult init(){
        MessageResult result = new MessageResult();
        List<Baike> baikeList = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        list1.add("文学");
        list1.add("小说");
        Baike baike1 = new Baike(1, "老人与海", list1, 1000, 10, "海明威", "男", 100, 0, new Date(), new Date());

        List<String> list2 = new ArrayList<>();
        list2.add("魔幻");
        list2.add("小说");
        Baike baike2 = new Baike(2, "全职法师", list2, 1000000, 10, "乱", "男", 1000, 0, new Date(), new Date());

        baikeList.add(baike1);
        baikeList.add(baike2);

        try {
            baikeService.addBeans(baikeList);
        } catch (IOException | SolrServerException e) {
            result.setMsg(e.getMessage());
            result.setCode(-1);
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 更新数据
     * @param id id
     * @param status 书本状态，0：上架、-1：下架
     * @return
     */
    @RequestMapping("/updata")
    public MessageResult updata(int id, int status){
        MessageResult result = new MessageResult();

        SolrQuery query = new SolrQuery("*:*");

        // 设置返回哪些的列
        query.addField("*");
        // 设定开始序号
        query.setStart(0);

        query.addFilterQuery("id:" + id);

        try {
            // 根据id查找数据
            SolrDocumentList solrDocumentList = baikeService.queryConditions(query);
            List<Baike> baikes = (List<Baike>)SolrDocBeanUtil.toBeanList(solrDocumentList, Baike.class);

            baikes.forEach(baike -> {
                baike.setStatus(status);
                try {
                    // 跟新数据
                    baikeService.addBean(baike);
                } catch (IOException | SolrServerException e) {
                    result.setCode(-1);
                    result.setData(e.getMessage());
                    e.printStackTrace();
                }
            });
        } catch (IOException | SolrServerException e) {
            result.setCode(-1);
            result.setData(e.getMessage());
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 查询所有数据
     * @return
     */
    @RequestMapping("/queryAll")
    public MessageResult queryAll(){
        MessageResult result = new MessageResult();
        MessageResult respMessage = new MessageResult();
        SolrDocumentList solrDocumentList = null;

        try {
            solrDocumentList = baikeService.queryAll();
        } catch (IOException | SolrServerException e) {
            result.setCode(-1);
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }

        List<Baike> baikes = (List<Baike>)SolrDocBeanUtil.toBeanList(solrDocumentList, Baike.class);
        result.setData(baikes);
        respMessage.setData(baikes);

        return respMessage;
    }

    /**
     * 分组查询
     * @return
     */
    @RequestMapping("/group")
    public MessageResult group(){
        MessageResult result = new MessageResult();
        SolrQuery query = new SolrQuery();

        // 设置查询字符串 * 号代表所有
        query.setQuery("*:*");

        // 设置行
        query.setRows(5);

        // 按照作者分组
        query.addFacetField("name");
        List<FacetField> group = null;
        try {
            group = baikeService.group(query);
        } catch (IOException | SolrServerException e) {
            result.setCode(-1);
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }

        result.setData(group);

        return result;
    }

    /**
     * 根据条件查询并按照点赞数排序
     * @return
     */
    @RequestMapping("/queryGood")
    public MessageResult queryGood() {
        MessageResult result = new MessageResult();
        SolrQuery query = new SolrQuery("*:*");

        // 设置返回哪些的列
        query.addField("*");
        // 设定开始序号
        query.setStart(0);
        // 设定返回的行数
        query.setRows(5);
        // 设置按照点赞数排序
        query.setSort(new SolrQuery.SortClause("good", "desc"));

        SolrDocumentList solrDocumentList = null;

        try {
            solrDocumentList = baikeService.queryConditions(query);
        } catch (IOException | SolrServerException e) {
            result.setCode(-1);
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }

        List<Baike> baikes = (List<Baike>)SolrDocBeanUtil.toBeanList(solrDocumentList, Baike.class);
        result.setData(baikes);

        return result;
    }

    /**
     * 根据条件模糊查询
     * @return
     */
    @RequestMapping("/queryName")
    public MessageResult queryName() {
        MessageResult result = new MessageResult();
        SolrQuery query = new SolrQuery("*:*");

        // 设置返回哪些的列
        query.addField("*");
        // 设定开始序号
        query.setStart(0);
        // 设定返回的行数
        query.setRows(5);

        // 等价sql： name like '海%'
        query.addFilterQuery("name:海*");

        SolrDocumentList solrDocumentList = null;

        try {
            solrDocumentList = baikeService.queryConditions(query);
        } catch (IOException | SolrServerException e) {
            result.setMsg(e.getMessage());
            result.setCode(-1);
            e.printStackTrace();
        }

        List<Baike> baikes = (List<Baike>)SolrDocBeanUtil.toBeanList(solrDocumentList, Baike.class);
        result.setData(baikes);

        return result;
    }

    /**
     * 按照范围搜索
     * @return
     */
    @RequestMapping("/queryInterval")
    public MessageResult queryInterval() {
        MessageResult result = new MessageResult();
        SolrQuery query = new SolrQuery("*:*");

        // 设置返回哪些的列
        query.addField("*");
        // 设定开始序号
        query.setStart(0);
        // 设定返回的行数
        query.setRows(5);

        // 等价sql：SL >= 20
//        query.addFilterQuery("good:[1000 TO *]");
        // 等价sql：SL > 20
        query.addFilterQuery("good:{1000 TO *]");

        SolrDocumentList solrDocumentList = null;

        try {
            solrDocumentList = baikeService.queryConditions(query);
        } catch (IOException | SolrServerException e) {
            result.setMsg(e.getMessage());
            result.setCode(-1);
            e.printStackTrace();
        }

        List<Baike> baikes = (List<Baike>)SolrDocBeanUtil.toBeanList(solrDocumentList, Baike.class);
        result.setData(baikes);

        return result;
    }
}
