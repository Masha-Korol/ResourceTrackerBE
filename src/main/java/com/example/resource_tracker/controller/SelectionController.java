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
    public List<Selection> getAll(){
        return selectionService.getAll();
    }

    @PostMapping("/")
    public Selection add(@RequestBody Selection selection) {
        return selectionService.save(selection);
    }

    @PutMapping("/")
    public Selection edit(@RequestBody Selection selection) {
        return selectionService.save(selection);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        selectionService.delete(id);
    }
}
