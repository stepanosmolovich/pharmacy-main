package com.example.main.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "medication_id")
    @JsonIgnore
    private Medication medication;
    @ManyToOne
    @JoinColumn(name = "pharmacist_id")
    @JsonIgnore
    private Pharmacist pharmacist;
    @Column(name = "quantity")
    private int quantity;
}
