package com.example.main.controllers;

import com.example.main.models.Medication;
import com.example.main.models.Pharmacist;
import com.example.main.models.Sale;
import com.example.main.dto.SaleRequest;
import com.example.main.models.Statistic;
import com.example.main.services.MedicationService;
import com.example.main.services.PharmacistService;
import com.example.main.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;
    private final PharmacistService pharmacistService;
    private final MedicationService medicationService;

    @GetMapping("/sales")
    public List<Sale> sales() {
        return saleService.salesList();
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        Sale sale = saleService.getSaleById(id);
        if (sale != null) {
            return ResponseEntity.ok(sale);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/sales/add")
    public ResponseEntity<String> saveSale(@RequestBody SaleRequest saleRequest) {
        Pharmacist pharmacist = pharmacistService.getPharmacistById(saleRequest.getPharmacistId());
        Medication medication = medicationService.getMedicationById(saleRequest.getMedicationId());

        if (pharmacist != null && medication != null) {
            Sale sale = new Sale();
            sale.setQuantity(saleRequest.getQuantity());
            saleService.saveSale(sale, pharmacist, medication);
            return ResponseEntity.ok("Sale added successfully");
        } else {
            return ResponseEntity.badRequest().body("Pharmacist or Medication not found");
        }
    }



    @PutMapping("/sales/update/{id}")
    public ResponseEntity<String> updateSale(@PathVariable Long id, @RequestBody Sale updatedSale) {
        saleService.updateSale(id, updatedSale);
        return ResponseEntity.ok("Medication updated successfully");
    }

    @DeleteMapping("/sales/delete/{id}")
    public ResponseEntity<String> deleteSales(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.ok("Sale deleted successfully");
    }

}
