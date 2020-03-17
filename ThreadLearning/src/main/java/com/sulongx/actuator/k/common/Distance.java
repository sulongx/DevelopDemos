package com.sulongx.actuator.k.common;

/**
 * @author Sulongx
 */
public class Distance implements Comparable{

    private Integer index;
    private Double distance;

    @Override
    public int compareTo(Object o) {
        if(this.distance < ((Distance)o).distance){
            return -1;
        }else if(this.distance > ((Distance)o).distance){
            return 1;
        }
        return 0;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
