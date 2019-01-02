package com.w3dai.DataRetrieval.Repositories;

import com.w3dai.DataRetrieval.Entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, String> {
    Page<Article> findByAuthors(String name, Pageable pageable);
}
