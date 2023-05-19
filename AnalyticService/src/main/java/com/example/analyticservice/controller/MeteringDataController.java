package com.example.analyticservice.controller;

import com.example.analyticservice.model.MeteringData;
import com.example.analyticservice.service.MeteringDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/get")
public class MeteringDataController {

    private final MeteringDataService meteringDataService;

    @Autowired
    public MeteringDataController(MeteringDataService meteringDataService) {
        this.meteringDataService = meteringDataService;
    }

    @GetMapping("/{groupBy}")
    public void getMeteringDataGroupedBy(
            @PathVariable("groupBy") String groupBy) {

    }
}
