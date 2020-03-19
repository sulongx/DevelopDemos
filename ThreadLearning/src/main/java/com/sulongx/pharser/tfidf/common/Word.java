package com.sulongx.pharser.tfidf.common;

/**
 * 存放某个单词的信息
 * @author Sulongx
 */
public class Word implements Comparable<Word> {

    /**
     * 单词内容
     */
    private String word;
    /**
     * 此单词在某个文档中出现的次数
     */
    private int tf;
    /**
     * 含有这个单词的文档的数量
     */
    private int df;
    /**
     * TF-IDF 值
     */
    private double tfIdf;

    public void init(){
        df = 1;
    }

    public Word(String word){
        this.word = word;
        init();
    }


    public void addTf(){
        this.tf ++;
    }


    public Word merge(Word word){
        this.tf += word.getTf();
        this.df += word.getDf();
        return this;
    }


    public void setDf(int df,int N){
        this.df = df;
        this.tfIdf = tf * Math.log(Double.valueOf(N) / df);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    public int getDf() {
        return df;
    }

    public void setDf(int df) {
        this.df = df;
    }

    public double getTfIdf() {
        return tfIdf;
    }

    public void setTfIdf(double tfIdf) {
        this.tfIdf = tfIdf;
    }

    @Override
    public int compareTo(Word o) {
        return Double.compare(o.getTfIdf(),this.getTfIdf());
    }
}
