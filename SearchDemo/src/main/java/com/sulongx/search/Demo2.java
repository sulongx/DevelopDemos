package com.sulongx.search;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LiveIndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建文档
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2021/1/15 17:37
 */
public class Demo2 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("D:\\workspace\\javaapplication\\DevelopDemos\\SearchDemo\\src\\main\\resources\\lucene");

        Directory directory = FSDirectory.open(path);

        //创建单字段分析器
        Map<String, Analyzer> perFieldAnalyzers = new HashMap<>();
        //创建一个停用词词素列表,用与在索引时从图书内容中进行删除
        CharArraySet stopWords = new CharArraySet(Arrays.asList("a", "an", "the"), true);
        //为页面字段使用指定停用词的 StopAnalyzer (停词分析器)
        perFieldAnalyzers.put("pages", new StopAnalyzer(stopWords));

        //对标题字段使用 WhitespaceAnalyzer
        perFieldAnalyzers.put("title", new WhitespaceAnalyzer());

        //创建一个单字段分析器,它也需要一个默认分析器(本例中为英语分析器),以备其他需要添加到Document中的字段使用
        Analyzer analyzer = new PerFieldAnalyzerWrapper(new EnglishAnalyzer(), perFieldAnalyzers);

        //将文档加入Lucene索引
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(directory, config);
        //创建实例文档
        Document document01 = new Document();
        document01.add(new TextField("title", "DL for search", Field.Store.YES));
        document01.add(new TextField("page", "Living in the information age ...", Field.Store.YES));
        Document document02 = new Document();
        document02.add(new TextField("title", "Relevant search", Field.Store.YES));
        document02.add(new TextField("page", "Getting a search engine to behave ...", Field.Store.YES));

        //将文档加入索引
        writer.addDocument(document01);
        writer.addDocument(document02);

        //提交更改
        writer.commit();
        writer.close();
    }
}
