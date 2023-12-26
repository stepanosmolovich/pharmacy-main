package com.example.main.models;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MedicationTest {
    @Test
    void testGettersAndSetters() {
        Medication medication = new Medication();
        Long id = 1L;
        String manufacturer = "Manufacturer";
        String name = "Medicine";
        double dosage = 10.5;
        LocalDate manufactureDate = LocalDate.of(2022, 1, 1);
        LocalDate shelfLife = LocalDate.of(2023, 1, 1);
        int quantity = 100;

        medication.setId(id);
        medication.setManufacturer(manufacturer);
        medication.setName(name);
        medication.setDosage(dosage);
        medication.setManufactureDate(manufactureDate);
        medication.setShelfLife(shelfLife);
        medication.setQuantity(quantity);

        assertEquals(id, medication.getId());
        assertEquals(manufacturer, medication.getManufacturer());
        assertEquals(name, medication.getName());
        assertEquals(dosage, medication.getDosage());
        assertEquals(manufactureDate, medication.getManufactureDate());
        assertEquals(shelfLife, medication.getShelfLife());
        assertEquals(quantity, medication.getQuantity());
    }

    @Test
    void testSalesAssociation() {
        Medication medication = new Medication();
        Sale sale1 = Mockito.mock(Sale.class);
        Sale sale2 = Mockito.mock(Sale.class);

        List<Sale> sales = new ArrayList<>();
        sales.add(sale1);
        sales.add(sale2);
        medication.setSales(sales);

        assertEquals(2, medication.getSales().size());
        assertTrue(medication.getSales().contains(sale1));
        assertTrue(medication.getSales().contains(sale2));
    }
}