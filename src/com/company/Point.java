package com.company;

import java.util.Arrays;

public class Point {

    String Id;
    String expectedClusterId;
    double[] vector;


    public Point(double[] vector) {
        this.vector = vector;
    }

    public double distance (Point other) {
        int dist  = 0;
        for(int i = 0; i< this.vector.length; i++){
            dist += (other.vector[i] - this.vector[i]) * (other.vector[i] - this.vector[i]);
        }
        return Math.sqrt(dist);
    }

    @Override
    public String toString() {
        return Arrays.toString(this.vector);
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getExpectedClusterId() {
        return expectedClusterId;
    }

    public void setExpectedClusterId(String expectedClusterId) {
        this.expectedClusterId = expectedClusterId;
    }

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }
}
