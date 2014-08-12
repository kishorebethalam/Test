package com.mart.service;

import java.util.List;
import com.mart.model.InventoryItem;

public interface InventoryItemService {

	public int addInventoryItem(InventoryItem inventoryItem);
	public void updateInventoryItem(InventoryItem inventoryItem);
	public void deleteInventoryItem(int id);
	public InventoryItem getInventoryItemById(int id);
	public List<InventoryItem> getAllInventoryItems();
	public List<InventoryItem> getInventoryItemsByCriteria(Object criterion);
}