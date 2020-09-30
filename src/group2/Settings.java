package group2;

//import javax.swing.JFrame;.
import java.awt.GridBagLayout;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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

public class Settings extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5370120067904098155L;
	private JTextField tfExpiryLength;
	
	public Settings(JDialog parent) {
		super(parent, true);
		setTitle("Top Shelf Settings");

	    this.setSize(351, 144);
	    
	    // maybe change this to be center of parent frame instead of screen
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) ((dimension.getWidth() - getWidth()) / 2), 
				(int) ((dimension.getHeight() - getHeight()) / 2));
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{145, 87, 75, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 14, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Expiration Warning (days):");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		tfExpiryLength = new JTextField();
		GridBagConstraints gbc_tfExpiryLength = new GridBagConstraints();
		gbc_tfExpiryLength.insets = new Insets(0, 0, 5, 0);
		gbc_tfExpiryLength.gridwidth = 2;
		gbc_tfExpiryLength.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfExpiryLength.gridx = 1;
		gbc_tfExpiryLength.gridy = 1;
		panel.add(tfExpiryLength, gbc_tfExpiryLength);
		tfExpiryLength.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		gbc_btnOK.gridy = 3;
		panel.add(btnOK, gbc_btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 3;
		panel.add(btnCancel, gbc_btnCancel);
	}
}
