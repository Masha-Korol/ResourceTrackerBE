package com.example.resource_tracker.controller;

import com.example.resource_tracker.data.model.Selection;
import com.example.resource_tracker.service.SelectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/selections")
public class SelectionController {

    private final SelectionService selectionService;

    public SelectionController(SelectionService selectionService) {
        this.selectionService = selectionService;
    }

    @GetMapping("/")
    public List<SelectionDto> getAll(){
        return selectionService.getAll();
    }

    @PostMapping("/")
    public SelectionDto add(@RequestBody SelectionDto selection) {
        return selectionService.save(selection);
    }

    @PutMapping("/")
    public SelectionDto edit(@RequestBody SelectionDto selection) {
        return selectionService.save(selection);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        selectionService.delete(id);
    }
}
