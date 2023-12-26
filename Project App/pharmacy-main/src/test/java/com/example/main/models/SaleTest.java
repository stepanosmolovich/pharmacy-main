package com.example.main.models;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SaleTest {
    @Test
    void testGettersAndSetters() {
        Sale sale = new Sale();
        Long id = 1L;
        Medication medication = Mockito.mock(Medication.class);
        Pharmacist pharmacist = Mockito.mock(Pharmacist.class);
        int quantity = 5;

        sale.setId(id);
        sale.setMedication(medication);
        sale.setPharmacist(pharmacist);
        sale.setQuantity(quantity);

        assertEquals(id, sale.getId());
        assertEquals(medication, sale.getMedication());
        assertEquals(pharmacist, sale.getPharmacist());
        assertEquals(quantity, sale.getQuantity());
    }

}