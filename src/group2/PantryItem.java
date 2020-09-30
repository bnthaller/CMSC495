package group2;

import javax.swing.JDialog;
//import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import group2.model.Item;
import group2.service.ItemService;
import group2.service.UserService;
//import javafx.util.converter.DateTimeStringConverter;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
//import java.util.Date;
import java.awt.event.ActionEvent;

public class PantryItem  extends JDialog{
//	public enum Mode {
//		ADD,
//		UPDATE
//	}
//	private Mode mode = Mode.ADD;
	private Item item = null;
	private ItemService itemService = new ItemService();
	private boolean result = false;
	
	public PantryItem(TopShelfGui topShelfGui, Item item) {
		super(topShelfGui, "Pantry Item", true);
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
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
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
		
		txtProductType = new JTextField();
		txtProductType.setColumns(10);
		GridBagConstraints gbc_txtProductType = new GridBagConstraints();
		gbc_txtProductType.gridwidth = 2;
		gbc_txtProductType.insets = new Insets(0, 0, 5, 5);
		gbc_txtProductType.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtProductType.gridx = 1;
		gbc_txtProductType.gridy = 1;
		pMain.add(txtProductType, gbc_txtProductType);
		
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
		gbc_txtQuantity.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuantity.gridx = 1;
		gbc_txtQuantity.gridy = 2;
		pMain.add(txtQuantity, gbc_txtQuantity);
		
		JLabel lblExpiration = new JLabel("Expiration:");
		GridBagConstraints gbc_lblExpiration = new GridBagConstraints();
		gbc_lblExpiration.anchor = GridBagConstraints.EAST;
		gbc_lblExpiration.insets = new Insets(0, 0, 0, 5);
		gbc_lblExpiration.gridx = 0;
		gbc_lblExpiration.gridy = 3;
		pMain.add(lblExpiration, gbc_lblExpiration);
		
		txtExpirationDate = new JTextField();
		txtExpirationDate.setColumns(10);
		GridBagConstraints gbc_txtExpirationDate = new GridBagConstraints();
		gbc_txtExpirationDate.insets = new Insets(0, 0, 0, 5);
		gbc_txtExpirationDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtExpirationDate.gridx = 1;
		gbc_txtExpirationDate.gridy = 3;
		pMain.add(txtExpirationDate, gbc_txtExpirationDate);
		
		JButton btnExpirationDate = new JButton("...");
		GridBagConstraints gbc_btnExpirationDate = new GridBagConstraints();
		gbc_btnExpirationDate.gridx = 2;
		gbc_btnExpirationDate.gridy = 3;
		pMain.add(btnExpirationDate, gbc_btnExpirationDate);
		
		JPanel pButtons = new JPanel();
		getContentPane().add(pButtons, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOK();
			}
		});
		pButtons.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		pButtons.add(btnCancel);
		populate(item);
	}
	
	private void populate(Item item) {
		if(item == null)
			return;
		
		txtName.setText(item.getName());
		txtProductType.setText(Integer.toString(item.getProductType()));
		txtQuantity.setText(Integer.toString(item.getQuantity()));
		txtExpirationDate.setText(item.getExpiryDate().toString());
	}
	
	private void doOK() {
		if(item == null) {
			// try to add the item
			itemService.createItem(txtName.getText(), Integer.parseInt(txtQuantity.getText()),
					LocalDate.parse(txtExpirationDate.getText()), 
					txtProductType.getText(), UserService.currentUser);
			result = true;
			dispose();
		}
		else {
			System.out.print("Updating... ");
			// try to update the item
			itemService.updateItemById(item.getId(), txtName.getText(), Integer.parseInt(txtQuantity.getText()), 
					txtProductType.getText(), LocalDate.parse(txtExpirationDate.getText()), UserService.currentUser);
			System.out.println("done.");
			result = true;
			dispose();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2096133478414933468L;
	private JTextField txtName;
	private JTextField txtProductType;
	private JTextField txtQuantity;
	private JTextField txtExpirationDate;

	public boolean getResult() {
		return result;
	}
	
}
