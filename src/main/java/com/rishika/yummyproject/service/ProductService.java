package com.rishika.yummyproject.service;


import com.rishika.yummyproject.dto.CustomerRequest;
import com.rishika.yummyproject.dto.ProductRequest;
import com.rishika.yummyproject.entity.Customer;
import com.rishika.yummyproject.entity.Product;
import com.rishika.yummyproject.mapper.CustomerMapper;
import com.rishika.yummyproject.mapper.ProductMapper;
import com.rishika.yummyproject.repo.CustomerRepo;
import com.rishika.yummyproject.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
