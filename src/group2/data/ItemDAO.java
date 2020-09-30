package group2.data;

import group2.Application;
import group2.model.Item;
import static group2.model.ItemStatus.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO /*extends DBConnection*/ {
    Connection conn = null;
    ResultSet resultSet;
    
    public ItemDAO() {
    	this.conn = Application.sqlConn;
    }
    private void clearResultSet() {
        resultSet = null;
    }
    
    public List<Item> getItems(int expiryLength) {
        List<Item> items = new ArrayList<>();
        LocalDate today = java.time.LocalDate.now();
        
        StringBuilder getItemsByUserIdQuery = new StringBuilder();
        getItemsByUserIdQuery.append("SELECT pantry_id, name, quantity, expiration, product_type_id, user_id FROM pantry");
        
        try {
            PreparedStatement getItemsByUserIdStatement = conn.prepareStatement(getItemsByUserIdQuery.toString());
           
            resultSet = getItemsByUserIdStatement.executeQuery();
            
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setName(resultSet.getString(2));
                item.setQuantity(resultSet.getInt(3));
                item.setExpiryDate(resultSet.getDate(4).toLocalDate());
                item.setProductType(resultSet.getInt(5));
                item.setUserId(resultSet.getInt(6));
                
                if (item.getExpiryDate().isAfter(today)) {
                    item.setStatus(EXPIRED);
                } else if (item.getExpiryDate().plusDays(expiryLength).isAfter(today)) {
                    item.setStatus(EXPIRING);
                } else {
                    item.setStatus(VALID);
                }
                
                items.add(item);
            }
            
            clearResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    public Item getItemByItemId(int itemId) {
        StringBuilder getItemsByUserIdQuery = new StringBuilder();
        getItemsByUserIdQuery.append("SELECT pantry_id, name, quantity, expiration, product_type_id, user_id FROM pantry WHERE pantry_id = ?");
        
        try {
            PreparedStatement getItemsByUserIdStatement = conn.prepareStatement(getItemsByUserIdQuery.toString());
            getItemsByUserIdStatement.setInt(1, itemId);
            
            resultSet = getItemsByUserIdStatement.executeQuery();
            Item item = null;
            if (resultSet.next()) {
                item = new Item();
                item.setId(resultSet.getInt(1));
                item.setName(resultSet.getString(2));
                item.setQuantity(resultSet.getInt(3));
                item.setExpiryDate(resultSet.getDate(4).toLocalDate());
                item.setProductType(resultSet.getInt(5));
                item.setUserId(resultSet.getInt(6));
            }
            
            clearResultSet();
            
            return item;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Item> getItemsByUserId(int userId, int expiryLength) {
        List<Item> items = new ArrayList<>();
        LocalDate today = java.time.LocalDate.now();
        
        StringBuilder getItemsByUserIdQuery = new StringBuilder();
        getItemsByUserIdQuery.append("SELECT pantry_id, name, quantity, expiration, product_type_id, user_id FROM pantry");// WHERE userId = ?");
        
        try {
            PreparedStatement getItemsByUserIdStatement = conn.prepareStatement(getItemsByUserIdQuery.toString());
//            getItemsByUserIdStatement.setInt(1, userId);
            
            resultSet = getItemsByUserIdStatement.executeQuery();
            
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setName(resultSet.getString(2));
                item.setQuantity(resultSet.getInt(3));
                item.setExpiryDate(resultSet.getDate(4).toLocalDate());
                item.setProductType(resultSet.getInt(5));
                item.setUserId(resultSet.getInt(6));
                
                if (item.getExpiryDate().isAfter(today)) {
                    item.setStatus(EXPIRED);
                } else if (item.getExpiryDate().plusDays(expiryLength).isAfter(today)) {
                    item.setStatus(EXPIRING);
                } else {
                    item.setStatus(VALID);
                }
                
                items.add(item);
            }
            
            clearResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return items;
    }
    
    public void createItem(String name, int quantity, LocalDate expiryDate, String productType, int userId) {        
        StringBuilder createItemQuery = new StringBuilder();
        createItemQuery.append("INSERT INTO pantry(name, quantity, expiration, product_type_id, user_id) ");
        createItemQuery.append("VALUES (?, ?, ?, ?, ?)");
        
        try {
            PreparedStatement createItemStatement = conn.prepareStatement(createItemQuery.toString(), Statement.RETURN_GENERATED_KEYS);
            createItemStatement.setString(1, name);
            createItemStatement.setInt(2, quantity);
            createItemStatement.setDate(3, Date.valueOf(expiryDate));
            createItemStatement.setString(4, productType);
            createItemStatement.setInt(5, userId);
            
            createItemStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateItemById(int itemId, String name, int quantity, String productType, LocalDate expiryDate) {        
        StringBuilder updateItemByIdQuery = new StringBuilder();
        updateItemByIdQuery.append("UPDATE pantry SET name = ?, quantity = ?, product_type_id = ?, expiration = ? WHERE pantry_id = ?");
        
        try {
            PreparedStatement updateItemByIdStatement = conn.prepareStatement(updateItemByIdQuery.toString());
            updateItemByIdStatement.setString(1, name);
            updateItemByIdStatement.setInt(2, quantity);
            updateItemByIdStatement.setString(3, productType);
            updateItemByIdStatement.setDate(4, Date.valueOf(expiryDate));
            updateItemByIdStatement.setInt(5, itemId);
            
            updateItemByIdStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteItemById(int itemId) {
        StringBuilder deleteItemByIdQuery = new StringBuilder();
        deleteItemByIdQuery.append("DELETE FROM pantry WHERE pantry_id = ?");
        
        try {
            PreparedStatement deleteItemByIdStatement = conn.prepareStatement(deleteItemByIdQuery.toString());
            deleteItemByIdStatement.setInt(1, itemId);
            
            deleteItemByIdStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}