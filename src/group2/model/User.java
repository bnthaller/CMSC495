package group2.model;

import static group2.model.UserStatus.*;

public class User {
    
    private String username;
    private String firstName;
    private String lastName;
    private int expiryLength;
    private int id;
    private String password;
    private UserStatus status;
    
    public User() {
        username = "";
        firstName = "";
        lastName = "";
        expiryLength = 0;
        id = 0;
        password = "";
        status = UNCONFIRMED;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setExpiryLength(int expiryLength) {
        this.expiryLength = expiryLength;
    }
    
    public int getExpiryLength() {
        return expiryLength;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    
    public UserStatus getStatus() {
        return status;
    }
}