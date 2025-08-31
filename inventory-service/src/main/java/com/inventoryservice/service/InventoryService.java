package com.inventoryservice.service;

import com.inventoryservice.dto.inventoryresponse;
import com.inventoryservice.reopositiry.InventiortRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class InventoryService {

    private final InventiortRepo inventiortRepo;

    public InventoryService(InventiortRepo inventiortRepo) {
        this.inventiortRepo = inventiortRepo;
    }
     @Transactional(readOnly = true)
    public List<inventoryresponse> isInstock(List<String> skucode) throws InterruptedException {
        // extension method for querying from inventory repo

  log.info("wait started");
  Thread.sleep(10000);
  log.info("wait ended");

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
