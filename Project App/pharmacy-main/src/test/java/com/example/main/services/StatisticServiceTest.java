package com.example.main.services;

import com.example.main.models.Sale;
import com.example.main.models.Statistic;
import com.example.main.repositories.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StatisticServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private StatisticService statisticService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test

    void getMedicationStatistics() {
        Sale sale1 = new Sale();
        Sale sale2 = new Sale();
        List<Sale> sales = Arrays.asList(sale1, sale2);

        when(saleRepository.findAll()).thenReturn(sales);

        List<Statistic> medicationStatistics = statisticService.getMedicationStatistics();
        assertEquals(statisticService, medicationStatistics.size());
    }

    @Test
    void getTotalQuantitySold() {
        Sale sale1 = new Sale();
        Sale sale2 = new Sale();
        List<Sale> sales = Arrays.asList(sale1, sale2);

        when(saleRepository.findAll()).thenReturn(sales);

        int totalQuantitySold = statisticService.getTotalQuantitySold();

        int expectedTotalQuantitySold = sale1.getQuantity() + sale2.getQuantity();
        assertEquals(expectedTotalQuantitySold, totalQuantitySold);
    }
}