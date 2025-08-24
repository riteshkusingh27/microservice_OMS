package com.inventoryservice.dto;


import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class inventoryresponse {
    private String skuCode;
    private boolean isInStock;
}
