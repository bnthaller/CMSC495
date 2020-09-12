package group2;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JDialog implements ActionListener {

//	private JDialog frmTopShelfLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private static final long serialVersionUID = 98599739738L;
	private boolean loginSuccessful = false;
	JButton btnLogIn = new JButton("Log In");
	JButton btnCancel = new JButton("Cancel");

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login window = new Login();
//					window.frmTopShelfLogin.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Login(JFrame parentFrame, String title) {
		super(parentFrame, title, true);
		initialize();
		this.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		this = new JDialog();
		this.setResizable(false);
		this.setTitle("Top Shelf Login");
		this.setBounds(100, 100, 315, 126);
//		frmTopShelfLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		this.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		btnLogIn.addActionListener(this);

		panel_1.add(btnLogIn);
		
		btnCancel.addActionListener(this);
		panel_1.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Username:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 0;
		panel.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtPassword = new JPasswordField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 1;
		panel.add(txtPassword, gbc_txtPassword);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogIn) {
			try {
				if(validateUser(txtUsername.getText(), String.copyValueOf(txtPassword.getPassword()))) {
					loginSuccessful = true;
					this.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Invalid username/password.");
				}
			}		
			catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
		else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
	
	private boolean validateUser(String username, String password) throws Exception{
		String url = "jdbc:mysql://localhost/topshelf";

		try {
			// root/cmsc495 is the user/pass to connect to the database
			Connection conn = DriverManager.getConnection(url, "root", "cmsc495");
			
			// this user/pass is the one to validate against our user table
			String query = String.format("SELECT count(*) as 'matches' FROM topshelf.user " 
					+ "where username='%s' and password='%s'",
					username, password);

			Statement statement = conn.createStatement();	
			ResultSet results =  statement.executeQuery(query);
			
			if (results.next()) {
				int matches = results.getInt("matches");
				if(matches == 1)
					return true;
				else
					return false;
			}
		}
		catch (Exception e) {
			throw e;
		}
		return false;
	}
	
	public boolean getLoginResponse() {
		return loginSuccessful;
	}

}
