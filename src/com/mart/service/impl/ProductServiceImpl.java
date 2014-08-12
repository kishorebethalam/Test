package com.mart.service.impl;


import java.util.List;
import com.mart.model.Product;
import com.mart.service.ProductService;
import com.mart.dao.ProductDAO;
import com.mart.dao.impl.ProductDAOImpl;

public class ProductServiceImpl implements ProductService {

	protected ProductDAO productDAO ;
	public ProductServiceImpl() {
		this.productDAO = new ProductDAOImpl();
	}
	
	public int addProduct(Product product){
		return this.productDAO.addProduct(product);
	}
	public void updateProduct(Product product){
		this.productDAO.updateProduct(product);
	}
	public void deleteProduct(int id){
		this.productDAO.deleteProduct(id);
	}
	public Product getProductById(int id){
		return this.productDAO.getProductById(id);
	}
	public List<Product> getAllProducts(){
		return this.productDAO.getAllProducts();
	}
	public List<Product> getProductsByCriteria(Object criterion){
		return this.productDAO.getProductsByCriteria(criterion);
	}
}

