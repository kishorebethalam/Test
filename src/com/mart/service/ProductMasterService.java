package com.mart.service;

import java.util.List;
import com.mart.model.ProductMaster;

public interface ProductMasterService {

	public int addProductMaster(ProductMaster productMaster);
	public void updateProductMaster(ProductMaster productMaster);
	public void deleteProductMaster(int id);
	public ProductMaster getProductMasterById(int id);
	public List<ProductMaster> getAllProductMasters();
	public List<ProductMaster> getProductMastersByCriteria(Object criterion);
}