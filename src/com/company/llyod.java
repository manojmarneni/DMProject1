package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.KMeansplusplus.NUMBER_OF_TRIALS;

public class llyod {

    public static final int NUMBER_OF_TRIALS = 100;

    public static void main(String[] args) throws IOException {

        List<Point> pointList = Main.readFile();

        //1st 4 points as centers
        List<Point> initalCenters = new ArrayList<>();
        initalCenters.add(pointList.get(0));
        initalCenters.add(pointList.get(1));
        initalCenters.add(pointList.get(2));
        initalCenters.add(pointList.get(3));

        getMeanCostOfClusters(pointList, initalCenters);



        List<Point> gonzeCenters = Main.getGenzoidCenters(pointList);
        getMeanCostOfClusters(pointList, gonzeCenters);



        //k-means
        double[] cost = new double[NUMBER_OF_TRIALS];
        double[] initcost = new double[NUMBER_OF_TRIALS];

        for (int i = 0; i < NUMBER_OF_TRIALS; i++) {

            List<Point> centers = KMeansplusplus.getKMeansCenters(pointList);
            initcost[i] = Main.get4MinCost(pointList, centers);
            cost[i] = getMeanCostOfClusters(pointList, centers);
        }
        System.out.println(Arrays.toString(initcost));
        System.out.println(Arrays.toString(cost));

    }

    private static double getMeanCostOfClusters(List<Point> pointList, List<Point> initalCenters) {
        List<List<Point>> clusters = Main.getClusters(pointList, initalCenters);
        for (int i = 0; i < 20; i++) {
            initalCenters = getNewCenters(clusters);
            clusters = Main.getClusters(pointList, initalCenters);
//            Main.printPoints(clusters);
        }
        Main.printPoints(clusters);
        ArrayList<List<Point>> centers = new ArrayList<>();
        centers.add(initalCenters);
        Main.printPoints(centers);
        double meanCost = Main.get4MinCost(pointList, initalCenters);
        System.out.println("4-mean cost = "+ meanCost);
        return  meanCost;
    }

    private static List<Point> getNewCenters(List<List<Point>> clusters) {
        List<Point> res = new ArrayList<>();
        for (int i = 0; i < clusters.size(); i++) {
            double xsum = 0;
            double ysum = 0;
            for (Point point : clusters.get(i)) {
                xsum += point.x;
                ysum += point.y;
            }
            res.add(new Point(xsum/clusters.get(i).size(), ysum/clusters.get(i).size()));
        }
        return res;
    }

    private static List<Point> getCenters(List<Point> pointList) {
        return null;
    }
}
