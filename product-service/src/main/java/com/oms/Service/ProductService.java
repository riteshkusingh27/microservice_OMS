package com.oms.Service;

import com.oms.Repo.ProductRepo;
import com.oms.dto.ProductRequest;
import com.oms.dto.ProductResponse;
import com.oms.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
   private final  ProductRepo productRepo;



    public void createProduct(ProductRequest productRequest){
   Product product =  Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepo.save(product);
        log.info("Product {} is saved" , product.getId());
    }

    public List<ProductResponse> getAllProduct() {

            List<Product> allproducts =      productRepo.findAll();
            // return the list using stream   stream.toList()  returns unmodifyable list
            // wherein strem.collect(Collectores.toList()) produces mutable arrayLIst
          return    allproducts.stream().map(this::convertToResponse).toList();
    }

    private ProductResponse convertToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }



}
