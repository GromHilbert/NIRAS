package com.w3dai.DataRetrieval.Controller;

import com.w3dai.DataRetrieval.Entity.Article;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class DataRetrievalController {
    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/result/showResultPage")
    public String searchAction(@RequestParam(value="searchContent",required = false,defaultValue = "解放军") String searchContent,
                               @RequestParam("pageSize") Optional<Integer> pageSize,
                               @RequestParam("page") Optional<Integer> page,
                               Model model){

        List<Author> searchAuthorResult = null;
        List<Authorfeature> searchAuthorFeatureResult = null;

        if(searchContent.matches("^[\\u4E00-\\u9FA5]{2,4}")) {
            searchAuthorResult = authorInfoService.searchByAuthorName(searchContent);
            searchAuthorFeatureResult = authorFeatureRepository.findByAuthorName(searchContent);
        }

        if(searchAuthorResult != null){
            model.addAttribute("author", searchAuthorResult);
            model.addAttribute("authorFeature", searchAuthorFeatureResult);
        }

        authorService.setSearchContent(searchContent);

        int evalPageSize = pageSize.orElse(INIT_PAGE_SIZE);
        int evalPageIndex = (page.orElse(0) < 1) ? 0 : page.get() - 1;

        int fromValue = evalPageIndex * evalPageSize;
        PagedList<Article> pagedList = new PagedList<>(evalPageSize, evalPageIndex);

        Map<String, Object> searchDataResults = authorService.getArticlesBySearchContent(searchContent, fromValue, evalPageSize);
        List<Article> articleList = (List<Article>)searchDataResults.get("resultsOfWanted");

        int totalCount = Integer.parseInt(searchDataResults.get("totalNumOfResults").toString());

        pagedList.setItemList(articleList);
        pagedList.setTotalCount(totalCount);
        model.addAttribute("list", pagedList);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("searchContent", searchContent);
        model.addAttribute("searchedArticlesNum", searchDataResults.get("totalNumOfResults"));

        Aggregations newAggregation = authorService.shouldReturnAggregatedResponseForGivenSearchQuery();

        Map<String, Aggregation> map=newAggregation.asMap();
        ArrayList<AuthorResult> authorListWithArticleNumber = new ArrayList<>();
        for(String s:map.keySet()){
            StringTerms a=(StringTerms) map.get(s);
            List<StringTerms.Bucket> list=a.getBuckets();

            for(Terms.Bucket b:list){
                AuthorResult tempAuthor = new AuthorResult();
                if(!b.getKeyAsString().equals("等")) {
                    tempAuthor.setAuthorName(b.getKeyAsString());
                    tempAuthor.setArticleNum((int) b.getDocCount());
                    authorListWithArticleNumber.add(tempAuthor);
                }
            }
        }

        if(authorListWithArticleNumber.size() != 0){
            model.addAttribute("authors", authorListWithArticleNumber);
        }

        model.addAttribute("searchContent", searchContent);
        return "/result/showResultPage";
    }

}

