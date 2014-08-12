package com.mart.service.impl;


import java.util.List;
import com.mart.model.Category;
import com.mart.service.CategoryService;
import com.mart.dao.CategoryDAO;
import com.mart.dao.impl.CategoryDAOImpl;

public class CategoryServiceImpl implements CategoryService {

	protected CategoryDAO categoryDAO ;
	public CategoryServiceImpl() {
		this.categoryDAO = new CategoryDAOImpl();
	}
	
	public int addCategory(Category category){
		return this.categoryDAO.addCategory(category);
	}
	public void updateCategory(Category category){
		this.categoryDAO.updateCategory(category);
	}
	public void deleteCategory(int id){
		this.categoryDAO.deleteCategory(id);
	}
	public Category getCategoryById(int id){
		return this.categoryDAO.getCategoryById(id);
	}
	public List<Category> getAllCategories(){
		return this.categoryDAO.getAllCategories();
	}
	public List<Category> getCategoriesByCriteria(Object criterion){
		return this.categoryDAO.getCategoriesByCriteria(criterion);
	}
}

