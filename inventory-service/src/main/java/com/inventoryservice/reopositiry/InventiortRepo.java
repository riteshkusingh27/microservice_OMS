package com.inventoryservice.reopositiry;

import com.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventiortRepo extends
        JpaRepository<Inventory, Long> {
    // generate query on runtime
    Optional<Inventory> findBySkuCode(String skucode);

    List<Inventory> findBySkuCodeIn(List<String> skucode);
}
