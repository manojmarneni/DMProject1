package com.company.util;

import com.company.Point;
import com.company.vo.Vocabulary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static final String DEFAULT_FILE_LOCATION = "";

    public static List<Point> getPoints() throws IOException {
        return getPoints(DEFAULT_FILE_LOCATION);
    }


    public static List<Point> getPoints(String fileName) throws IOException {
        List<String> lines = parseFile(fileName);
        Vocabulary vocabulary = getVocabulary(lines);
        return getPoints(lines, vocabulary);
    }


    public static List<String> parseFile(String fileName) throws IOException {
        List<String> res = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                res.add(line);
                line = br.readLine();
            }
        }
        return res;
    }

    public static List<Point> getPoints(List<String> strings, Vocabulary vocabulary) {
        List<Point> res = new ArrayList<>();
        for (String str : strings) {
            res.add(getPoint(str, vocabulary));
        }
        return res;
    }

    private static Point getPoint(String str, Vocabulary vocabulary) {
        double[] vector = new double[vocabulary.getNumberOfWords()];
        Point p = new Point(vector);
        String[] st = str.split(" ");
        p.setExpectedClusterId(st[0]);

        for (int i = 1; i < st.length; i++) {
            vector[vocabulary.getWordIndex().get(st[i])] += 1;
        }
        return p;
    }

    public static Vocabulary getVocabulary(List<String> strings) {
        Vocabulary vocabulary = new Vocabulary();

        int i = 0;
        for (String record : strings) {
            for (String str : record.split(" ")) {
                if (!vocabulary.getWordIndex().containsKey(str)) {
                    vocabulary.getWordIndex().put(str, i++);
                }
            }
        }
        vocabulary.setNumberOfWords(i);
        return vocabulary;
    }
}
