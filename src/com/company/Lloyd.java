package com.company;

import com.company.algo.Clustering;
import com.company.util.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lloyd {

    public static final int NUMBER_OF_TRIALS = 1;

    public static void main(String[] args) throws IOException {

        List<Point> pointList = Parser.getPoints();

//        //k-means
//        double[] cost = new double[NUMBER_OF_TRIALS];
//        double[] initcost = new double[NUMBER_OF_TRIALS];
//
//        for (int i = 0; i < NUMBER_OF_TRIALS; i++) {
//
//            List<Point> centers = KMeansplusplus.getKMeansCenters(pointList, 10);
//            initcost[i] = Main.get4MinCost(pointList, centers);
//            cost[i] = getMeanCostOfClusters(pointList, centers);
//
//        }
//
//        System.out.println(Arrays.toString(initcost));
//        System.out.println(Arrays.toString(cost));
        List<Point> initialCenters = KMeansplusplus.getKMeansCenters(pointList, 10);
        List<Point> centers = getCenters(pointList, initialCenters);
        List<List<Point>> clusters = Clustering.getClusters(pointList, centers);
        Clustering.printCluster(clusters);
        double meanCost = Main.get4MinCost(pointList, centers);
        System.out.println("4-mean cost = "+ meanCost);

    }

    public static List<Point> getCenters(List<Point> pointList, List<Point> initalCenters){
        List<List<Point>> clusters = Clustering.getClusters(pointList, initalCenters);

        for (int i = 0; i < 20; i++) {
            initalCenters = getNewCenters(clusters);
            clusters = Clustering.getClusters(pointList, initalCenters);
        }
        return initalCenters;
    }

    private static double getMeanCostOfClusters(List<Point> pointList, List<Point> initalCenters) {
        List<List<Point>> clusters = Clustering.getClusters(pointList, initalCenters);

        for (int i = 0; i < 20; i++) {
            initalCenters = getNewCenters(clusters);
            clusters = Clustering.getClusters(pointList, initalCenters);
        }

        Clustering.printCluster(clusters);
        double meanCost = Main.get4MinCost(pointList, initalCenters);
        System.out.println("4-mean cost = "+ meanCost);
        return  meanCost;
    }

    private static List<Point> getNewCenters(List<List<Point>> clusters) {
        List<Point> res = new ArrayList<>();
        for (List<Point> cluster : clusters) {
            int length = clusters.get(0).get(0).vector.length;
            double[] nc = new double[length];

            for (int j = 0; j < length; j++) {
                for (Point point : cluster) {
                    nc[j] += point.vector[j];
                }
                nc[j] = nc[j] / length;
            }
            res.add(new Point(nc));
        }
        return res;
    }
}
