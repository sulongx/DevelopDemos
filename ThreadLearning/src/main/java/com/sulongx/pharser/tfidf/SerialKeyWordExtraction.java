package com.sulongx.pharser.tfidf;

import com.sulongx.pharser.tfidf.common.Document;
import com.sulongx.pharser.tfidf.common.DocumentParser;
import com.sulongx.pharser.tfidf.common.KeyWord;
import com.sulongx.pharser.tfidf.common.Word;
import com.sun.javaws.security.Resource;

import java.io.File;
import java.util.*;

/**
 * @author Sulongx
 * @version 1.0
 * @date 2020-03-19
 */
public class SerialKeyWordExtraction {

    public static void main(String[] args) {
        Date start,end;
        File source = new File("E:\\workplace_ideal\\DevelopDemos\\ThreadLearning\\src\\main\\data");
        File[] files = source.listFiles();
        HashMap<String, Word> globalVoc = new HashMap<>();
        HashMap<String,Integer> globalKeywords = new HashMap<>();
        int totalCalls = 0;
        int numDocuments = 0;

        start = new Date();
        if(files == null){
            System.err.println("无法读取目录data文件");
        }

        for(File file : files){
            if(file.getName().endsWith(".txt")){
                Document document = DocumentParser.parse(file.getAbsolutePath());
                for(Word word : document.getVoc().values()){
                    globalVoc.merge(word.getWord(),word,Word::merge);
                }
                numDocuments ++;
            }
        }
        System.out.println("包含 " + numDocuments + " 个文档");
        for(File file : files){
            if(file.getName().endsWith(".txt")){
                Document document = DocumentParser.parse(file.getAbsolutePath());
                ArrayList<Word> keyWords = new ArrayList<>(document.getVoc().values());
                int index = 0;
                for(Word word : keyWords){
                    Word globalWord = globalVoc.get(word.getWord());
                    word.setDf(globalWord.getDf(),numDocuments);
                }
                Collections.sort(keyWords);
                int counter = 0;

                for(Word word : keyWords){
                    addKeyword(globalKeywords,word.getWord());
                    totalCalls ++;
                }

                List<KeyWord> orderGlobalKeywords = new ArrayList<>();
                for(Map.Entry<String,Integer> entry : globalKeywords.entrySet()){
                    KeyWord keyWord = new KeyWord();
                    keyWord.setWord(entry.getKey());
                    keyWord.setDf(entry.getValue());
                    orderGlobalKeywords.add(keyWord);
                }
                Collections.sort(orderGlobalKeywords);

                //取前100
                if(orderGlobalKeywords.size() > 100){
                    orderGlobalKeywords = orderGlobalKeywords.subList(0,100);
                }
                for(KeyWord keyWord : orderGlobalKeywords){
                    System.out.println("关键字:" + keyWord.getWord() + " ---- 个数: " + keyWord.getDf() + " -----所在文档：");
                }
            }
        }
        end = new Date();
        System.out.println("执行时间:" + (end.getTime() - start.getTime()));
    }



    private static void addKeyword(Map<String,Integer> globalKeyword,String word){
        globalKeyword.merge(word,1,Integer::sum);
    }
}
