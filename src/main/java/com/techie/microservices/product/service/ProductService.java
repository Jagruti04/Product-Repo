package com.techie.microservices.product.service;

import com.techie.microservices.product.dto.ProductRequest;
import com.techie.microservices.product.dto.ProductResponse;
import com.techie.microservices.product.external.client.InventoryClient;
import com.techie.microservices.product.external.dto.InventoryRequest;
import com.techie.microservices.product.external.dto.InventoryResponse;
import com.techie.microservices.product.model.Product;
import com.techie.microservices.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);

	@Autowired
    private  ProductRepository productRepository;
   
    //private  InventoryClient inventoryClient;

    public ProductResponse createProduct(ProductRequest productRequest) {
        // Logic to create a product
        Product product = new Product();
                product.setSkuCode(productRequest.skuCode());
                product.setName(productRequest.name());
                product.setDescription(productRequest.description());
                product.setPrice(productRequest.price());
                

        productRepository.save(product);
        log.info("Product created successfully: {}", product);

      //InventoryResponse inventoryResponse = inventoryClient.upsertInventory(new InventoryRequest(productRequest.skuCode(), productRequest.quantity())).getBody();
        //log.info("Product's inventory created: {}", inventoryResponse);

//        assert inventoryResponse != null;
        return new ProductResponse(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice());
           //  inventoryResponse.quantity());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productsList = productRepository.findAll();
        for(Product product: productsList){
            System.out.println(product.getSkuCode());
        }
        return productsList
                .stream()
                .map(product -> new ProductResponse(product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice())).toList();
                      //  inventoryClient.getInventoryBySkuCode(product.getSkuCode()).getBody().quantity()))
                
    }
}
