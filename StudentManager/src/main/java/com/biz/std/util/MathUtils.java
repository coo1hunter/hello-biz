package com.biz.std.util;

import com.biz.std.model.Score;
import com.biz.std.model.Student;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class MathUtils {
    public static BigDecimal avgScore(List<Score> scores){
        BigDecimal avgScore = new BigDecimal(new BigInteger("0"),2);
        if (scores.size() > 0){
            BigDecimal sum = new BigDecimal("0");
            for (Score score: scores) {
                sum = sum.add(score.getScore());
            }
            BigDecimal count = new BigDecimal(String.valueOf(scores.size()));
            avgScore =  sum.divide(count, 2, BigDecimal.ROUND_HALF_UP);
        }
        return avgScore;
    }

    public static BigDecimal gradeAvg(List<Student> students){
        BigDecimal avgScore = new BigDecimal(new BigInteger("0"),2);
        if (students.size()>0) {
            BigDecimal sum = new BigDecimal("0");
            for (Student student : students) {
                List<Score> scores = student.getScores();
                sum = sum.add(avgScore(scores));
            }
            BigDecimal count = new BigDecimal(String.valueOf(students.size()));
            avgScore = sum.divide(count, 2, BigDecimal.ROUND_HALF_UP);
        }
        return avgScore;
    }
}
