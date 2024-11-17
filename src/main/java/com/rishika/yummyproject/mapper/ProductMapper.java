package com.rishika.yummyproject.mapper;

import com.rishika.yummyproject.dto.ProductRequest;
import com.rishika.yummyproject.entity.Customer;
import com.rishika.yummyproject.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }


}
