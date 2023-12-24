package com.example.main.services;

import com.example.main.dto.SaleRequest;
import com.example.main.models.Medication;
import com.example.main.models.Pharmacist;
import com.example.main.models.Sale;
import com.example.main.repositories.MedicationRepository;
import com.example.main.repositories.PharmacistRepository;
import com.example.main.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final MedicationService medicationService;
    private final PharmacistService pharmacistService;

    public List<Sale> salesList() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public void saveSale(Sale sale, Pharmacist pharmacist, Medication medication) {
        log.info("Saving new sale");

        if (pharmacist != null && medication != null) {
            // Отримуємо поточну кількість препарату
            int currentQuantity = medication.getQuantity();

            // Перевіряємо, чи можна віднімати вказану кількість
            if (currentQuantity >= sale.getQuantity()) {
                // Змінюємо кількість препарату
                medication.setQuantity(currentQuantity - sale.getQuantity());

                // Змінюємо фармацевта
                pharmacist.setSalesCount(pharmacist.getSalesCount() + sale.getQuantity());

                // Зберігаємо зміни у базі даних
                sale.setPharmacist(pharmacist);
                sale.setMedication(medication);
                saleRepository.save(sale);
            } else {
                log.warn("Not enough quantity of medication. Sale not saved.");
            }
        } else {
            log.warn("Pharmacist or medication is null. Sale not saved.");
        }
    }

    public void updateSale(Long id, Sale updatedSale) {
        Sale existingSale = saleRepository.findById(id).orElse(null);
        if (existingSale != null) {
            existingSale.setMedication(updatedSale.getMedication());
            existingSale.setPharmacist(updatedSale.getPharmacist());
            existingSale.setQuantity(updatedSale.getQuantity());

            saleRepository.save(existingSale);
            log.info("Updated sale with id {}", id);
        } else {
            log.warn("Sale with id {} not found. Update failed.", id);
        }
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}
