package com.example.main.services;

import com.example.main.models.Pharmacist;
import com.example.main.repositories.PharmacistRepository;
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
import static org.mockito.Mockito.*;

class PharmacistServiceTest {
    @Mock
    private PharmacistRepository pharmacistRepository;

    @InjectMocks
    private PharmacistService pharmacistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void pharmacistsList() {
        Pharmacist pharmacist1 = new Pharmacist();
        Pharmacist pharmacist2 = new Pharmacist();
        List<Pharmacist> expectedPharmacists = Arrays.asList(pharmacist1, pharmacist2);

        when(pharmacistRepository.findAll()).thenReturn(expectedPharmacists);

        List<Pharmacist> actualPharmacists = pharmacistService.pharmacistsList();

        assertEquals(expectedPharmacists, actualPharmacists);
    }

    @Test
    void savePharmacist() {
        Pharmacist pharmacistToSave = new Pharmacist();

        pharmacistService.savePharmacist(pharmacistToSave);

        verify(pharmacistRepository, times(1)).save(any(Pharmacist.class));
    }

    @Test
    void updatePharmacist() {
        Long pharmacistId = 1L;
        Pharmacist existingPharmacist = new Pharmacist();
        Pharmacist updatedPharmacist = new Pharmacist();

        Mockito.when(pharmacistRepository.findById(anyLong())).thenReturn(Optional.of(existingPharmacist));

        pharmacistService.updatePharmacist(pharmacistId, updatedPharmacist);

        verify(pharmacistRepository, times(1)).findById(pharmacistId);
        verify(pharmacistRepository, times(1)).save(existingPharmacist);
    }

    @Test
    void updatePharmacistNotFound() {
        Long pharmacistId = 1L;

        Mockito.when(pharmacistRepository.findById(anyLong())).thenReturn(Optional.empty());

        pharmacistService.updatePharmacist(pharmacistId, new Pharmacist());

        verify(pharmacistRepository, times(1)).findById(pharmacistId);
        verify(pharmacistRepository, times(0)).save(Mockito.any());
    }

    @Test
    void deletePharmacist() {
        Long pharmacistId = 1L;

        pharmacistService.deletePharmacist(pharmacistId);

        verify(pharmacistRepository, times(1)).deleteById(pharmacistId);
    }

    @Test
    void getPharmacistById() {
        Long pharmacistId = 1L;
        Pharmacist expectedPharmacist = new Pharmacist();

        Mockito.when(pharmacistRepository.findById(pharmacistId)).thenReturn(Optional.of(expectedPharmacist));

        Pharmacist actualPharmacist = pharmacistService.getPharmacistById(pharmacistId);

        assertEquals(expectedPharmacist, actualPharmacist);
    }

    @Test
    void getPharmacistByIdNotFound() {
        Long pharmacistId = 1L;

        Mockito.when(pharmacistRepository.findById(pharmacistId)).thenReturn(Optional.empty());

        Pharmacist actualPharmacist = pharmacistService.getPharmacistById(pharmacistId);

        assertNull(actualPharmacist);
    }
}