package group2;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


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
//				Connection sqlConn = null;
				
				try {
					// create the sql connection
					sqlConn = DriverManager.getConnection(dbUrl, "root", "password");
					
					// show the login screen until the user "exits" the application
					// if the user "logs out", the login screen will be presented again
					boolean showLoginScreen = false;
					do {
						// reset this boolean if the user logged out (convoluted)
						showLoginScreen = false;
						
						System.out.println("Showing login screen... ");
						boolean loginSuccess = (new Login(rootFrame, "Login", sqlConn).getLoginResponse());

						if(loginSuccess) {
							TopShelfGui g = new TopShelfGui(rootFrame, sqlConn);
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
