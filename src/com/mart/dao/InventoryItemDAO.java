package com.mart.dao;

import java.util.List;
import com.mart.model.InventoryItem;

public interface InventoryItemDAO {

	public int addInventoryItem(InventoryItem inventoryItem);
	public void updateInventoryItem(InventoryItem inventoryItem);
	public void deleteInventoryItem(int id);
	public InventoryItem getInventoryItemById(int id);
	public List<InventoryItem> getAllInventoryItems();
	public List<InventoryItem> getInventoryItemsByCriteria(Object criterion);
}