package com.example.main.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pharmacist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pharmacist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "passport_id", unique = true)
    private String passportId;
    @Column(name = "salesCount")
    private int salesCount;
    @Column(name = "experience")
    private int experience;

    @OneToMany(mappedBy = "pharmacist")
    private List<Sale> sales;

}
