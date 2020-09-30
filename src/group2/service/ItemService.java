package group2.service;

import group2.data.ItemDAO;
import group2.model.Item;
import group2.model.User;
import java.time.LocalDate;
import java.util.List;

public class ItemService {
    
    ItemDAO itemDAO = new ItemDAO();
    
    public List<Item> createItem(String name, int quantity, LocalDate expiryDate, String productType, User user) {
        itemDAO.createItem(name, quantity, expiryDate, productType, user.getId());
        return itemDAO.getItemsByUserId(user.getId(), user.getExpiryLength());
    }
    
    public List<Item> deleteItemById(int itemId, User user) {
        itemDAO.deleteItemById(itemId);
        return getItemsByUserId(user);
    }

    public List<Item> getItems(User user) {
        return itemDAO.getItems(user.getExpiryLength());
    }
    public Item getItemByItemId(int itemId) {
        return itemDAO.getItemByItemId(itemId);
    }
    
    public List<Item> getItemsByUserId(User user) {
        return itemDAO.getItemsByUserId(user.getId(), user.getExpiryLength());
    }
        
    public List<Item> updateItemById(int itemId, String name, int quantity, String productType, LocalDate expiryDate, User user) {
        itemDAO.updateItemById(itemId, name, quantity, productType, expiryDate);
        return getItemsByUserId(user);
    }
    
}