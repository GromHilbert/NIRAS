package com.w3dai.DataRetrieval.Service;

import com.w3dai.DataRetrieval.Entity.Article;
import com.w3dai.DataRetrieval.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ElasticsearchRestTemplate elasticsearchRestTemplate, ArticleRepository articleRepository) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.articleRepository = articleRepository;
    }

    @Override
    public Page<Article> findByAuthors(String name, Pageable pageable) {
        return articleRepository.findByAuthors(name, pageable);
    }

    @Override
    public long count() {
        return articleRepository.count();
    }

    @Override
    public void delete(Article article) {
        articleRepository.delete(article);
    }
}
