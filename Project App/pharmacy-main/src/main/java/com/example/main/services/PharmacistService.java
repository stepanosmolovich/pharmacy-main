package com.example.main.services;

import com.example.main.models.Pharmacist;
import com.example.main.repositories.PharmacistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacistService {
    private final PharmacistRepository pharmacistRepository;
    public List<Pharmacist> pharmacistsList() {
        return pharmacistRepository.findAll();
    }

    public void savePharmacist(Pharmacist pharmacist){
        log.info("Saving new {}", pharmacist);
        pharmacistRepository.save(pharmacist);
    }

    public void updatePharmacist(Long id, Pharmacist updatedPharmacist) {
        Pharmacist existingPharmacist = pharmacistRepository.findById(id).orElse(null);
        if (existingPharmacist != null) {
            existingPharmacist.setFirstName(updatedPharmacist.getFirstName());
            existingPharmacist.setLastName(updatedPharmacist.getLastName());
            existingPharmacist.setPassportId(updatedPharmacist.getPassportId());
            existingPharmacist.setSalesCount(updatedPharmacist.getSalesCount());
            existingPharmacist.setExperience(updatedPharmacist.getExperience());

            log.info("Updating medication with ID {}: {}", id, existingPharmacist);
            pharmacistRepository.save(existingPharmacist);
        } else {
            log.warn("Medication with ID {} not found. Update operation failed.", id);
        }
    }

    public void deletePharmacist(Long id){
        pharmacistRepository.deleteById(id);
    }
    public Pharmacist getPharmacistById(Long id){
        return pharmacistRepository.findById(id).orElse(null);
    }

}
