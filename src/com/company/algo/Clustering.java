package com.company.algo;

import com.company.Point;

import java.util.ArrayList;
import java.util.List;

public class Clustering {

    public static List<List<Point>> getClusters(List<Point> pointList, List<Point> centers) {
        List<List<Point>> res = new ArrayList<>();
        for (int i = 0; i < centers.size(); i++) {
            res.add(new ArrayList<>());
        }

        for (Point point : pointList) {
            int minDistIndex = -1;
            double minDistSoFar = Double.MAX_VALUE;
            for (int j = 0; j < centers.size(); j++) {
                double distanceToCenter = point.distance(centers.get(j));
                if (minDistSoFar > distanceToCenter) {
                    minDistSoFar = distanceToCenter;
                    minDistIndex = j;
                }
            }
            res.get(minDistIndex).add(point);
        }

        return res;
    }

    public static double getClosestCenterDistance(List<Point> centers, Point temp) {
        double minDistSoFar = Double.MAX_VALUE;
        for (Point center : centers) {
            minDistSoFar = Math.min(minDistSoFar, temp.distance(center));
        }
        return minDistSoFar;
    }

    public static void printCluster(List<List<Point>> clusters) {
        for (List<Point> cluster : clusters) {
            System.out.println("----- ");
            for (Point point : cluster) {
                System.out.println(point.getExpectedClusterId());
            }
            System.out.println("----- ");
        }
    }
}
