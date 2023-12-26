package com.example.main.services;

import com.example.main.models.Medication;
import com.example.main.repositories.MedicationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MedicationServiceTest {
    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private MedicationService medicationService;

    @Test
    void medicationList() {

        Medication medication1 = new Medication();
        Medication medication2 = new Medication();
        List<Medication> expectedMedications = Arrays.asList(medication1, medication2);

        Mockito.when(medicationRepository.findAll()).thenReturn(expectedMedications);


        List<Medication> actualMedications = medicationService.medicationList();


        assertEquals(expectedMedications, actualMedications);
    }

    @Test
    void getMedicationById() {

        Long medicationId = 1L;
        Medication expectedMedication = new Medication();

        Mockito.when(medicationRepository.findById(medicationId)).thenReturn(Optional.of(expectedMedication));

        Medication actualMedication = medicationService.getMedicationById(medicationId);

        assertEquals(expectedMedication, actualMedication);
    }

    @Test
    void getMedicationByIdNotFound() {
        Long medicationId = 1L;

        Mockito.when(medicationRepository.findById(medicationId)).thenReturn(Optional.empty());

        Medication actualMedication = medicationService.getMedicationById(medicationId);

        assertNull(actualMedication);
    }

    @Test
    void saveMedication() {

        Medication medicationToSave = new Medication();
        medicationService.saveMedication(medicationToSave);

        verify(medicationRepository, times(1)).save(any(Medication.class));
    }

    @Test
    void updateMedication() {
        Long medicationId = 1L;
        Medication existingMedication = new Medication();
        Medication updatedMedication = new Medication();

        Mockito.when(medicationRepository.findById(anyLong())).thenReturn(Optional.of(existingMedication));

        medicationService.updateMedication(medicationId, updatedMedication);

        verify(medicationRepository, times(1)).findById(medicationId);
        verify(medicationRepository, times(1)).save(existingMedication);
    }

    @Test
    void updateMedicationNotFound() {
        Long medicationId = 1L;

        Mockito.when(medicationRepository.findById(anyLong())).thenReturn(Optional.empty());

        medicationService.updateMedication(medicationId, new Medication());

        verify(medicationRepository, times(1)).findById(medicationId);
        verify(medicationRepository, times(0)).save(Mockito.any());
    }

    @Test
    void deleteMedication() {
        Long medicationId = 1L;

        medicationService.deleteMedication(medicationId);

        verify(medicationRepository, times(1)).deleteById(medicationId);
    }

    @Test
    void deleteMedicationNotFound() {
        Long medicationId = 1L;

        Mockito.doThrow(new EmptyResultDataAccessException(1)).when(medicationRepository).deleteById(anyLong());

        assertThrows(EmptyResultDataAccessException.class, () -> medicationService.deleteMedication(medicationId));

        verify(medicationRepository, times(1)).deleteById(medicationId);
    }
}