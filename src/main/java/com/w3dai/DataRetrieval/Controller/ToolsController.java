package com.w3dai.DataRetrieval.Controller;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;


@Controller
@RequestMapping("/tools/")
public class ToolsController {
    public static final Resource TEMPFILES_DIR = new FileSystemResource("tempFiles");
    @RequestMapping("/")
    public String nirasTools(){
        return "tools/toolslist";
    }

    @RequestMapping("/textAnalysis")
    public String textAnalysisPage(){
        return "tools/textAnalysis";
    }

    @RequestMapping(value = "/textAnalysis", method = RequestMethod.POST)
    public String textAnalysis(MultipartFile file, Model model) throws IOException {
        String filename = file.getOriginalFilename();
        File tempFile = File.createTempFile("txt", getFileExtension(filename), TEMPFILES_DIR.getFile());
        try ( InputStream in = file.getInputStream();
        OutputStream out = new FileOutputStream(tempFile)){
            IOUtils.copy(in, out);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile), "utf-8"));
        StringBuilder stringBuilder = new StringBuilder();
        String tempLine;
        while((tempLine = bufferedReader.readLine())!=null){
            stringBuilder.append(tempLine);
        }

        List<Term> outputText = HanLP.segment(stringBuilder.toString());
        String outputString = null;

        for(Term tmp:outputText){
            if(outputString == null)
                outputString = tmp.toString();
            else
                outputString = outputString  + '/' + tmp.toString();
        }
        model.addAttribute("outputString", outputString);
        return "tools/textAnalysis";
    }

    private static String getFileExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }
}
