package com.yi.elasticsearch.service;

import com.yi.elasticsearch.model.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * elasticsearch操作文章
 * @author YI
 * @date 2018-8-13 10:38:49
 */
public interface ArticleService {

    /**
     * 保存单个实例
     * @param article
     */
    void articleSave(Article article);

    /**
     * 批量保存
     * @param articleList
     */
    void articleSaveList(List<Article> articleList);

    /**
     * 查找全部
     * @return
     */
    Iterable<Article> searchAll();

    /**
     * 按照标题精确查找
     * @param title 文章标题
     * @return
     */
    Iterable<Article> findByTitle(String title);

    /**
     * 查找文章内容 分页排序
     * @param content   文章内容
     * @param pageable  分页排序
     * @return
     */
    List<Article> findByContent(String content, Pageable pageable);

    /**
     * 多条件聚合搜索
     * @param searchQuery   搜索条件
     * @return
     */
    List<Article> searchQuery(SearchQuery searchQuery);

    /**
     * 通过模板引擎查询
     * @param searchQuery 查询条件
     * @return
     */
    List<Article> templateSearchQuery(SearchQuery searchQuery);
}
