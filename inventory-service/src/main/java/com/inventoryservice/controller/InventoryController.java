package com.inventoryservice.controller;


import com.inventoryservice.dto.inventoryresponse;
import com.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;


    // http://localhost:8082/api/inventory?sku-code=iphone12&sku-code=iphone13red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<inventoryresponse> isInStock(@RequestParam List<String> skucode) {
        return inventoryService.isInstock(skucode);

    }
}
