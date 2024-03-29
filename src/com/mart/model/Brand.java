package com.mart.model;

// Generated Aug 12, 2014 10:36:52 PM by Hibernate Tools 3.4.0.CR1

import com.mart.annotation.POSFieldAnnotation;
import com.mart.annotation.POSModelAnnotation;

/**
 * Brand generated by hbm2java
 */
@POSModelAnnotation(dbTableName="BRAND")
public class Brand extends POSModel implements java.io.Serializable {

	@POSFieldAnnotation(dbColumnName = "id", jsonColumnName = "id")
	private Integer id;
	
	@POSFieldAnnotation(dbColumnName = "manufacturer_id", jsonColumnName = "manufacturer_id")
	private Integer manufacturerId;

	@POSFieldAnnotation(dbColumnName = "name", jsonColumnName = "name")
	private String name;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the manufacturerId
	 */
	public Integer getManufacturerId() {
		return manufacturerId;
	}

	/**
	 * @param manufacturerId the manufacturerId to set
	 */
	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
