package com.example.resource_tracker.service.recommendations.brismf;

import com.example.resource_tracker.repository.UserResourceMarkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BRISMFService {

    public List<Integer> getTopFiveUserPredictions(Integer userId, List<Integer> resourcesThatUserEstimatedAlready) {
        GradientDescent gradientDescent = new GradientDescent();
        gradientDescent.getTopFiveUserPredictions(userId, resourcesThatUserEstimatedAlready);
        return new ArrayList<>();
    }

    public void train() {

    }

    private void fillTrainingAndValidationSets() {

    }
}
