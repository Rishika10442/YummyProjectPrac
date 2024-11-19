package com.rishika.yummyproject.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.rishika.yummyproject.dto.CustomerRequest;
import com.rishika.yummyproject.dto.ProductRequest;
import com.rishika.yummyproject.entity.Customer;
import com.rishika.yummyproject.entity.Product;
import com.rishika.yummyproject.exception.CustomerNotFoundException;
import com.rishika.yummyproject.mapper.CustomerMapper;
import com.rishika.yummyproject.mapper.ProductMapper;
import com.rishika.yummyproject.repo.CustomerRepo;
import com.rishika.yummyproject.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo prepo;
    private final ProductMapper pmapper;

    public String createProduct(ProductRequest request) {

        Product p = pmapper.toEntity(request);
        prepo.save(p);
        return "Created";
    }

    public List<Product> getTop2ProductsInPriceRange(double minPrice, double maxPrice) {
        return prepo.findTop2ByPriceRange(minPrice, maxPrice);
    }

    public String updateProduct(JsonNode requestBody) {
        String name = requestBody.get("name").asText();
        Product existing = prepo.findByName(name);

        if (existing == null) {
            throw new CustomerNotFoundException("Product not found");
        }



        // Update fields if they exist in the request
        if (requestBody.has("price")) {
            existing.setPrice(requestBody.get("price").asDouble());
        }

        prepo.save(existing);
        return "Product updated successfully";
    }

    public void deleteProduct(String name) {
        Product existing = prepo.findByName(name);
        //logger.debug("Loading user details for deletion: {}", existingCustomer);
        if (existing == null) {
            throw new CustomerNotFoundException("Product not found");
        }


        prepo.delete(existing);
    }
}
