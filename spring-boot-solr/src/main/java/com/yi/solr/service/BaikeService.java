package com.yi.solr.service;

import com.yi.solr.model.Baike;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.List;

/**
 * solr业务接口
 * @author YI
 * @date 2018-8-21 15:31:22
 */
public interface BaikeService {
    /**
     * 添加单个元数据
     * @param baike
     * @return
     */
    void addBean(Baike baike) throws IOException, SolrServerException;

    /**
     * 批量添加数据
     * @param list
     * @return
     */
    void addBeans(List<Baike> list) throws IOException, SolrServerException;

    /**
     * 按文档格式添加数据
     * @param doc
     */
    void addDocument(SolrInputDocument doc) throws IOException, SolrServerException;

    /**
     * 查询所有数据
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    SolrDocumentList queryAll() throws IOException, SolrServerException;

    /**
     * 分组查询
     * @param query 查询条件
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    List<FacetField> group(SolrQuery query) throws IOException, SolrServerException;

    /**
     * 按条件搜索
     * @param query 查询条件
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    SolrDocumentList queryConditions(SolrQuery query) throws IOException, SolrServerException;
}
