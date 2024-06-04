package com.example.resource_tracker.service;

import com.example.resource_tracker.data.model.Selection;
import com.example.resource_tracker.repository.SelectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectionService {

    private final SelectionRepository selectionRepository;

    public SelectionService(SelectionRepository selectionRepository) {
        this.selectionRepository = selectionRepository;
    }

    public List<SelectionDto> getAll() {
        return (List<SelectionDto>) selectionRepository.findAll();
    }

    public SelectionDto save(SelectionDto selection) {
        return selectionRepository.save(selection);
    }

    public void delete(Integer id) {
        selectionRepository.delete(selectionRepository.findById(id).get());
    }
}
