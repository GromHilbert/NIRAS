package com.w3dai.DataRetrieval.Service;

import com.w3dai.DataRetrieval.Entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleService {

    Page<Article> findByAuthors(String name, Pageable pageable);

    Page<Article> findByPublicationDate(String searchContent, Pageable pageable);

}
