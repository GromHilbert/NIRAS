package com.w3dai.DataRetrieval.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    SearchController(ElasticsearchTemplate elasticsearchTemplate){
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Hello, welcome to use NIRAS!";
    }
}

//spend some time to check DispatcherServletAutoConfiguration