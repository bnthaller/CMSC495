package group2;

import java.util.List;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import group2.model.Item;
import group2.model.ItemException;
import group2.service.ItemService;
import group2.service.UserService;
//import javafx.util.converter.DateTimeStringConverter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class PantryItem  extends JDialog{
	JButton btnOK;
	private Item item = null;
	private ItemService itemService = new ItemService();
	private boolean result = false;
	JComboBox<String> cbProductType = null;
	JFormattedTextField txtExpirationDate = null;
	
	public PantryItem(TopShelfGui topShelfGui, Item item) {
		super(topShelfGui, "Pantry Item", true);
		getRootPane().setDefaultButton(btnOK);
		
		this.item = item;

	    this.setSize(300, 200);
	    
		JPanel pMain = new JPanel();
		getContentPane().add(pMain, BorderLayout.CENTER);
		pMain.setBorder(new EmptyBorder(10, 10, 10, 10));
		GridBagLayout gbl_pMain = new GridBagLayout();
		gbl_pMain.columnWidths = new int[]{0, 185, 34, 0};
		gbl_pMain.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pMain.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pMain.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pMain.setLayout(gbl_pMain);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		pMain.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.gridwidth = 2;
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 1;
		gbc_txtName.gridy = 0;
		pMain.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblProductType = new JLabel("Product Type:");
		GridBagConstraints gbc_lblProductType = new GridBagConstraints();
		gbc_lblProductType.anchor = GridBagConstraints.EAST;
		gbc_lblProductType.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductType.gridx = 0;
		gbc_lblProductType.gridy = 1;
		pMain.add(lblProductType, gbc_lblProductType);
		
		cbProductType = new JComboBox<String>();
		GridBagConstraints gbc_cbProductType = new GridBagConstraints();
		gbc_cbProductType.gridwidth = 2;
		gbc_cbProductType.insets = new Insets(0, 0, 5, 0);
		gbc_cbProductType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbProductType.gridx = 1;
		gbc_cbProductType.gridy = 1;
		
		
		try {
			List<String> productTypes = itemService.getProductTypes();
			cbProductType.setModel(new DefaultComboBoxModel<String>(productTypes.toArray(new String[0])));
		}
		catch (ItemException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
		
		pMain.add(cbProductType, gbc_cbProductType);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 2;
		pMain.add(lblQuantity, gbc_lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setColumns(10);
		GridBagConstraints gbc_txtQuantity = new GridBagConstraints();
		gbc_txtQuantity.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQuantity.gridwidth = 2;
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 0);
		gbc_txtQuantity.gridx = 1;
		gbc_txtQuantity.gridy = 2;
		pMain.add(txtQuantity, gbc_txtQuantity);
		
		JLabel lblExpiration = new JLabel("Expiration (yyyy-MM-dd):");
		GridBagConstraints gbc_lblExpiration = new GridBagConstraints();
		gbc_lblExpiration.anchor = GridBagConstraints.EAST;
		gbc_lblExpiration.insets = new Insets(0, 0, 0, 5);
		gbc_lblExpiration.gridx = 0;
		gbc_lblExpiration.gridy = 3;
		pMain.add(lblExpiration, gbc_lblExpiration);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		txtExpirationDate = new JFormattedTextField(format);
		GridBagConstraints gbc_txtExpirationDate = new GridBagConstraints();
		gbc_txtExpirationDate.gridwidth = 2;
		gbc_txtExpirationDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtExpirationDate.gridx = 1;
		gbc_txtExpirationDate.gridy = 3;
		pMain.add(txtExpirationDate, gbc_txtExpirationDate);
		
		JPanel pButtons = new JPanel();
		getContentPane().add(pButtons, BorderLayout.SOUTH);
		
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOK();
			}
		});
		pButtons.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		pButtons.add(btnCancel);
		populate(item);

	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) ((dimension.getWidth() - getWidth()) / 2), 
				(int) ((dimension.getHeight() - getHeight()) / 2));
	}
	
	private void populate(Item item) {
		if(item == null)
			return;
		
		txtName.setText(item.getName());
		txtQuantity.setText(Integer.toString(item.getQuantity()));
		txtExpirationDate.setText(item.getExpiryDate().toString());
		cbProductType.setSelectedItem(item.getProductType());
	}
	
	private void doOK() {
		try {
			if(areFieldsEmpty()) {
				JOptionPane.showMessageDialog(this, "Name, product type, quantity, and expiration fields are required.");
				return;
			}
			
			if(item == null) {
				// try to add the item
				itemService.createItem(txtName.getText(), Integer.parseInt(txtQuantity.getText()),
						LocalDate.parse(txtExpirationDate.getText()), 
						cbProductType.getSelectedItem().toString(), UserService.currentUser);
				result = true;
				dispose();
			}
			else {
//				System.out.print("Updating... ");
				// try to update the item
				itemService.updateItemById(item.getId(), txtName.getText(), Integer.parseInt(txtQuantity.getText()), 
						cbProductType.getSelectedItem().toString(), LocalDate.parse(txtExpirationDate.getText()), UserService.currentUser);
//				System.out.println("done.");
				result = true;
				dispose();
			}
		} catch (ItemException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}

	private boolean areFieldsEmpty() {
		System.out.println(txtExpirationDate.getText().equals(""));
		System.out.println(txtName.getText().equals(""));
		System.out.println(txtQuantity.getText().equals(""));
		return txtExpirationDate.getText().equals("") || txtName.getText().equals("") 
				|| txtQuantity.getText().equals("");
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2096133478414933468L;
	private JTextField txtName;
	private JTextField txtQuantity;

	public boolean getResult() {
		return result;
	}
	
}
