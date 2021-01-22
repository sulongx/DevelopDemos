package com.sulongx.search;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 空白分词器查询
 * @author sulongx
 * @version 1.0
 * @description
 * @date 2021/1/14 17:48
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        //倒排索引文件
        Path path = Paths.get("D:\\workspace\\javaapplication\\DevelopDemos\\SearchDemo\\src\\main\\resources\\lucene");

        Directory directory = FSDirectory.open(path);

        DirectoryReader reader = DirectoryReader.open(directory);

        //通过identifier(Document标识)获取Document
        int identifier = 1;
        Document document1 = reader.document(identifier);

        //使用WhitespaceAnalyzer（空白分析器）为标题字段创建查询解析器
        QueryParser parser = new QueryParser("title", new WhitespaceAnalyzer());
        //通过用户输入内容获取一个Lucene Query
        Query query = parser.parse("Relevant");

        //在IndexSearch上执行查询，并返回前10个文档
        IndexSearcher searcher = new IndexSearcher(reader);
        TopDocs hits = searcher.search(query, 10);
        //遍历结果
        for(int i = 0; i < hits.scoreDocs.length; i ++){
            //检索ScoreDoc,并返回文档标识及其分数（来自底层检索模型）
            ScoreDoc scoreDoc = hits.scoreDocs[i];
            //获取文档,你可以用该文档的ID查看文档字段
            Document document = reader.document(scoreDoc.doc);
            //输出返回文档的标题字段的值
            System.out.println(document.get("title") + " : " + scoreDoc.score);
        }






    }
}
