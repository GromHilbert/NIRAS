package com.w3dai.DataRetrieval.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Hello, welcome to use NIRAS!";
    }
}

//spend some time to check DispatcherServletAutoConfiguration