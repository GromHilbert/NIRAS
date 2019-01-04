package com.w3dai.DataRetrieval.Controller;

import com.w3dai.DataRetrieval.Entity.Article;
import com.w3dai.DataRetrieval.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SearchController {

    private ArticleService articleService;
    @Autowired
    SearchController(ArticleService articleService){
        this.articleService = articleService;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/result/resultPage")
    public String searchAction(@RequestParam(value="searchContent",required = false,defaultValue = "解放军") String searchContent,
                               @PageableDefault(size = 20) Pageable pageable,
                               Model model){
        //Page<Article> searchResults = articleService.findByAuthors(searchContent, pageable);
       Page<Article> searchResults = articleService.findByAuthorsOrderByPublicationDateDesc(searchContent, pageable);

        long searchedArticlesNum = searchResults.getTotalElements();


        model.addAttribute("searchResults", searchResults);
        model.addAttribute("searchedArticlesNum", searchedArticlesNum);
        model.addAttribute("searchContent", searchContent);

        return "result/resultPage";

    }
}

//spend some time to check DispatcherServletAutoConfiguration