package group2.data;

import group2.Application;
import group2.model.Item;
import group2.model.ItemException;

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
    
    /**
     * 
     * @param expiryLength
     * @return
     * @throws ItemException
     * 
     * Returns a list of Item objects in the pantry table.
     * If the retrieval is unsuccessful, throw an ItemException.
     */
    public List<Item> getItems(int expiryLength) throws ItemException {
        List<Item> items = new ArrayList<>();
        LocalDate today = java.time.LocalDate.now();
        
        StringBuilder getItemsByUserIdQuery = new StringBuilder();
        getItemsByUserIdQuery.append("SELECT p.pantry_id, p.name, p.quantity, p.expiration, pt.product_type ");
        getItemsByUserIdQuery.append("FROM pantry p, product_type pt ");
        getItemsByUserIdQuery.append("WHERE p.pantry_type_id = pt.pantry_type_id");
        
        try {
            PreparedStatement getItemsByUserIdStatement = conn.prepareStatement(getItemsByUserIdQuery.toString());
           
            resultSet = getItemsByUserIdStatement.executeQuery();
            
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setName(resultSet.getString(2));
                item.setQuantity(resultSet.getInt(3));
                item.setExpiryDate(resultSet.getDate(4).toLocalDate());
                item.setProductType(resultSet.getString(5));
                
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
            
            return items;
        } catch (SQLException ex) {
        	throw new ItemException("Unable to retrieve Items.");
        }
    }
    
    /**
     * 
     * @param itemId
     * @return
     * @throws ItemException
     * 
     * Returns an Item object by itemId.
     * If the retrieval is unsuccessful, throw an ItemException.
     */
    public Item getItemByItemId(int itemId) throws ItemException {
        StringBuilder getItemsByUserIdQuery = new StringBuilder();
        getItemsByUserIdQuery.append("SELECT p.pantry_id, p.name, p.quantity, p.expiration, pt.product_type ");
        getItemsByUserIdQuery.append("FROM pantry p, product_type pt ");
        getItemsByUserIdQuery.append("WHERE p.product_type_id = pt.product_type_id AND pantry_id = ?");
        
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
                item.setProductType(resultSet.getString(5));
            }
            
            clearResultSet();
            
            return item;
        } catch (SQLException ex) {
        	throw new ItemException("Unable to retrieve Item.");
        }
    }
    
    /**
     * 
     * @param name
     * @param quantity
     * @param expiryDate
     * @param productType
     * @param userId
     * @throws ItemException
     * 
     * Inserts a new record into the pantry table with the provided values.
     * If the entry is unsuccessful, throw an ItemException.
     */
    public void createItem(String name, int quantity, LocalDate expiryDate, String productType, int userId) throws ItemException {        
        StringBuilder createItemQuery = new StringBuilder();
        createItemQuery.append("INSERT INTO pantry(name, quantity, expiration, product_type_id) ");
        createItemQuery.append("VALUES (?, ?, ?, ?)");
                
        try {
            PreparedStatement createItemStatement = conn.prepareStatement(createItemQuery.toString(), Statement.RETURN_GENERATED_KEYS);
            createItemStatement.setString(1, name);
            createItemStatement.setInt(2, quantity);
            createItemStatement.setDate(3, Date.valueOf(expiryDate));
            createItemStatement.setInt(4, getProductTypeId(productType));
            
            createItemStatement.executeUpdate();
        } catch (SQLException ex) {
        	throw new ItemException("Unable to create the item.");
        }
    }
    
    /**
     * 
     * @param itemId
     * @param name
     * @param quantity
     * @param productType
     * @param expiryDate
     * @throws ItemException
     * 
     * Updates a pantry item with the provided values.
     * If the update is unsuccessful, throw an ItemException.
     */
    public void updateItemById(int itemId, String name, int quantity, String productType, LocalDate expiryDate) throws ItemException {        
        StringBuilder updateItemByIdQuery = new StringBuilder();
        updateItemByIdQuery.append("UPDATE pantry SET name = ?, quantity = ?, product_type_id = ?, expiration = ? WHERE pantry_id = ?");
        
        try {
            PreparedStatement updateItemByIdStatement = conn.prepareStatement(updateItemByIdQuery.toString());
            updateItemByIdStatement.setString(1, name);
            updateItemByIdStatement.setInt(2, quantity);
            updateItemByIdStatement.setInt(3, getProductTypeId(productType));
            updateItemByIdStatement.setDate(4, Date.valueOf(expiryDate));
            updateItemByIdStatement.setInt(5, itemId);
            
            updateItemByIdStatement.executeUpdate();
        } catch (SQLException ex) {
        	throw new ItemException("Unable to update the item.");
        }
    }
    
    /**
     * 
     * @param itemId
     * @throws ItemException
     * 
     * Deletes an Item from the item table based on the items id.
     * If the retrieval is unsuccessful, throw an ItemException.
     */
    public void deleteItemById(int itemId) throws ItemException {
        StringBuilder deleteItemByIdQuery = new StringBuilder();
        deleteItemByIdQuery.append("DELETE FROM pantry WHERE pantry_id = ?");
        
        try {
            PreparedStatement deleteItemByIdStatement = conn.prepareStatement(deleteItemByIdQuery.toString());
            deleteItemByIdStatement.setInt(1, itemId);
            
            deleteItemByIdStatement.executeUpdate();
        } catch (SQLException ex) {
        	throw new ItemException("Unable to delete the item.");
        }
    }
    
    /**
     * 
     * @param productType
     * @return
     * @throws ItemException
     * 
     * Returns the product type from the product type table based on the id.
     * If the retrieval is unsuccessful, throw an ItemException.
     */
    private int getProductTypeId(String productType) throws ItemException {
    	StringBuilder getProductTypeIdQuery = new StringBuilder();
    	getProductTypeIdQuery.append("SELECT pantry_type_id FROM pantry WHERE product_type = ?");
    	
    	try {
    		PreparedStatement getProductTypeIdStatement = conn.prepareStatement(getProductTypeIdQuery.toString());
    		getProductTypeIdStatement.setString(1, productType);
    		
    		resultSet = getProductTypeIdStatement.executeQuery();
    		return resultSet.getInt(1);
    	} catch (SQLException ex) {
    		throw new ItemException("Unable to retrieve Pantry Type.");
    	}
    }
    
    public List<String> getProductTypes() throws ItemException {
    	List<String> productTypes = new ArrayList<>();
    	
    	StringBuilder getProductTypesQuery = new StringBuilder();
    	getProductTypesQuery.append("SELECT product_type FROM product_type");
    	
    	try {
    		PreparedStatement getProductTypeStatement = conn.prepareStatement(getProductTypesQuery.toString());
    		resultSet = getProductTypeStatement.executeQuery();
    		
    		while (resultSet.next()) {
    			productTypes.add(resultSet.getString(1));
    		}
    		
    		clearResultSet();
            
            return productTypes;
    	} catch (SQLException ex) {
    		throw new ItemException("Unable to retrieve Product Types.");
    	}
    }
    
}