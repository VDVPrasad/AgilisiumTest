package com.agilisium.test.product.service;

import java.util.List;

import com.agilisium.test.product.entities.ConfigDetails;
import com.agilisium.test.product.entities.Product;

public interface ProductService {
	public List<Product> getAllProducts();
	public List<ConfigDetails> getAllProductsConfigDetails();
	public Product addProduct(Product product);
	public Product updateProduct(Product product);
	public void deleteProduct(long id);
	public void deleteAllProducts();
	public boolean isProductExist(Product product);
	public Product findProductById(long id);
	public Product findProductByName(String name);
}
