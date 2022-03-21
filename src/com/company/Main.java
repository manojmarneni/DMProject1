package com.company;


import com.company.algo.Clustering;
import com.company.algo.Gonzalez;
import com.company.util.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static final int NUMBER_OF_CLUSTERS = 10;

    public static void main(String[] args) throws IOException {

        //read into List of points
        List<Point> pointList = Parser.getPoints();

        List<Point> centers = Gonzalez.getCenters(pointList, NUMBER_OF_CLUSTERS);

        List<List<Point>> clusters = Clustering.getClusters(pointList, centers);

        Clustering.printCluster(clusters);

        double cost = get4MinCost(pointList, centers);
        System.out.println("Cost of clusters = " + cost);
    }


    static double get4MinCost(List<Point> pointList, List<Point> centers) {
        double max4center = Double.MIN_VALUE;
        double mean4Cost = 0;
        for (Point point : pointList) {
            double closestCenterDistance = Clustering.getClosestCenterDistance(centers, point);
            mean4Cost += (closestCenterDistance * closestCenterDistance);
            max4center = Math.max(max4center, closestCenterDistance);
        }
        mean4Cost = mean4Cost / pointList.size();
        System.out.println("The 4-center cost = " + max4center);
        mean4Cost = Math.sqrt(mean4Cost);
        System.out.println("The 4-mean cost = " + mean4Cost);
        return mean4Cost;
    }

}
