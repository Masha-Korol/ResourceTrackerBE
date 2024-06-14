package com.example.resource_tracker.service.recommendations.brismf;

import com.example.resource_tracker.repository.UserResourceMarkRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class BRISMFService {

    public List<Integer> getTopFiveUserPredictions(Integer userId, List<Integer> resourcesThatUserEstimatedAlready) {
        GradientDescent gradientDescent = train();
        return gradientDescent.getTopFiveUserPredictions(userId, resourcesThatUserEstimatedAlready);
    }

    private GradientDescent train() {
        Double[][] sourceData = new Double[1000][1000];
        // случайным образом заполняем матрицу оценок
        SecureRandom random = new SecureRandom();
        List<Double> possibleMarks = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, null);
        for (int u = 0; u < 1000; u++) {
            for (int i = 0; i < 1000; i++) {
                sourceData[u][i] = possibleMarks.get(random.nextInt(possibleMarks.size()));
            }
        }

        ArrayList<EvaluationEntity> trainingSet = new ArrayList<>();
        ArrayList<EvaluationEntity> validationSet = new ArrayList<>();
        fillTrainingAndValidationSets(sourceData, trainingSet, validationSet);
        GradientDescent gradientDescent = new GradientDescent(trainingSet, validationSet,
                1000, 1000, 3, 0.001,
                0.001, 0.001, 0.001);
        gradientDescent.train();
        return gradientDescent;
    }

    /**
     * Этот метод заполняет trainingSet и validationSet таким образом, что в trainingSet оказываются представлены
     * все юзеры и все товары из sourceData, а в validationSet хранится треть всех известных оценок
     *
     * @param sourceData
     * @param trainingSet
     * @param validationSet
     */
    private static void fillTrainingAndValidationSets(Double[][] sourceData,
                                                      List<EvaluationEntity> trainingSet,
                                                      List<EvaluationEntity> validationSet) {
        HashSet<Integer> usersRepetitions = new HashSet<>();
        HashSet<Integer> itemsRepetitions = new HashSet<>();
        int epoch = 0;
        for (int u = 0; u < sourceData.length; u++) {
            for (int i = 0; i < sourceData[0].length; i++) {
                if (sourceData[u][i] != null) {
                    // на третий раз добавляем элемент в validationSet
                    if (epoch == 3) {
                        // если trainingSet уже содержит хотя бы одну оценку данного юзера и
                        // хотя бы одну оценку данного ресурса,
                        // текущую оценку можно добавлять в validationSet
                        // (т.о. не получится ситуации, когда trainingSet не содержал бы информации о каком-то
                        // юзере или о каком-то ресурсе)
                        if (usersRepetitions.contains(u) && itemsRepetitions.contains(i)) {
                            EvaluationEntity evaluationEntity = new EvaluationEntity(u, i, sourceData[u][i]);
                            validationSet.add(evaluationEntity);
                            epoch = 0;
                        } else {
                            EvaluationEntity evaluationEntity = new EvaluationEntity(u, i, sourceData[u][i]);
                            trainingSet.add(evaluationEntity);
                            // добавляем только что добавленного юзера в сет
                            usersRepetitions.add(u);
                            // добавляем только что добавленный ресурс в сет
                            itemsRepetitions.add(i);
                        }
                    } else {
                        EvaluationEntity evaluationEntity = new EvaluationEntity(u, i, sourceData[u][i]);
                        trainingSet.add(evaluationEntity);
                        // добавляем только что добавленного юзера в сет
                        usersRepetitions.add(u);
                        // добавляем только что добавленный ресурс в сет
                        itemsRepetitions.add(i);
                        epoch++;
                    }
                }
            }
        }
    }
}
