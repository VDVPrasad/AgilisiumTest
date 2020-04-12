package com.agilisium.test.product.data;

import org.springframework.data.repository.CrudRepository;

import com.agilisium.test.product.entities.Product;


public interface ProductRepository extends CrudRepository<Product, Integer> {

}
