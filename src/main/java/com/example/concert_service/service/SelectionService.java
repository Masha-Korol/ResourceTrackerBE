package com.example.concert_service.service;

import com.example.concert_service.data.model.Selection;
import com.example.concert_service.repository.SelectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectionService {

    private final SelectionRepository selectionRepository;

    public SelectionService(SelectionRepository selectionRepository) {
        this.selectionRepository = selectionRepository;
    }

    public List<Selection> getAll() {
        return (List<Selection>) selectionRepository.findAll();
    }

    public Selection save(Selection selection) {
        return selectionRepository.save(selection);
    }

    public void delete(Integer id) {
        selectionRepository.delete(selectionRepository.findById(id).get());
    }
}
