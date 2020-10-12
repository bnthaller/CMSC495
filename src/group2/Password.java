package group2;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import group2.model.User;
import group2.model.UserException;
import group2.service.UserService;

import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Password extends JDialog {
	private boolean isPasswordValidated = false;
	private User currentUser = null;
	
	public Password(JDialog parent) {
		super(parent, true);
		setTitle("Enter Password");

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		panel.add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		panel.add(passwordField, gbc_passwordField);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// validate the current user/password
				UserService userService = new UserService();
			
				try {
					userService.verifyUser(currentUser.getUsername(), String.copyValueOf(passwordField.getPassword()));
					isPasswordValidated = true;
					dispose();
				}
				catch (UserException ex) {
					isPasswordValidated = false;
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPasswordValidated = false;
				dispose();
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 2;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		this.setSize(new Dimension(250, 100));
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) ((dimension.getWidth() - getWidth()) / 2), 
				(int) ((dimension.getHeight() - getHeight()) / 2));
		
//		System.out.println(UserService.currentUser.getUsername());
		currentUser = UserService.currentUser;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1931804858523028144L;
	private JPasswordField passwordField;
	
	public boolean getIsPasswordValidated() {
		return isPasswordValidated;
	}
	public String getPassword() {
		return String.copyValueOf(passwordField.getPassword());
	}

}
