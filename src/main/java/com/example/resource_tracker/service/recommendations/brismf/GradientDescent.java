package com.example.resource_tracker.service.recommendations.brismf;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.sqrt;

public class GradientDescent {

    private List<EvaluationEntity> trainingSet;
    private List<EvaluationEntity> validationSet;
    private Double[][] p;
    private Double[][] q;
    private int numberOfUsers;
    private int numberOfItems;
    private int numberOfCriteria;
    // первые настраиваемые параметры
    private double zp;
    private double zq;
    // вторые настраиваемые параметры
    private double lp;
    private double lq;
    // output
    private Double[][] pResult;
    private Double[][] qResult;

    public GradientDescent(List<EvaluationEntity> trainingSet,
                           List<EvaluationEntity> validationSet,
                           int numberOfUsers,
                           int numberOfItems,
                           int numberOfCriteria,
                           double zp,
                           double zq,
                           double lp,
                           double lq) {
        this.trainingSet = trainingSet;
        this.validationSet = validationSet;
        this.numberOfUsers = numberOfUsers;
        this.numberOfItems = numberOfItems;
        this.numberOfCriteria = numberOfCriteria;
        this.zp = zp;
        this.zq = zq;
        this.lp = lp;
        this.lq = lq;
    }

    private void fillPDefault() {
        p = new Double[numberOfUsers][numberOfCriteria];
        for (int i = 0; i < numberOfUsers; i++) {
            for (int j = 0; j < numberOfCriteria; j++) {
                // первой колонке присваиваем значение 1
                if (j == 0) {
                    p[i][j] = 1.0;
                } else {
                    p[i][j] = (Math.random() * 0.02) - 0.01;
                }
            }
        }
    }

    private void fillQDefault() {
        q = new Double[numberOfCriteria][numberOfItems];
        for (int i = 0; i < numberOfCriteria; i++) {
            for (int j = 0; j < numberOfItems; j++) {
                // второму ряду присваиваем значение 1
                if (i == 1) {
                    q[i][j] = 1.0;
                } else {
                    q[i][j] = (Math.random() * 0.02) - 0.01;
                }
            }
        }
    }

    public void train() {
        fillPDefault();
        fillQDefault();
        int numberOfTimesWhenRMSEDidNotDecrease = 0;
        Double prevRMSE = null;
        while (numberOfTimesWhenRMSEDidNotDecrease < 2) {
            // iterate over each element from training set
            for (EvaluationEntity evaluationEntity : trainingSet) {
                int u = evaluationEntity.getUserId();
                int i = evaluationEntity.getResourceId();
                double error = calculateError(evaluationEntity);
                for (int k = 2; k < numberOfCriteria; k++) {
                    double pukOld = p[u][k];
                    p[u][k] += zp * (error * q[k][i] - lp * p[u][k]);
                    q[k][i] += zq * (error * pukOld - lq * q[k][i]);
                }
            }
            double RMSE = calculateRMSE();
            // если это первый период
            if (prevRMSE == null) {
                prevRMSE = RMSE;
                pResult = p;
                qResult = q;
            } else {
                // если новый RMSE лучше предыдущего
                if (RMSE < prevRMSE) {
                    pResult = p;
                    qResult = q;
                    prevRMSE = RMSE;
                    System.out.println("RMSE = " + RMSE);
                } else {
                    numberOfTimesWhenRMSEDidNotDecrease++;
                }
            }
        }
    }

    // over validation set
    public double calculateRMSE() {
        double SSE = 0;
        for (EvaluationEntity evaluationEntity : validationSet) {
            int u = evaluationEntity.getUserId();
            int i = evaluationEntity.getResourceId();
            double pq = 0;
            for (int k = 0; k < numberOfCriteria; k++) {
                pq += p[u][k] * q[k][i];
            }
            SSE += (evaluationEntity.getMark() - pq) * (evaluationEntity.getMark() - pq);
        }
        return sqrt(SSE / (numberOfUsers * numberOfItems));
    }

    // calculation of training error
    private double calculateError(EvaluationEntity evaluationEntity) {
        double ruiNew = 0;
        for (int k = 0; k < numberOfCriteria; k++) {
            ruiNew += p[evaluationEntity.getUserId()][k] *
                    q[k][evaluationEntity.getResourceId()];
        }
        return evaluationEntity.getMark() - ruiNew;
    }

    /**
     * Этот метод возвращает 5 ресурсов, которые понравятся пользователю с заданным айди
     * с наибольшей вероятностью
     *
     * @param u - айди заданного пользователя
     * @return
     */
    public List<Integer> getTopFiveUserPredictions(int u, List<Integer> knownItemMarks) {
        HashMap<Integer, Double> unknownItemMarks = new HashMap<>();
        for (int i = 0; i < numberOfItems; i++) {
            // если оценка пользователем текущего товара неизвестна, вычисляем ее
            if (!knownItemMarks.contains(i)) {
                double currentMark = 0;
                for (int k = 0; k < numberOfCriteria; k++) {
                    currentMark += pResult[u][k] * qResult[k][i];
                }
                unknownItemMarks.put(i, currentMark);
            }
        }
        Map<Integer, Double> collect = unknownItemMarks.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new ArrayList<>(collect.keySet());
    }
}
