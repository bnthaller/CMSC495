package group2;

import java.sql.SQLException;

public class User {
	private int userId;
//	private String username;
//	private String password;
	private int expiryWarningInDays = 5;
	
	public User() {
	}
	
	public boolean logIn(String username, String password) throws SQLException {
		User login = UserData.logIn(username, password);
		if(login != null)
			return true;
		return false;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int id) {
		this.userId = id;
	}
	public int getExpiryWarning() {
		return this.expiryWarningInDays;
	}
	public void setExpiryWarning(int days) {
		this.expiryWarningInDays = days;
	}
	
	
	public boolean updateUser() throws SQLException {
		return UserData.updateUser();
	}
}
