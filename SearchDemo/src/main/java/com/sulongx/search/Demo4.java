package com.sulongx.search;

import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 从文档中提取同义词
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2021/1/18 11:22
 */
public class Demo4 {

    public static void main(String[] args) throws IOException {
        Map<String, String> sffargs = new HashMap<>();
        sffargs.put("synonyms", "synonyms-wn.txt");
        sffargs.put("ignoreCase", "true");
        //定义一个分词器
        CustomAnalyzer customAnalyzer = CustomAnalyzer.builder()
                .withTokenizer(WhitespaceTokenizerFactory.class)
                .addTokenFilter(SynonymGraphFilterFactory.class, sffargs)
                .build();
    }
}
