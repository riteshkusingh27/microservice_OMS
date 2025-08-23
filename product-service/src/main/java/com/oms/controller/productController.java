package com.oms.controller;

import com.oms.Service.ProductService;
import com.oms.dto.ProductRequest;
import com.oms.dto.ProductResponse;
import com.oms.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class productController {
    private final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest product){
        productService.createProduct(product);

    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getallProduct(){
      return productService.getAllProduct();
    }

}
