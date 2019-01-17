package com.w3dai.niras;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class HanLPTest {
    public static void main(String[] args){

        List<Term> testA = HanLP.segment("严守一把手机关了");

        for(Term tmp:testA){
                System.out.println(tmp.toString());
        }
    }
}
