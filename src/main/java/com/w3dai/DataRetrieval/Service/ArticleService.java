package com.w3dai.DataRetrieval.Service;

import com.w3dai.DataRetrieval.Entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ArticleService {

    Page<Article> findByAuthors(String name, Pageable pageable);

    Page<Article> findByBodyOrderByPublicationDateDesc(String searchContent, Pageable pageable);

    Page<Article> findByBody(String name, Pageable pageable);

    Optional<Article> findById(String articleID);

    Page<Article> findBySectionNameOrderByPublicationDateDesc(String searchContent, Pageable pageable);

    Page<Article> findBySectionName(String name, Pageable pageable);

}
