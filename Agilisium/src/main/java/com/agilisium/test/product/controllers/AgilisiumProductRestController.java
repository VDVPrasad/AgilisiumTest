package com.agilisium.test.product.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.agilisium.test.product.entities.ConfigDetails;
import com.agilisium.test.product.entities.Product;

import com.agilisium.test.product.service.ProductService;
import com.agilisium.test.util.CustomErrorType;

@RestController
@RequestMapping(path="/api")
public class AgilisiumProductRestController {
public static final Logger logger=LoggerFactory.getLogger(AgilisiumProductRestController.class);

@Autowired	
ProductService productService;

//---------------------- Retrieve All Products ----------------------

@RequestMapping(value="/getproductdetails/", method=RequestMethod.GET, produces = "application/json")
public ResponseEntity<List<Product>> getAllProducts(){
	logger.info("Fetching all products..... . . . ");
	List<Product> products=productService.getAllProducts();
	if(products.isEmpty()) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
}

//
//---------------------- Retrieve All Products config details ----------------------

@RequestMapping(value="/getconfigdetails/", method=RequestMethod.GET,produces = "application/json")
public ResponseEntity<List<ConfigDetails>> getAllProductsConfigDetails(){
	logger.info("Fetching all products config details ..... . . . ");
	List<ConfigDetails> configs=productService.getAllProductsConfigDetails();
	if(configs.isEmpty()) {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<ConfigDetails>>(configs,HttpStatus.OK);
}

//---------------------- Retrieve Single Products ------------------------
@RequestMapping(value="/product/", method=RequestMethod.GET)
public ResponseEntity<?> getProduct(@PathVariable("id") long id){
	logger.info("Fetching product with id :"+id);
	Product product=productService.findProductById(id);
	if(product==null) {
		logger.error("Product with id : "+id+" not found");
		return new ResponseEntity(new CustomErrorType("Product with id :"+id+" not found "), HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<Product>(product, HttpStatus.OK);
}

//--------------------------Add Product    ---------------------
@RequestMapping(value="/addProduct/", method=RequestMethod.POST)
public ResponseEntity<?> addProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder){
	logger.error("Unable to create a product with name "+product.getProductName() +", its already exist.");
	if(productService.isProductExist(product)) {
		return new ResponseEntity(new CustomErrorType("Unable to create a product with name :"+ product.getProductName()+ " already Exist. "),HttpStatus.CONFLICT);
	}
	productService.addProduct(product);
	
	HttpHeaders headers=new HttpHeaders();
	headers.setLocation(ucBuilder.path("/api/addProduct/{id}").buildAndExpand(product.getProductId()).toUri());

	return new ResponseEntity<String>(headers, HttpStatus.CREATED);
}

//-----------------------  Delete a Product -----------------------------

@RequestMapping(value="/product/{id}", method=RequestMethod.DELETE)
public ResponseEntity<?>  deleteProduct(@PathVariable("id") long id){
	logger.info("Fetching and Deleteing Product with id :"+id);
	Product product=productService.findProductById(id);
	if(product==null) {
		logger.error("Unable to delete, Product with id : "+id+" not found", id);
		return new ResponseEntity(new CustomErrorType("Unable to delete. Product with Id not found"), HttpStatus.NOT_FOUND);
	}
	productService.deleteProduct(id);
	return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
}

//-------------------------- Delete All Products  ------------------------------------------
@RequestMapping(value="/product/", method=RequestMethod.DELETE)
public ResponseEntity<Product> deleteAllProducts(){
	logger.info("Deleting All Products");
	productService.deleteAllProducts();
	return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	
}


	/*
	 * @GetMapping(path="/getProducts")
	 * 
	 * @ResponseBody public ResponseEntity<Product> getProducts(){
	 * 
	 * Products products = new Products(); products.add(101,"Apple", 95000.00);
	 * products.add(102,"Dell", 76000.00); products.add(103,"Hp", 82000.00);
	 * 
	 * //return productService.getAllProducts(); return products.getProducts(); }
	 * 
	 * 
	 * @GetMapping(path="/getAllProducts") public Iterable<Product>
	 * getAllProducts(){
	 * 
	 * return productService.getAllProducts(); }
	 * 
	 * @PostMapping(path="/addProduct") public Product addProduct(@RequestBody
	 * Product product) { return productService.addProduct(product); }
	 * 
	 * @PutMapping(path="/updateProduct") public Product updateProduct(@RequestBody
	 * Product product) { return productService.updateProduct(product); }
	 * 
	 * @DeleteMapping(path="/deleteProduct/{id}") public void
	 * deleteProduct(@PathVariable Integer id) { productService.deleteProduct(id); }
	 * 
	 * @DeleteMapping(path="/deleteAllProduct") public void dleteAllProduct() {
	 * productService.deleteAllProducts(); }
	 * 
	 */
}
