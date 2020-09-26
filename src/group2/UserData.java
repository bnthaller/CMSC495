package group2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserData {
	private static Connection sqlConn = null;
	public static User currentUser = null;
	
	public UserData(Connection sqlConn) {
		UserData.sqlConn = sqlConn;
		currentUser = null;
	}
	
	public static User logIn(String username, String password) throws SQLException {
		try {
			// this user/pass is the one to validate against our user table
			String query = String.format("SELECT count(*) as 'matches' FROM topshelf.user " 
					+ "where username='%s' and password='%s'",
					username, password);

			Statement statement = sqlConn.createStatement();	
			ResultSet results =  statement.executeQuery(query);
			
			if (results.next()) {
				int matches = results.getInt("matches");
				if(matches == 1) {
					// create the user and set it the currentUser
					// pull down the id, name, expiry length
					currentUser = null;
					
					return currentUser;
				}
				else
					return null;
			}
		}
		catch (SQLException e) {
			throw e;
		}
		return null;
	}
	
	public static boolean updateUserExpirationWarning() {
		return true;
	}
	
	public static boolean updateUser() throws SQLException {
		String query = String.format("UPDATE user"
				+ " SET expiry_length=" + currentUser.getExpiryWarning()
				+ " WHERE user_id=" + currentUser.getUserId());

		Statement statement = sqlConn.createStatement();	
		return statement.executeUpdate(query) == 0;		
	}
}
