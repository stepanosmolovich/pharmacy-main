package com.example.main.controllers;

import com.example.main.models.Statistic;
import com.example.main.services.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/statistic")
    public List<Statistic> getMedicationStatistics() {
        return statisticService.getMedicationStatistics();
    }
    @GetMapping("/totalQuantitySold")
    public int getTotalQuantitySold() {
        return statisticService.getTotalQuantitySold();
    }
}
