package com.yi.solr.service.impl;

import com.yi.solr.model.Baike;
import com.yi.solr.service.BaikeService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * solr业务操作
 * @author YI
 * @date 2018-8-21 15:31:22
 */
@Service
public class BaikeServiceImpl implements BaikeService {
    @Autowired
    private SolrClient solrClient;

    @Override
    public void addBean(Baike baike) throws IOException, SolrServerException {
        solrClient.addBean(baike);
        solrClient.commit();
    }

    @Override
    public void addBeans(List<Baike> list) throws IOException, SolrServerException {
        solrClient.addBeans(list);
        solrClient.commit();
    }

    @Override
    public void addDocument(SolrInputDocument doc) throws IOException, SolrServerException {
        solrClient.add(doc);
        solrClient.commit();
    }

    @Override
    public SolrDocumentList queryAll() throws IOException, SolrServerException {
        // 常见查询条件
        SolrQuery query = new SolrQuery();

        query.setQuery("*:*");

        // 添加要检索的字段 * 好代表全部
        query.addField("*");

        SolrQuery.SortClause sortClause = new SolrQuery.SortClause("id", "desc");
        query.addSort(sortClause);

        QueryResponse queryResponse = solrClient.query(query);

        // 查询结果
        SolrDocumentList docs = queryResponse.getResults();

        return docs;
    }

    @Override
    public List<FacetField> group(SolrQuery query) throws IOException, SolrServerException {
        // 创建一个查询请求
        QueryRequest qryReq = new QueryRequest(query);

        // 创建一个查询响应
        QueryResponse resp = qryReq.process(solrClient);

        List<FacetField> facetFields = resp.getFacetFields();

        return facetFields;
    }

    @Override
    public SolrDocumentList queryConditions(SolrQuery query) throws IOException, SolrServerException {
        QueryResponse solrRes = solrClient.query(query);

        System.out.println("Response:" + solrRes.getResponse());
        System.out.println("ResponseHeader:" + solrRes.getResponseHeader());

        // 执行搜索条件
        SolrDocumentList docs = solrRes.getResults();

        return docs;
    }
}
