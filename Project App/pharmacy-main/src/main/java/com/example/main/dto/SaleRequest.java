package com.example.main.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SaleRequest {
    private int quantity;
    private Long pharmacistId;
    private Long medicationId;
}
