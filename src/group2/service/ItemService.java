package group2.service;

import group2.data.ItemDAO;
import group2.model.Item;
import group2.model.ItemException;
import group2.model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ItemService {
    
    ItemDAO itemDAO = new ItemDAO();
    
    public List<Item> createItem(String name, int quantity, LocalDate expiryDate, String productType, User user) throws ItemException {
    	try {
    		itemDAO.createItem(name, quantity, expiryDate, productType, user.getId());
    		return itemDAO.getItems(user.getExpiryLength());
    	} catch (ItemException itemException) {
        	throw itemException;
        }
    }
    
    public List<Item> deleteItemById(int itemId, User user) throws ItemException {
    	try {
    		itemDAO.deleteItemById(itemId);
    		return getItems(user);
    	} catch (ItemException itemException) {
        	throw itemException;
        }
    }

    public List<Item> getItems(User user) throws ItemException {
    	try {
    		return itemDAO.getItems(user.getExpiryLength());
    	} catch (ItemException itemException) {
        	throw itemException;
        }
    }
    
    public Item getItemByItemId(int itemId) throws ItemException {
    	try {
    		return itemDAO.getItemByItemId(itemId);
    	} catch (ItemException itemException) {
        	throw itemException;
        }
    }
            
    public List<Item> updateItemById(int itemId, String name, int quantity, String productType, LocalDate expiryDate, User user) throws ItemException {
    	try {
    		itemDAO.updateItemById(itemId, name, quantity, productType, expiryDate);
    		return getItems(user);
    	} catch (ItemException itemException) {
        	throw itemException;
        }
    }
    
}