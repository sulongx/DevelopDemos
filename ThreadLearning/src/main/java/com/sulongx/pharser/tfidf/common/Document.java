package com.sulongx.pharser.tfidf.common;

import java.util.HashMap;

/**
 * 文档集合，存放一个文档中词汇统计
 * @author Sulongx
 */
public class Document {

    private String fileName;
    private HashMap<String,Word> voc;

    public void init(){
        voc = new HashMap<>();
    }

    public Document(){
        init();
    }


    public void addWord(String word){
        voc.computeIfAbsent(word , k -> new Word(k)).addTf();
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public HashMap<String, Word> getVoc() {
        return voc;
    }

    public void setVoc(HashMap<String, Word> voc) {
        this.voc = voc;
    }
}
