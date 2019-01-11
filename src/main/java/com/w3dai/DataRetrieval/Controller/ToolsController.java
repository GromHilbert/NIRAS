package com.w3dai.DataRetrieval.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tools/")
public class ToolsController {

    @RequestMapping("/")
    public String nirasTools(){
        return "tools/toolslist";
    }

    @RequestMapping("/textAnalysis")
    public String textAnalysis(){
        return "tools/textAnalysis";
    }
}
