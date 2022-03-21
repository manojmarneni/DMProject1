package com.company;

import com.company.algo.Clustering;
import com.company.util.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KMeansplusplus {

    public static final int NUMBER_OF_TRIALS = 20;

    public static void main(String[] args) throws IOException {
        List<Point> pointList = Parser.getPoints();

        double[] cost = new double[NUMBER_OF_TRIALS];

        for (int i = 0; i < NUMBER_OF_TRIALS; i++) {
            cost[i] = getMeanCost(pointList);
        }

        System.out.println(Arrays.toString(cost));

    }

    static double getMeanCost(List<Point> pointList) {
        List<Point> centers = getKMeansCenters(pointList);
        return Main.get4MinCost(pointList, centers);
    }

    static List<Point> getKMeansCenters(List<Point> pointList) {
        Random random = new Random();
        int c1 = random.nextInt(pointList.size());

        List<Point> centers = new ArrayList<>();
        centers.add(pointList.get(c1));
        pointList.remove(c1);

        double total  = 0;
        for (int k = 1; k < 4; k++) {
            double[] probs = new double[pointList.size()];
            for (int i = 0; i < pointList.size(); i++) {
                double closestCenterDistance = Clustering.getClosestCenterDistance(centers, pointList.get(i));
                probs[i] = closestCenterDistance * closestCenterDistance;
                total += probs[i];
            }

            int nextCenter = getNextCenterIndex(normalise(probs, total));
            centers.add(pointList.get(nextCenter));
            pointList.remove(nextCenter);
        }
        return centers;
    }

    private static int getNextCenterIndex(double[] normalise) {

        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < normalise.length; i++) {
            for (int j = 0; j < normalise[i] * 100; j++) {
                nums.add(i);
            }
        }
        Random random = new Random();
        return nums.get(random.nextInt(nums.size()));
    }

    private static double[] normalise(double[] probs, double total) {
        for (int i = 0; i < probs.length; i++) {
            probs[i] = probs[i]/total;
        }
        return probs;
    }
}
