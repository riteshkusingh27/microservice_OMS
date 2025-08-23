package com.inventoryservice.service;

import com.inventoryservice.reopositiry.InventiortRepo;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final InventiortRepo inventiortRepo;

    public InventoryService(InventiortRepo inventiortRepo) {
        this.inventiortRepo = inventiortRepo;
    }
     @Transactional(readOnly = true)
    public boolean isInstock(String skucode){
        // extension method for querying from inventory repo
         return   inventiortRepo.findBySkuCode(skucode).isPresent();
       }
}
