package com.company;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {

    public static final int NUMBER_OF_CLUSTERS = 4;

    public static void main(String[] args) throws IOException {


        //read into List of points
        List<Point> pointList = readFile();

        List<Point> centers = getGenzoidCenters(pointList);

        for (int i = 0; i < NUMBER_OF_CLUSTERS; i++) {
            System.out.println(centers.get(i));
        }

        get4MinCost(pointList, centers);
        List<List<Point>> clusters = getClusters(pointList, centers);

        printPoints(clusters);

        List<List<Point>> arrayList = new ArrayList<>();
        arrayList.add(centers);
        printPoints(arrayList);
    }

    static List<Point> getGenzoidCenters(List<Point> pointList) {
        List<Point> centers = new ArrayList<>();
        centers.add(pointList.get(0));
        pointList.remove(0);

        for (int i = 1; i < NUMBER_OF_CLUSTERS; i++) {
            int k = getIndexOfMaxDistance(centers, pointList);
            centers.add(pointList.get(k));
            pointList.remove(k);
        }
        return centers;
    }

    static double get4MinCost(List<Point> pointList, List<Point> centers) {
        double max4center = Double.MIN_VALUE;
        double mean4Cost = 0;
        for (int i = 0; i < pointList.size(); i++) {
            double closestCenterDistance = getClosestCenterDistance(centers, pointList.get(i));
            mean4Cost += (closestCenterDistance * closestCenterDistance);
            max4center = Math.max(max4center, closestCenterDistance);
        }
        mean4Cost = mean4Cost / pointList.size();
        System.out.println("The 4-center cost = " + max4center);
        mean4Cost = Math.sqrt(mean4Cost);
        System.out.println("The 4-mean cost = " + mean4Cost);
        return mean4Cost;
    }

    static void printPoints(List<List<Point>> clusters) {
        int k = 0;
        for (List<Point> cluster : clusters) {
            double[] xpoints = new double[cluster.size()];
            double[] ypoints = new double[cluster.size()];
            int i = 0;
            for (Point point : cluster) {
                xpoints[i] = point.x;
                ypoints[i] = point.y;
                i++;
            }
            System.out.println("x"+k+"="+Arrays.toString(xpoints));
            System.out.println("y"+k+"="+Arrays.toString(ypoints));
            k++;
        }
    }

    static List<List<Point>> getClusters(List<Point> pointList, List<Point> centers) {
        List<List<Point>> res = new ArrayList<>();
        for (int i = 0; i < centers.size(); i++) {
            res.add(new ArrayList<>());
        }

        for (int i = 0; i < pointList.size(); i++) {
            int minDistIndex = -1;
            double minDistSoFar = Double.MAX_VALUE;
            for (int j = 0; j < centers.size(); j++) {
                double distanceToCenter = pointList.get(i).distance(centers.get(j));
                if (minDistSoFar > distanceToCenter) {
                    minDistSoFar = distanceToCenter;
                    minDistIndex = j;
                }
            }
            res.get(minDistIndex).add(pointList.get(i));
        }

        return res;
    }

    static List<Point> readFile() throws IOException {
        List<Point> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/u1320407/Downloads/C2.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                String[] str = line.split(" ");
                list.add(new Point(Double.parseDouble(str[0]), Double.parseDouble(str[1])));
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
        }
        return list;
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
        for (int j = 0; j < centers.size(); j++) {
            minDistSoFar = Math.min(minDistSoFar, temp.distance(centers.get(j)));
        }
        return minDistSoFar;
    }
}
