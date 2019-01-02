package com.w3dai.DataRetrieval.Controller;

import com.w3dai.DataRetrieval.Entity.Article;
import com.w3dai.DataRetrieval.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    private ArticleService articleService;


    @Autowired
    SearchController(ElasticsearchRestTemplate elasticsearchRestTemplate, ArticleService articleService){
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
        this.articleService = articleService;
    }

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        String nameToFind = "戴斌";
        Page<Article> articleByAuthorName
                = articleService.findByAuthors(nameToFind, PageRequest.of(0, 10));

        return "Hello, welcome to use NIRAS!";
    }
}

//spend some time to check DispatcherServletAutoConfiguration