package group2;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.sql.Connection;
import javax.swing.JRadioButton;

public class Login extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7706773593143792961L;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JRadioButton rbLogIn;
	private JRadioButton rbRegister;
	
	private boolean loginSuccessful = false;
	JButton btnOK = new JButton("Log In");
	JButton btnCancel = new JButton("Cancel");
	Connection sqlConn = null;

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
	public Login(JFrame parentFrame, String title, Connection sqlConn) {
		super(parentFrame, title, true);
		this.sqlConn = sqlConn;
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
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setSize(315, 161);
		this.setLocation((int) ((dimension.getWidth() - getWidth()) / 2), 
				(int) ((dimension.getHeight() - getHeight()) / 2));
//		frmTopShelfLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		this.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		btnOK.addActionListener(this);

		panel_1.add(btnOK);
		
		btnCancel.addActionListener(this);
		panel_1.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.getContentPane().add(panel, BorderLayout.NORTH);
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
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		
		rbLogIn = new JRadioButton("Log In");
		rbLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("selected log in");
				btnOK.setText("Log In");
			}
		});
		rbLogIn.setSelected(true);
		panel_2.add(rbLogIn);
		
		rbRegister = new JRadioButton("Register");
		rbRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("selected register");
				btnOK.setText("Register");
			}
		});
		panel_2.add(rbRegister);
		
		ButtonGroup g = new ButtonGroup();
		g.add(rbLogIn);
		g.add(rbRegister);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOK) {
			
			// log in or register?
			if(rbLogIn.isSelected()) {
				// call the User service to log in
				// let's just pretend that the credentials are valid
				loginSuccessful = true;
				this.dispose();
//				try {
//					if(validateUser(txtUsername.getText(), String.copyValueOf(txtPassword.getPassword()))) {
//						loginSuccessful = true;
//						this.dispose();
//					}
//					else
//					{
//						JOptionPane.showMessageDialog(this, "Invalid username/password.");
//					}
//				}		
//				catch (Exception ex) {
//					JOptionPane.showMessageDialog(this, ex.getMessage());
//				}
			}
			else if(rbRegister.isSelected()) {
				// let's pretend everything checks out
				loginSuccessful = true;
				this.dispose();
			}
					
		}
		else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
	
//	private boolean validateUser(String username, String password) throws Exception{
//		String url = "jdbc:mysql://localhost/topshelf";
//
//		try {
//			// root/cmsc495 is the user/pass to connect to the database
////			Connection conn = DriverManager.getConnection(url, "root", "cmsc495");
//			
//			// this user/pass is the one to validate against our user table
//			String query = String.format("SELECT count(*) as 'matches' FROM topshelf.user " 
//					+ "where username='%s' and password='%s'",
//					username, password);
//
//			Statement statement = sqlConn.createStatement();	
//			ResultSet results =  statement.executeQuery(query);
//			
//			if (results.next()) {
//				int matches = results.getInt("matches");
//				if(matches == 1)
//					return true;
//				else
//					return false;
//			}
//		}
//		catch (Exception e) {
//			throw e;
//		}
//		return false;
//	}
	
	public boolean getLoginResponse() {
		return loginSuccessful;
	}

}
