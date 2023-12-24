package com.example.main.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {
    private String medicationName;
    private double dosage;
    private String manufacturer;
    private int quantitySold;
    private String pharmacistName;
}
