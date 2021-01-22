package com.sulongx.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.util.CharsRef;

import java.io.IOException;

/**
 * 自定义分词器
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2021/1/15 18:17
 */
public class Demo3 {

    public static void main(String[] args) throws IOException {

        //定义同义词
        SynonymMap.Builder builder = new SynonymMap.Builder();
        builder.add(new CharsRef("aeroplane"), new CharsRef("plane"), true);
        final SynonymMap map = builder.build();
        //创建一个定制分词器
        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName) {
                WhitespaceTokenizer tokenizer = new WhitespaceTokenizer();
                //创建同义词过滤器,该过滤器从空白分词器接收词项,并根据该词的映射词扩展同义词，忽略大小写
                SynonymGraphFilter synFilter = new SynonymGraphFilter(tokenizer, map, true);

                return new TokenStreamComponents(tokenizer, synFilter);
            }
        };
        //搜索时的空白分词器
        Analyzer searchTimeAnalyzer = new WhitespaceAnalyzer();


    }
}
