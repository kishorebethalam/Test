package com.mart.dao;

import java.util.List;
import com.mart.model.Product;

public interface ProductDAO {

	public int addProduct(Product product);
	public void updateProduct(Product product);
	public void deleteProduct(int id);
	public Product getProductById(int id);
	public List<Product> getAllProducts();
	public List<Product> getProductsByCriteria(Object criterion);
}