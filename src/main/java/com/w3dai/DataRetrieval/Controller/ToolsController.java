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
import java.util.*;


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

        HashMap<String,Integer> wordFreq = new HashMap<String, Integer>();
        String segmentedWord = null;

        for(Term tmp:outputText){
            int count = 0;
            segmentedWord = tmp.toString();
            if(segmentedWord.length()>=2 && segmentedWord.matches("^[\u4e00-\u9fbb]+$")) {
                if (wordFreq.get(segmentedWord) == null) {
                    count = 1;
                } else {
                    count = wordFreq.get(segmentedWord).intValue() + 1;
                }
                wordFreq.put(tmp.toString(), count);
            }

            if(outputString == null)
                outputString = segmentedWord;
            else
                outputString = outputString  + "/  " + segmentedWord;
        }


        model.addAttribute("outputString", outputString);
        model.addAttribute("wordFreq", hashMapSort(wordFreq));

        return "tools/textAnalysis";
    }

    private static String getFileExtension(String name){
        return name.substring(name.lastIndexOf("."));
    }

    public static HashMap<String, Integer>  hashMapSort(HashMap<String, Integer> map){
        //1、按顺序保存map中的元素，使用LinkedList类型
        List<Map.Entry<String, Integer>> keyList = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
        //2、按照自定义的规则排序
        Collections.sort(keyList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                if(o2.getValue().compareTo(o1.getValue())>0){
                    return 1;
                }else if(o2.getValue().compareTo(o1.getValue())<0){
                    return -1;
                }  else {
                    return 0;
                }
            }

        });
        //3、将LinkedList按照排序好的结果，存入到HashMap中
        HashMap<String,Integer> result=new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry:keyList){
            result.put(entry.getKey(),entry.getValue());
        }
        return result;
    }
}
