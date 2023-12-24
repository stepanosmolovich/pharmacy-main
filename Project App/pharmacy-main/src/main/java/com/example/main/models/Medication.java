package com.example.main.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "medication")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "name")
    private String name;
    @Column(name = "dosage")
    private double dosage;
    @Column(name = "manufactureDate")
    private LocalDate manufactureDate;
    @Column(name = "shelf_life")
    private LocalDate shelfLife;
    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "medication")
    private List<Sale> sales;

}
