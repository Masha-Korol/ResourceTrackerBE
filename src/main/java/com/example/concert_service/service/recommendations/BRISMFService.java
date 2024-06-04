package com.example.concert_service.service.recommendations;

import com.example.concert_service.repository.UserResourceMarkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BRISMFService {

    private final UserResourceMarkRepository userResourceMarkRepository;

    public BRISMFService(UserResourceMarkRepository userResourceMarkRepository) {
        this.userResourceMarkRepository = userResourceMarkRepository;
    }

    public List<Integer> getTopFiveUserPredictions(Integer userId, List<Integer> resourcesThatUserEstimatedAlready) {
        return new ArrayList<>();
    }

    public void train() {

    }

    private void fillTrainingAndValidationSets() {

    }
}
