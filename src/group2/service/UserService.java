package group2.service;

import java.security.NoSuchAlgorithmException;

import group2.data.UserDAO;
import group2.model.UserStatus;
import group2.utility.Utility;
import group2.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    public static User currentUser = null;
    
    public UserService() {
    	currentUser = null;
    }
    public User createUser(String username, String firstName, String lastName, String password, int expiryLength) {
    	if (!Utility.isUserValid(username, password)) {
    		return new User();
    	}
    	
		try {
			int userId = userDAO.createUser(username, firstName, lastName, Utility.hashPassword(password), expiryLength);
			return userDAO.getUserById(userId);
    	} catch (NoSuchAlgorithmException ex) {
    		return new User();
    	}
    }
    
    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }
    
    public User updateUser(int userId, String username, String firstName, String lastName, String password, int expiryLength) {
    	try {
			password = Utility.hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			return new User();
		}
    	
        userDAO.updateUserById(userId, username, firstName, lastName, password, expiryLength);
        return getUserById(userId);
    }
    
    public User verifyUser(String email, String password) {
    	try {
			password = Utility.hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
//			return new User();
			return null;
		}
    	
        int userId = userDAO.verifyUser(email, password);
        
        if (userId > 0) {
            User verifiedUser = userDAO.getUserById(userId);
            verifiedUser.setStatus(UserStatus.CONFIRMED);
            currentUser = verifiedUser;
            return verifiedUser;
        }
        
//        return new User();
		return null;
    }
    
}
