package com.agilisium.test.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilisium.test.product.data.ProductRepository;
import com.agilisium.test.product.entities.ConfigDetails;
import com.agilisium.test.product.entities.Product;

@Service
public class ProductServiceImpl implements ProductService {

	//@Autowired
	//ProductRepository productRepo;
	
	private static final AtomicLong counter = new AtomicLong();
	private static List<Product> products;
	private static List<ConfigDetails> configDetails;
	
	static {
		products=populateDummyProducts();
		configDetails=populateProductconfigDetails();
	}

	public List<Product> getAllProducts() {
		return products;
	}

	public List<ConfigDetails> getAllProductsConfigDetails() {
		return configDetails;
	}
	
	public Product addProduct(Product product) {
		product.setProductId(counter.incrementAndGet());
		products.add(product);
		return product;
	}

	public Product updateProduct(Product product) {
	int index=products.indexOf(product);
	products.set(index, product);
		return product;
	}

	public void deleteProduct(Integer id) {
		
	}

	public void deleteAllProducts() {
		products.clear();		
	}

	public boolean isProductExist(Product product) {
		return findProductByName(product.getProductName())!=null;
	}

	public Product findProductById(int id) {
		for(Product product:products) {
			if(product.getProductId()==id) {
				return product;
			}
		}
		return null;
	}

	public Product findProductByName(String name) {
		for(Product product:products) {
			if(product.getProductName().equalsIgnoreCase(name)) {
				return product;
			}
		}
		return null;
	}
	
	private static List<Product> populateDummyProducts(){
		List<Product> products=new ArrayList<Product>();
		products.add(new Product(counter.incrementAndGet(),"Apple",95000.00));
		products.add(new Product(counter.incrementAndGet(),"Dell",35000));
		products.add(new Product(counter.incrementAndGet(),"HP",36000));
		products.add(new Product(counter.incrementAndGet(),"Samsung",25000));
		
		return products;
	}
	
	private static List<ConfigDetails> populateProductconfigDetails(){
		List<ConfigDetails> configDetails=new ArrayList<ConfigDetails>();
		configDetails.add(new ConfigDetails(" Apple ","8GB RAM","512GB"," 4-core Intel Core i5 processor","13.3-inch Retina"));
		configDetails.add(new ConfigDetails(" Dell ","3GB RAM","350GM","i3 processor","15.6-inch"));
		configDetails.add(new ConfigDetails(" HP ","8GB RAM","1 TB","i3","0.94 x 14.96 x 9.99 inches"));
		configDetails.add(new ConfigDetails(" Sampsung ","16GB RAM","500GB","i7 processor","13.3'' inches"));
		
		return configDetails;
	}

	@Override
	public void deleteProduct(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product findProductById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	/*
	 * @Transactional public List<Product> getAllProducts() { // TODO Auto-generated
	 * method stub return productRepo.findAll(); }
	 * 
	 * @Transactional public Product addProduct(Product product) { // TODO
	 * Auto-generated method stub return productRepo.save(product); }
	 * 
	 * @Transactional public Product updateProduct(Product product) { // TODO
	 * Auto-generated method stub return productRepo.save(product); }
	 * 
	 * 
	 * public void deleteProduct(Integer id) { // TODO Auto-generated method stub
	 * productRepo.deleteById(id);
	 * 
	 * }
	 * 
	 * public void deleteAllProducts() { // TODO Auto-generated method stub
	 * productRepo.deleteAll(); }
	 */

}
