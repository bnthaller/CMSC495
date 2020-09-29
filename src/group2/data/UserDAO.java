package group2.data;

import group2.Application;
import group2.model.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO /*extends DBConnection*/ {
    Connection conn = null;
    ResultSet resultSet;
    
    public UserDAO() {
    	this.conn = Application.sqlConn;
    }
    
    private void clearResultSet() {
        resultSet = null;
    }
        
    public int createUser(String username, String firstName, String lastName, String password, int expiryLength) {
        int userId = 0;
        
        StringBuilder createUserQuery = new StringBuilder();
        createUserQuery.append("INSERT INTO user(username, firstName, lastName, password, expiry_length) ");
        createUserQuery.append("VALUES (?, ?, ?, ?, ?)");
        
        try {
            PreparedStatement createUserStatement = conn.prepareStatement(createUserQuery.toString(), Statement.RETURN_GENERATED_KEYS);
            createUserStatement.setString(1, username);
            createUserStatement.setString(2, firstName);
            createUserStatement.setString(3, lastName);
            createUserStatement.setString(4, password);
            createUserStatement.setInt(5, expiryLength);
            
            userId = createUserStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userId;
    }
    
    public User getUserById(int userId) {
        User user = new User();
        
        StringBuilder getUserByIdQuery = new StringBuilder();
        getUserByIdQuery.append("SELECT user_id, username, firstName, lastName, password, expiry_length FROM User WHERE user_id = ?");
        
        try {
            PreparedStatement getUserByIdStatement = conn.prepareStatement(getUserByIdQuery.toString());
            getUserByIdStatement.setInt(1, userId);
            
            resultSet = getUserByIdStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setExpiryLength(resultSet.getInt(6));
            }
            
            clearResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public void updateUserById(int userId, String username, String firstName, String lastName, String password, int expiryLength) {        
        StringBuilder updateUserByIdQuery = new StringBuilder();
        updateUserByIdQuery.append("UPDATE user SET firstName = ?, lastName = ?, password = ?, expiry_length = ? WHERE id = ?");
        
        try {
            PreparedStatement updateUserByIdStatement = conn.prepareStatement(updateUserByIdQuery.toString());
            updateUserByIdStatement.setString(1, firstName);
            updateUserByIdStatement.setString(2, lastName);
            updateUserByIdStatement.setString(3, password);
            updateUserByIdStatement.setInt(4, expiryLength);
            updateUserByIdStatement.setInt(5, userId);
            
            updateUserByIdStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int verifyUser(String username, String password) {
        int userId = 0;
        
        StringBuilder verifyUserQuery = new StringBuilder();
        verifyUserQuery.append("SELECT user_id from user WHERE username = ? AND password = ?");
        
        try {
            PreparedStatement verifyUserStatement = conn.prepareStatement(verifyUserQuery.toString());
            verifyUserStatement.setString(1, username);
            verifyUserStatement.setString(2, password);
            
            resultSet = verifyUserStatement.executeQuery();
            
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            
            clearResultSet();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userId;
    }
    
}
