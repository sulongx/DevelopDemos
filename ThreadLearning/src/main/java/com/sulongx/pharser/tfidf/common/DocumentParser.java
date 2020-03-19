package com.sulongx.pharser.tfidf.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;

/**
 * @author Sulongx
 */
public class DocumentParser {

    /***
     * 统计指定文档中词汇
     * @param filePath
     * @return
     */
    public static Document parse(String filePath){
        Document document = new Document();
        Path path = Paths.get(filePath);
        document.setFileName(path.toString());

        try(
                BufferedReader reader = Files.newBufferedReader(path);
        ) {
            for(String line : Files.readAllLines(path)){
                parseLine(line,document);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return document;
    }



    private static void parseLine(String line,Document document){
        line = Normalizer.normalize(line, Normalizer.Form.NFKD);
        line = line.replaceAll("[^\\p{ASCII}]","");
        line = line.toLowerCase();
        for(String word : line.split("\\w+")){
            document.addWord(word);
        }
    }
}
