package group2;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import group2.service.UserService;


public class Application {
	private static String dbUrl = "jdbc:mysql://localhost/topshelf";
	private static JFrame rootFrame = null;
	public static Connection sqlConn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {				
				try {
					// create the sql connection
					sqlConn = DriverManager.getConnection(dbUrl, "user", "*C06327039E918D3247E4438D3785C723719DC8B5");
					
					// show the login screen until the user "exits" the application
					// if the user "logs out", the login screen will be presented again
					boolean showLoginScreen = false;
					do {
						// reset this boolean if the user logged out (convoluted)
						showLoginScreen = false;
						
						boolean loginSuccess = (new Login(rootFrame, "Login").getLoginResponse());

						if(loginSuccess) {
							System.out.println("Welcome to Top Shelf, " + UserService.currentUser.getFirstName() + " " + UserService.currentUser.getLastName());
							TopShelfGui g = new TopShelfGui(rootFrame);
							g.setVisible(true);
							
							if(g.getIsLogOut()) {
								showLoginScreen = true;
							}
							else {
								showLoginScreen = false;
							}
						}
						
					} while(showLoginScreen);
					
				}
				catch(SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				finally {
					try {
						System.out.print("Closing SQL connection... ");
						sqlConn.close();
						System.out.println("done.");
					}
					catch(Exception e) {
						// do something?
					}
				}
				System.out.println("Exiting...");
			}
		});
	}
}
