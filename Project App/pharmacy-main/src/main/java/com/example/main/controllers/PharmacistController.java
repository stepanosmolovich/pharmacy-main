package com.example.main.controllers;

import com.example.main.models.Medication;
import com.example.main.services.PharmacistService;
import com.example.main.models.Pharmacist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PharmacistController {
    private final PharmacistService pharmacistService;

    @GetMapping("/pharmacists")
    public List<Pharmacist> pharmacists() {
        return pharmacistService.pharmacistsList();
    }

    @GetMapping("/pharmacists/{id}")
    public ResponseEntity<Pharmacist> getPharmacistById(@PathVariable Long id) {
        Pharmacist pharmacist = pharmacistService.getPharmacistById(id);
        if (pharmacist != null) {
            return ResponseEntity.ok(pharmacist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/pharmacists/add")
    public ResponseEntity<String> addPharmacist(@RequestBody Pharmacist pharmacist) {
        pharmacistService.savePharmacist(pharmacist);
        return ResponseEntity.ok("Pharmacist added successfully");
    }

    @PutMapping("/pharmacists/update/{id}")
    public ResponseEntity<String> updatePharmacist(@PathVariable Long id, @RequestBody Pharmacist updatedPharmacist) {
        pharmacistService.updatePharmacist(id, updatedPharmacist);
        return ResponseEntity.ok("Medication updated successfully");
    }

    @DeleteMapping("/pharmacists/delete/{id}")
    public ResponseEntity<String> deletePharmacist(@PathVariable Long id) {
        pharmacistService.deletePharmacist(id);
        return ResponseEntity.ok("Pharmacist deleted successfully");
    }
}
