package com.inventoryservice.service;

import com.inventoryservice.dto.inventoryresponse;
import com.inventoryservice.reopositiry.InventiortRepo;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final InventiortRepo inventiortRepo;

    public InventoryService(InventiortRepo inventiortRepo) {
        this.inventiortRepo = inventiortRepo;
    }
     @Transactional(readOnly = true)
    public List<inventoryresponse> isInstock(List<String> skucode){
        // extension method for querying from inventory repo


         // find all the inventory with the skew code in the list
    // return stock information for each sku code
         return   inventiortRepo.findBySkuCodeIn(skucode).stream()
                 .map(inventory ->
                     inventoryresponse.builder()
                             .skuCode(inventory.getSkuCode())
                             .isInStock(inventory.getQuantity() >0)
                             .build()
                 )
                 .toList();
         // unmodified list
       }
}
