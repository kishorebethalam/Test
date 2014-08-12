package com.mart.service;

import java.util.List;
import com.mart.model.Brand;

public interface BrandService {

	public int addBrand(Brand brand);
	public void updateBrand(Brand brand);
	public void deleteBrand(int id);
	public Brand getBrandById(int id);
	public List<Brand> getAllBrands();
	public List<Brand> getBrandsByCriteria(Object criterion);
}