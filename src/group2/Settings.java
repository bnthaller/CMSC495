package group2;

//import javax.swing.JFrame;.
import java.awt.GridBagLayout;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import group2.model.User;
import group2.model.UserException;
import group2.service.UserService;

import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
//import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Settings extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5370120067904098155L;
	private JTextField txtExpiryLength;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JPasswordField txtNewPassword;
	private User currentUser = null;
	
	public Settings(JDialog parent) {
		super(parent, true);
		setTitle("Top Shelf Settings");

	    this.setSize(351, 208);
	    
	    // maybe change this to be center of parent frame instead of screen
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) ((dimension.getWidth() - getWidth()) / 2), 
				(int) ((dimension.getHeight() - getHeight()) / 2));
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{160, 76, 76, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 16, 0, 14, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(gbl_panel);
		
		JLabel lblFirstName = new JLabel("First Name:");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 1;
		panel.add(lblFirstName, gbc_lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		GridBagConstraints gbc_txtFirstName = new GridBagConstraints();
		gbc_txtFirstName.gridwidth = 2;
		gbc_txtFirstName.insets = new Insets(0, 0, 5, 0);
		gbc_txtFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFirstName.gridx = 1;
		gbc_txtFirstName.gridy = 1;
		panel.add(txtFirstName, gbc_txtFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 2;
		panel.add(lblLastName, gbc_lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.gridwidth = 2;
		gbc_txtLastName.insets = new Insets(0, 0, 5, 0);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 1;
		gbc_txtLastName.gridy = 2;
		panel.add(txtLastName, gbc_txtLastName);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		GridBagConstraints gbc_lblNewPassword = new GridBagConstraints();
		gbc_lblNewPassword.anchor = GridBagConstraints.EAST;
		gbc_lblNewPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewPassword.gridx = 0;
		gbc_lblNewPassword.gridy = 3;
		panel.add(lblNewPassword, gbc_lblNewPassword);
		
		txtNewPassword = new JPasswordField();
		GridBagConstraints gbc_txtNewPassword = new GridBagConstraints();
		gbc_txtNewPassword.gridwidth = 2;
		gbc_txtNewPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtNewPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNewPassword.gridx = 1;
		gbc_txtNewPassword.gridy = 3;
		panel.add(txtNewPassword, gbc_txtNewPassword);
		
		JLabel lblNewLabel = new JLabel("Expiration Warning (days):");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		txtExpiryLength = new JTextField();
		GridBagConstraints gbc_txtExpiryLength = new GridBagConstraints();
		gbc_txtExpiryLength.gridwidth = 2;
		gbc_txtExpiryLength.insets = new Insets(0, 0, 5, 0);
		gbc_txtExpiryLength.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtExpiryLength.gridx = 1;
		gbc_txtExpiryLength.gridy = 4;
		panel.add(txtExpiryLength, gbc_txtExpiryLength);
		txtExpiryLength.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserService userService = new UserService();
				try {
					userService.updateUser(currentUser.getId(), currentUser.getUsername(), 
							txtFirstName.getText(), txtLastName.getText(), 
							String.copyValueOf(txtNewPassword.getPassword()), 
							Integer.parseInt(txtExpiryLength.getText()));
					dispose();
				}
				catch (UserException ex) { 
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				// validate the data entry
				
//				// update the value through userdata
//				UserData.currentUser.setExpiryWarning(Integer.parseInt(tfExpiryLength.getText()));
//				try {
//					UserData.updateUser();
//				}catch(SQLException ex) {
//					JOptionPane.showMessageDialog(null, ex.getMessage());
//				}
			}
		});
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnOK.insets = new Insets(0, 0, 5, 5);
		gbc_btnOK.gridx = 1;
		gbc_btnOK.gridy = 6;
		panel.add(btnOK, gbc_btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 6;
		panel.add(btnCancel, gbc_btnCancel);
		currentUser = UserService.currentUser;
	}
	
	public void setFirstName(String firstName) {
		txtFirstName.setText(firstName);
	}
	public void setLastName(String lastName) {
		txtLastName.setText(lastName);
	}
	public void setExpiryLength(int expiryLength) {
		txtExpiryLength.setText(Integer.toString(expiryLength));
	}
	public void setPassword(String password) {
		txtNewPassword.setText(password);
	}
}
