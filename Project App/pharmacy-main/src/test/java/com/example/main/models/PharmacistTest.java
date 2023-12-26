package com.example.main.models;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PharmacistTest {
    @Test
    void testGettersAndSetters() {
        Pharmacist pharmacist = new Pharmacist();
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String passportId = "AB123456";
        int salesCount = 10;
        int experience = 5;

        pharmacist.setId(id);
        pharmacist.setFirstName(firstName);
        pharmacist.setLastName(lastName);
        pharmacist.setPassportId(passportId);
        pharmacist.setSalesCount(salesCount);
        pharmacist.setExperience(experience);

        assertEquals(id, pharmacist.getId());
        assertEquals(firstName, pharmacist.getFirstName());
        assertEquals(lastName, pharmacist.getLastName());
        assertEquals(passportId, pharmacist.getPassportId());
        assertEquals(salesCount, pharmacist.getSalesCount());
        assertEquals(experience, pharmacist.getExperience());
    }

    @Test
    void testSalesAssociation() {
        Pharmacist pharmacist = new Pharmacist();
        Sale sale1 = Mockito.mock(Sale.class);
        Sale sale2 = Mockito.mock(Sale.class);

        List<Sale> sales = new ArrayList<>();
        sales.add(sale1);
        sales.add(sale2);
        pharmacist.setSales(sales);

        assertEquals(2, pharmacist.getSales().size());
        assertTrue(pharmacist.getSales().contains(sale1));
        assertTrue(pharmacist.getSales().contains(sale2));
    }

}