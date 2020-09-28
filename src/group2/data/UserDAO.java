package group2.data;

import group2.model.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO extends DBConnection {
    
    ResultSet resultSet;
    
    private void clearResultSet() {
        resultSet = null;
    }
        
    public int createUser(String username, String firstName, String lastName, String password) {
        int userId = 0;
        
        StringBuilder createUserQuery = new StringBuilder();
        createUserQuery.append("INSERT INTO users(email, firstName, lastName, password) ");
        createUserQuery.append("VALUES (?, ?, ?, ?)");
        
        try {
            PreparedStatement createUserStatement = conn.prepareStatement(createUserQuery.toString(), Statement.RETURN_GENERATED_KEYS);
            createUserStatement.setString(1, username);
            createUserStatement.setString(2, firstName);
            createUserStatement.setString(3, lastName);
            createUserStatement.setString(4, password);
            
            userId = createUserStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userId;
    }
    
    public User getUserById(int userId) {
        User user = new User();
        
        StringBuilder getUserByIdQuery = new StringBuilder();
        getUserByIdQuery.append("SELECT id, username, firstName, lastName, password, expiryLength FROM Users WHERE id = ?");
        
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
        updateUserByIdQuery.append("UPDATE users SET firstName = ?, lastName = ?, password = ?, expiryLength = ? WHERE id = ?");
        
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
        verifyUserQuery.append("SELECT id from users WHERE username = ? AND password = ?");
        
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
