package com.inventoryservice;

import com.inventoryservice.entity.Inventory;
import com.inventoryservice.reopositiry.InventiortRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventiortRepo invrepo){

        return args ->{
            Inventory inventory = new Inventory();
            inventory.setSkuCode("iphone13");
            inventory.setQuantity(8);
            Inventory inventory2 = new Inventory();
            inventory2.setSkuCode("iphone13_red");
            inventory2.setQuantity(5);


            invrepo.save(inventory);
            invrepo.save(inventory2);

        };

    }

}
