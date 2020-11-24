package com.yi.elasticsearch.service.impl;

import com.yi.elasticsearch.model.Article;
import com.yi.elasticsearch.repository.ArticleSearchRepository;
import com.yi.elasticsearch.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * elasticsearch操作文章
 * @author YI
 * @date 2018-8-13 10:38:49
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleSearchRepository articleSearchRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void articleSave(Article article) {
        articleSearchRepository.save(article);
    }

    @Override
    public void articleSaveList(List<Article> articleList) {
        articleSearchRepository.saveAll(articleList);
    }

    @Override
    public Iterable<Article> searchAll() {
        return articleSearchRepository.findAll();
    }

    @Override
    public Iterable<Article> findByTitle(String title) {
        return articleSearchRepository.findByTitle(title);
    }

    @Override
    public List<Article> findByContent(String content, Pageable pageable) {
        Page<Article> pageageRsutl=articleSearchRepository.findByContent(content, pageable );

        if (pageageRsutl == null) return null;

        return pageageRsutl.getContent();
    }

    @Override
    public List<Article> searchQuery(SearchQuery searchQuery) {
        Page<Article> searchPageResults = articleSearchRepository.search(searchQuery);

        if (searchPageResults == null) return null;

        return searchPageResults.getContent();
    }

    @Override
    public List<Article> templateSearchQuery(SearchQuery searchQuery) {
        List<Article> articles = elasticsearchTemplate.queryForList(searchQuery, Article.class);
        if (articles == null) return null;

        return articles;
    }
}
