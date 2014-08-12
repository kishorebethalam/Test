package com.mart.service.impl;


import java.util.List;
import com.mart.model.InventoryItem;
import com.mart.service.InventoryItemService;
import com.mart.dao.InventoryItemDAO;
import com.mart.dao.impl.InventoryItemDAOImpl;

public class InventoryItemServiceImpl implements InventoryItemService {

	protected InventoryItemDAO inventoryItemDAO ;
	public InventoryItemServiceImpl() {
		this.inventoryItemDAO = new InventoryItemDAOImpl();
	}
	
	public int addInventoryItem(InventoryItem inventoryItem){
		return this.inventoryItemDAO.addInventoryItem(inventoryItem);
	}
	public void updateInventoryItem(InventoryItem inventoryItem){
		this.inventoryItemDAO.updateInventoryItem(inventoryItem);
	}
	public void deleteInventoryItem(int id){
		this.inventoryItemDAO.deleteInventoryItem(id);
	}
	public InventoryItem getInventoryItemById(int id){
		return this.inventoryItemDAO.getInventoryItemById(id);
	}
	public List<InventoryItem> getAllInventoryItems(){
		return this.inventoryItemDAO.getAllInventoryItems();
	}
	public List<InventoryItem> getInventoryItemsByCriteria(Object criterion){
		return this.inventoryItemDAO.getInventoryItemsByCriteria(criterion);
	}
}

