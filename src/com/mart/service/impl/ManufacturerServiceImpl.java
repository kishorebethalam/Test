package com.mart.service.impl;


import java.util.List;
import com.mart.model.Manufacturer;
import com.mart.service.ManufacturerService;
import com.mart.dao.ManufacturerDAO;
import com.mart.dao.impl.ManufacturerDAOImpl;

public class ManufacturerServiceImpl implements ManufacturerService {

	protected ManufacturerDAO manufacturerDAO ;
	public ManufacturerServiceImpl() {
		this.manufacturerDAO = new ManufacturerDAOImpl();
	}
	
	public int addManufacturer(Manufacturer manufacturer){
		return this.manufacturerDAO.addManufacturer(manufacturer);
	}
	public void updateManufacturer(Manufacturer manufacturer){
		this.manufacturerDAO.updateManufacturer(manufacturer);
	}
	public void deleteManufacturer(int id){
		this.manufacturerDAO.deleteManufacturer(id);
	}
	public Manufacturer getManufacturerById(int id){
		return this.manufacturerDAO.getManufacturerById(id);
	}
	public List<Manufacturer> getAllManufacturers(){
		return this.manufacturerDAO.getAllManufacturers();
	}
	public List<Manufacturer> getManufacturersByCriteria(Object criterion){
		return this.manufacturerDAO.getManufacturersByCriteria(criterion);
	}
}

