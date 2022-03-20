package com.company.algo;

import com.company.Point;

import java.util.ArrayList;
import java.util.List;

public class Gonzalez {

    public static final int NUMBER_OF_DEFAULT_CLUSTERS = 5;

    // With initial center as first center
    public static List<Point> getCenters(List<Point> pointList, int numberOfClusters){
        List<Point> centers = new ArrayList<>();
        centers.add(pointList.get(0));
        pointList.remove(0);

        for (int i = 1; i < numberOfClusters; i++) {
            int k = getIndexOfMaxDistance(centers, pointList);
            centers.add(pointList.get(k));
            pointList.remove(k);
        }
        return centers;
    }

    public static List<Point> getCenters(List<Point> pointList){
        return getCenters(pointList, NUMBER_OF_DEFAULT_CLUSTERS);
    }

    private static int getIndexOfMaxDistance(List<Point> centers, List<Point> pointList) {
        int maxCenterIndex = -1;

        double maxDistSoFar = Double.MIN_VALUE;
        for (int i = 0; i < pointList.size(); i++) {
            double closestCenterDistance = getClosestCenterDistance(centers, pointList.get(i));
            if (maxDistSoFar < closestCenterDistance) {
                maxDistSoFar = closestCenterDistance;
                maxCenterIndex = i;
            }
        }
        return maxCenterIndex;
    }

    static double getClosestCenterDistance(List<Point> centers, Point temp) {
        double minDistSoFar = Double.MAX_VALUE;
        for (Point center : centers) {
            minDistSoFar = Math.min(minDistSoFar, temp.distance(center));
        }
        return minDistSoFar;
    }
}
