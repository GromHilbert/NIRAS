package com.w3dai.DataRetrieval.Service;

import com.w3dai.DataRetrieval.Entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleService {

    Page<Article> findByAuthors(String name, Pageable pageable);

    long count();

    void delete(Article article);
}
