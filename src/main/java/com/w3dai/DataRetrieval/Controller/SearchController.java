package com.w3dai.DataRetrieval.Controller;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.w3dai.DataRetrieval.Entity.Article;
import com.w3dai.DataRetrieval.Service.ArticleService;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


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
        List<Term> testA = HanLP.segment(searchContent);

        searchContent = QueryParser.escape(searchContent.trim().replace(" ","/"));

        /*Another stupid way to highlight the searching keywords*/
        Page<Article> searchResults = articleService.findByBody(searchContent, pageable);
        List<Article> tempArticleList = searchResults.getContent();
        for(Article articleBody : tempArticleList){
            for(Term searchWord : testA) {
                articleBody.setBody(articleBody.getBody().replace(searchWord.toString(), "<font color=\"red\">"+searchWord+"</font>"));
            }
        }


        long searchedArticlesNum = searchResults.getTotalElements();

        model.addAttribute("articleList", tempArticleList);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("searchedArticlesNum", searchedArticlesNum);
        model.addAttribute("searchContent", searchContent);

        return "result/resultPage";
    }
    @RequestMapping("/result/article")
    public String searchArticle(@RequestParam(value="articleID") String articleID,
                                Model model)
    {
        Optional<Article> getArticle = articleService.findById(articleID);
        model.addAttribute("article", getArticle.get());

        return "result/article";
    }
}

//spend some time to check DispatcherServletAutoConfiguration