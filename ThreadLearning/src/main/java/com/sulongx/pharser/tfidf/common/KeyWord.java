package com.sulongx.pharser.tfidf.common;

/**
 *
 * @author Sulongx
 */
public class KeyWord implements Comparable<KeyWord>{

    private String word;
    private int df;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getDf() {
        return df;
    }

    public void setDf(int df) {
        this.df = df;
    }

    @Override
    public int compareTo(KeyWord o) {
        return Integer.compare(o.getDf(),this.getDf());
    }
}
