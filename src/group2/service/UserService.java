package group2.service;

import group2.data.UserDAO;
import group2.model.UserStatus;
import group2.model.User;

public class UserService {
    
    UserDAO userDAO = new UserDAO();
    
    public User createUser(String username, String firstName, String lastName, String password) {
        int userId = userDAO.createUser(username, firstName, lastName, password);
        return userDAO.getUserById(userId);
    }
    
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
    
    public User updateUser(int userId, String username, String firstName, String lastName, String password, int expiryLength) {
        userDAO.updateUserById(userId, username, firstName, lastName, password, expiryLength);
        return getUserById(userId);
    }
    
    public User verifyUser(String email, String password) {
        int userId = userDAO.verifyUser(email, password);
        
        if (userId > 0) {
            User verifiedUser = userDAO.getUserById(userId);
            verifiedUser.setStatus(UserStatus.CONFIRMED);
            return verifiedUser;
        }
        
        return new User();
    }
    
}
