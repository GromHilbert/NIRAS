package com.w3dai.niras;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class HanLPTest {
    public static void main(String[] args){

        List<Term> testA = HanLP.segment("张良 海军 空军");

        for(Term tmp:testA){
            if(tmp.length() > 1)
                System.out.println(tmp.toString());
        }
    }
}
