package group2.model;

import static group2.model.ItemStatus.*;
import java.time.LocalDate;

public class Item {
    
    private int quantity;
    private String name;
    private LocalDate expiryDate;
    private int id;
    private ItemStatus status;
    private int userId;
    private int productTypeId;
   
    public Item() {
    	quantity = 0;
        name = "";
        expiryDate = java.time.LocalDate.now();
        id = 0;
        status = EXPIRED;
        userId = 0;
        productTypeId = 0;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getProductType() {
        return productTypeId;
    }

    public void setProductType(int productTypeId) {
        this.productTypeId = productTypeId;
    }
}