package group2;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
//import java.sql.Connection;
import javax.swing.JRadioButton;
import group2.model.User;
import group2.model.UserException;
import group2.service.UserService;

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
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtExpiryWarning;
//	Connection sqlConn = null;
	private UserService userService = new UserService();

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
//		this.sqlConn = sqlConn;
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
		
		JPanel pButtons = new JPanel();
		this.getContentPane().add(pButtons, BorderLayout.SOUTH);
		
		btnOK.addActionListener(this);

		pButtons.add(btnOK);
		
		btnCancel.addActionListener(this);
		pButtons.add(btnCancel);
		
		JPanel pFields = new JPanel();
		pFields.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.getContentPane().add(pFields, BorderLayout.NORTH);
		GridBagLayout gbl_pFields = new GridBagLayout();
		gbl_pFields.columnWidths = new int[]{0, 0, 0};
		gbl_pFields.rowHeights = new int[]{0, 0, 0};
		gbl_pFields.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pFields.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pFields.setLayout(gbl_pFields);
		
		JLabel lblNewLabel = new JLabel("Username:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		pFields.add(lblNewLabel, gbc_lblNewLabel);
		
		txtUsername = new JTextField();
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 0;
		pFields.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		pFields.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		
		
		
		
		
		
		txtPassword = new JPasswordField();
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 1;
		pFields.add(txtPassword, gbc_txtPassword);
		
		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 2;
		pFields.add(lblFirstName, gbc_lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 2;
		pFields.add(txtFirstName, gbc_txtFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 3;
		pFields.add(lblLastName, gbc_lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.insets = new Insets(0, 0, 5, 0);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 1;
		gbc_txtLastName.gridy = 3;
		pFields.add(txtLastName, gbc_txtLastName);
		
		JLabel lblExpiryWarning = new JLabel("Expiry Warning:");
		GridBagConstraints gbc_lblExpiryWarning = new GridBagConstraints();
		gbc_lblExpiryWarning.anchor = GridBagConstraints.EAST;
		gbc_lblExpiryWarning.insets = new Insets(0, 0, 0, 5);
		gbc_lblExpiryWarning.gridx = 0;
		gbc_lblExpiryWarning.gridy = 4;
		pFields.add(lblExpiryWarning, gbc_lblExpiryWarning);
		
		txtExpiryWarning = new JTextField();
		txtExpiryWarning.setColumns(10);
		GridBagConstraints gbc_txtExpiryWarning = new GridBagConstraints();
		gbc_txtExpiryWarning.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtExpiryWarning.gridx = 1;
		gbc_txtExpiryWarning.gridy = 4;
		pFields.add(txtExpiryWarning, gbc_txtExpiryWarning);
		
		
		JPanel pOptions = new JPanel();
		getContentPane().add(pOptions, BorderLayout.CENTER);
		
		
		lblFirstName.setVisible(false);
		txtFirstName.setVisible(false);
		lblLastName.setVisible(false);
		txtLastName.setVisible(false);
		lblExpiryWarning.setVisible(false);
		txtExpiryWarning.setVisible(false);
	    setWindowSize(315, 175);
		
		
		rbLogIn = new JRadioButton("Log In");
		rbLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("selected log in");
				lblFirstName.setVisible(false);
				txtFirstName.setVisible(false);
				lblLastName.setVisible(false);
				txtLastName.setVisible(false);
				lblExpiryWarning.setVisible(false);
				txtExpiryWarning.setVisible(false);
			    setWindowSize(315, 175);
				btnOK.setText("Log In");
			}
		});
		rbLogIn.setSelected(true);
		pOptions.add(rbLogIn);
		
		rbRegister = new JRadioButton("Register");
		rbRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("selected register");				
				lblFirstName.setVisible(true);
				txtFirstName.setVisible(true);
				lblLastName.setVisible(true);
				txtLastName.setVisible(true);
				lblExpiryWarning.setVisible(true);
				txtExpiryWarning.setVisible(true);
			    setWindowSize(315, 256);
				btnOK.setText("Register");
			}
		});
		pOptions.add(rbRegister);
		
		ButtonGroup g = new ButtonGroup();
		g.add(rbLogIn);
		g.add(rbRegister);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOK) {
			try {
				// log in or register?
				if(rbLogIn.isSelected()) {
					// call the User service to log in
					User user = userService.verifyUser(txtUsername.getText(), 
						String.copyValueOf(txtPassword.getPassword()));
					
					loginSuccessful = true;
					this.dispose();
				}
				else if(rbRegister.isSelected()) {
					// let's pretend everything checks out
				
					System.out.print("Creating user... ");
					User user = userService.createUser(txtUsername.getText(), 
							txtFirstName.getText(), 
							txtLastName.getText(),
							String.copyValueOf(txtPassword.getPassword()), 
							Integer.parseInt(txtExpiryWarning.getText()));
					System.out.println("done.");
					
					loginSuccessful = true;
					this.dispose();
				}
			} catch (UserException ex) {
				loginSuccessful = false;
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
		else if (e.getSource() == btnCancel) {
			this.dispose();
		}
	}
	
	public boolean getLoginResponse() {
		return loginSuccessful;
	}

	private void setWindowSize(int width, int height) {
		this.setSize(width, height);
	}
}
