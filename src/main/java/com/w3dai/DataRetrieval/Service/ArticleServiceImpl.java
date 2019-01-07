package com.w3dai.DataRetrieval.Service;

import com.w3dai.DataRetrieval.Entity.Article;
import com.w3dai.DataRetrieval.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private  ArticleRepository articleRepository;

    @Override
    public Page<Article> findByAuthors(String name, Pageable pageable) {
        return articleRepository.findByAuthors(name, pageable);
    }

    @Override
    public Page<Article> findByAuthorsOrderByPublicationDateDesc(String name, Pageable pageable) {
        return articleRepository.findByAuthorsOrderByPublicationDateDesc(name, pageable);
    }

    @Override
    public Page<Article> findByBody(String searchContent, Pageable pageable) {
        return articleRepository.findByBody(searchContent, pageable);
    }

    @Override
    public Optional<Article> findById(String articleID) {
        return articleRepository.findById(articleID);
    }

}
