package com.example.main.services;

import com.example.main.models.Medication;
import com.example.main.models.Pharmacist;
import com.example.main.models.Sale;
import com.example.main.repositories.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private MedicationService medicationService;

    @Mock
    private PharmacistService pharmacistService;

    @InjectMocks
    private SaleService saleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salesList() {
        Sale sale1 = new Sale();
        Sale sale2 = new Sale();
        List<Sale> expectedSales = Arrays.asList(sale1, sale2);

        when(saleRepository.findAll()).thenReturn(expectedSales);
        List<Sale> actualSales = saleService.salesList();
        assertEquals(expectedSales, actualSales);
    }

    @Test
    void getSaleById() {
        Long saleId = 1L;
        Sale expectedSale = new Sale(/* set sale properties */);

        when(saleRepository.findById(saleId)).thenReturn(Optional.of(expectedSale));
        Sale actualSale = saleService.getSaleById(saleId);
        assertEquals(expectedSale, actualSale);
    }

    @Test
    void saveSale() {
        Sale saleToSave = new Sale();
        Pharmacist pharmacist = new Pharmacist();
        Medication medication = new Medication();

        when(medicationService.getMedicationById(anyLong())).thenReturn(medication);
        when(pharmacistService.getPharmacistById(anyLong())).thenReturn(pharmacist);

        saleService.saveSale(saleToSave, pharmacist, medication);

        verify(saleRepository, times(1)).save(saleToSave);
        assertEquals(medication.getQuantity() - saleToSave.getQuantity(), saleToSave.getMedication().getQuantity());
        assertEquals(pharmacist.getSalesCount() + saleToSave.getQuantity(), saleToSave.getPharmacist().getSalesCount());
    }

    @Test
    void updateSale() {
        Long saleId = 1L;
        Sale existingSale = new Sale();
        Sale updatedSale = new Sale();

        when(saleRepository.findById(anyLong())).thenReturn(Optional.of(existingSale));
        saleService.updateSale(saleId, updatedSale);

        verify(saleRepository, times(1)).save(existingSale);
        assertEquals(updatedSale.getMedication(), existingSale.getMedication());
        assertEquals(updatedSale.getPharmacist(), existingSale.getPharmacist());
        assertEquals(updatedSale.getQuantity(), existingSale.getQuantity());
    }

    @Test
    void updateSaleNotFound() {
        Long saleId = 1L;

        when(saleRepository.findById(anyLong())).thenReturn(Optional.empty());
        saleService.updateSale(saleId, new Sale());
        verify(saleRepository, times(0)).save(Mockito.any());
    }

    @Test
    void deleteSale() {
        Long saleId = 1L;

        saleService.deleteSale(saleId);
        verify(saleRepository, times(1)).deleteById(saleId);
    }
}