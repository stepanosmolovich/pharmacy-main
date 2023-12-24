package com.example.main.services;
import com.example.main.models.Sale;
import com.example.main.models.Statistic;
import com.example.main.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final SaleRepository saleRepository;
    public List<Statistic> getMedicationStatistics() {
        return saleRepository.findAll().stream()
                .map(sale -> new Statistic(
                        sale.getMedication().getName(),
                        sale.getMedication().getDosage(),
                        sale.getMedication().getManufacturer(),
                        sale.getQuantity(),
                        sale.getPharmacist().getFirstName() + " " + sale.getPharmacist().getLastName()
                ))
                .collect(Collectors.toList());
    }
    public int getTotalQuantitySold() {
        return saleRepository.findAll().stream()
                .mapToInt(Sale::getQuantity)
                .sum();
    }

}
