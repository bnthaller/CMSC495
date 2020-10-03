package group2.service;

//import java.security.NoSuchAlgorithmException;

import group2.data.UserDAO;
import group2.model.UserStatus;
import group2.utility.Utility;
import group2.model.User;
import group2.model.UserException;

public class UserService {
    private UserDAO userDAO = new UserDAO();
    public static User currentUser = null;
    
    public UserService() {
    	currentUser = null;
    }
    
    /**
     * 
     * @param username
     * @param firstName
     * @param lastName
     * @param password
     * @param expiryLength
     * @return
     * @throws UserException
     * 
     * If the user has a valid username and password, create the user and return a hydrated user object with their data.
     * If unable to create or return the users data, throw a UserException.
     */
    public User createUser(String username, String firstName, String lastName, String password, int expiryLength) throws UserException {
    	try {
    		//Validate Users username and password
	    	Utility.isUserValid(username, password);
			
	    	//Create the user and return the new Users userId
			int userId = userDAO.createUser(username, firstName, lastName, Utility.preparePassword(password), expiryLength);
			
			//Return the newly created user
			return userDAO.getUserById(userId);
    	} catch (UserException userException) {
    		throw (userException);
    	}
    }
    
    /**
     * 
     * @param userId
     * @return
     * @throws UserException
     * 
     * Method retrieves and hydrates a User by their userId.
     * If unable to retrieve the user, thow a UserException.
     */
    public User getUserById(int userId) throws UserException {
    	try {
    		return userDAO.getUserById(userId);
    	} catch (UserException userException) {
    		throw (userException);
    	}
    }
    
    /**
     * 
     * @param userId
     * @param username
     * @param firstName
     * @param lastName
     * @param password
     * @param expiryLength
     * @return
     * @throws UserException
     * 
     * Method updates users information.
     * If unable to update a users information, throw a UserException.
     * 
     * TODO: if a users password is sent here and it's not a new entry, it will resalt and rehash the value.  This will break the users record.
     */
    public User updateUser(int userId, String username, String firstName, String lastName, String password, int expiryLength) throws UserException {
    	try {
    		//Salt and hash password
			password = Utility.preparePassword(password);

			//Update user information
			userDAO.updateUserById(userId, username, firstName, lastName, password, expiryLength);
			
			//Return a new User object with the users updated information
			return getUserById(userId);
    	} catch (UserException userException) {
    		throw (userException);
    	}
    }
    
    /**
     * 
     * @param username
     * @param password
     * @return
     * @throws UserException
     * 
     * Method salts and hashes the provided password and finds the user with the associated username and password.
     * If unable to return the User object, throw UserException.
     */
    public User verifyUser(String username, String password) throws UserException {
    	try {
    		//Salt and Hash the users password entry
			password = Utility.preparePassword(password);
    	
			//Get the userId of the user associated with the username password combination provided
			int userId = userDAO.verifyUser(username, password);
        
			//Get the user with the userId
            User verifiedUser = userDAO.getUserById(userId);
            
            //Set the status of the user to confirmed
            verifiedUser.setStatus(UserStatus.CONFIRMED);
            
            //Set the static currentUser value to this user
            currentUser = verifiedUser;
            
            //Return a the User object with the users information
            return verifiedUser;
    	} catch (UserException userException) {
    		throw (userException);
    	}
    }
    
}
