package group2;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

//import group2.data.UserDAO;
import group2.model.Item;
import group2.model.ItemException;
import group2.service.ItemService;
import group2.service.UserService;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
//import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class TopShelfGui extends JDialog implements ActionListener  {
	private static final long serialVersionUID = -8617864607802138782L;
	

	String[] columns = {"ID", "Name", "Product Type", "Quantity", "Expiration Date"};
//	private Connection sqlConn = null;
	private boolean isLogOut = false;
	private JTable table;
	JButton btnDeleteItem;
	JButton btnUpdateItem;
	ItemService itemService = new ItemService();
	
	try {
		ItemTableModel itemTableModel = new ItemTableModel();
	} catch (ItemException ex) {
		JOptionPane.showMessageDialog(this, ex.getMessage());
	}

	public TopShelfGui(JFrame parent) {
		super(parent, "Top Shelf", true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mntmSettings.addActionListener(this);
		
		mnNewMenu.add(mntmSettings);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isLogOut = true;
				dispose();
			}
		});
		mnNewMenu.add(mntmLogOut);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isLogOut = false;
				dispose();
			}
		});
		mnNewMenu.add(mntmExit);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(.2);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

//		String[][] data = { 
//				{ "1", "Item1", "Dairy", 	"16", "9/27/2020" },
//				{ "2", "Item2", "Produce", 	"27", "9/27/2020" },
//				{ "3", "Item3", "Meat", 		"36", "9/27/2020" },
//				{ "4", "Item4", "Dairy", 	"46", "9/27/2020" },
//				{ "5", "Item5", "Bread", 	"33", "9/27/2020" },
//				{ "6", "Item6", "Alcohol", 	"73", "9/27/2020" },
//				
//		};
//		table = new JTable(null, columns);
		table = new JTable(itemTableModel);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selected = table.getSelectedRow();
				System.out.println(selected);
				System.out.println("selection changed");
				setButtonState(btnDeleteItem, selected != -1);
				setButtonState(btnUpdateItem, selected != -1);
					
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		scrollPane.setRowHeaderView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
				
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PantryItem dlg = createPantryItemDialog(null);
				dlg.setVisible(true);
				if(dlg.getResult()) {
					try {
						refreshData();
					} catch (ItemException ex) {
						JOptionPane.showMessageDialog(parent, ex.getMessage());
					}
				}
				else {
//					System.out.println("false");
					
				}
			}
		});
		panel_1.add(btnAddItem);
		
		btnUpdateItem = new JButton("Update Item");
		btnUpdateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selected = table.getSelectedRow();
				int id = (int)table.getValueAt(selected, 0);
				
				try {
					Item selectedItem = itemService.getItemByItemId(id);
					System.out.println("id " + selectedItem.getId());
					PantryItem dlg = createPantryItemDialog(selectedItem);
					dlg.setVisible(true);
					if(dlg.getResult())
						refreshData();
				} catch (ItemException ex) {
					JOptionPane.showMessageDialog(parent, ex.getMessage());
				}
			}
		});
		panel_1.add(btnUpdateItem);
		setButtonState(btnUpdateItem, false);
		
		btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected = table.getSelectedRow();
				int id = (int)table.getValueAt(selected, 0);
				System.out.println(selected);
				
				try {
					itemService.deleteItemById(id, UserService.currentUser);
					refreshData();
				} catch (ItemException ex) {
					JOptionPane.showMessageDialog(parent, ex.getMessage());
				}
//				if(dlg.getResult())
//					refreshData();
			}
		});
		setButtonState(btnDeleteItem, false);
		panel_1.add(btnDeleteItem);
		btnDeleteItem.setMaximumSize(new Dimension(300, btnDeleteItem.getPreferredSize().height));
		
		JLabel label = new JLabel("");
		panel_1.add(label);
		
		JLabel lblProductType = new JLabel("Product Type:", SwingConstants.LEFT);
		panel_1.add(lblProductType);
		
		JComboBox<String> cbProductType = new JComboBox<String>();
		panel_1.add(cbProductType);
		cbProductType.addItem("All");
		
		JLabel lblSortBy = new JLabel("Sort By:", SwingConstants.LEFT);
		panel_1.add(lblSortBy);
		
		JComboBox<String> cbSortBy = new JComboBox<String>();
		panel_1.add(cbSortBy);
		cbSortBy.addItem("Name");
		cbSortBy.addItem("Product Type");
		cbSortBy.addItem("Quantity");
		cbSortBy.addItem("Expiration Date");
		JLabel label_1 = new JLabel("");
		panel_1.add(label_1);
		
	    this.setSize(800, 600);
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) ((dimension.getWidth() - getWidth()) / 2), 
				(int) ((dimension.getHeight() - getHeight()) / 2));
		
//		table.getModel().addTableModelListener(this);
		
	}
	
	private PantryItem createPantryItemDialog(Item item) {
		PantryItem dlg = new PantryItem(this, item);
		return dlg;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Display settings...");
		
		Settings s = new Settings(this);
		s.setVisible(true);
	}
	
	private void setButtonState(JButton btn, boolean enabled) {
		btn.setEnabled(enabled);
	}

	public boolean getIsLogOut() {
		return isLogOut;
	}
	
	private void refreshData() throws ItemException {
		try {
			itemTableModel.getData();
		} catch (ItemException itemException) {
			throw itemException;
		}
	}
	
//    public void tableChanged(TableModelEvent e) {
//        int row = e.getFirstRow();
//        int column = e.getColumn();
//        TableModel model = (TableModel)e.getSource();
//        String columnName = model.getColumnName(column);
//        Object data = model.getValueAt(row, column);
//        System.out.println("changed");
//    }
//	private void showItems() {
////		table = new JTable(items, columns);
//	}
}

class ItemTableModel extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3214768094639730363L;
	private String[] columnNames = {"ID", "Name", "Product Type", "Quantity", "Expiration Date"};
    private Object[][] data = null;
    private ItemService itemService = new ItemService();
    
    public ItemTableModel() throws ItemException {
    	try {
    		getData();
    	} catch (ItemException itemException) {
    		throw itemException;
    	}
    }
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    public void getData() throws ItemException {
    	try {
			List<Item> items =  itemService.getItems(UserService.currentUser);
	
	    	data = new Object[items.size()][5];
	    	for(int i = 0; i < items.size(); ++i) {
	    		System.out.println(items.get(i).getName());
	    		data[i][0] = items.get(i).getId();
	    		data[i][1] = items.get(i).getName();
	    		data[i][2] = items.get(i).getProductType();
	    		data[i][3] = items.get(i).getQuantity();
	    		data[i][4] = items.get(i).getExpiryDate();
	    	}
	    	fireTableDataChanged();
    	} catch (ItemException itemException) {
    		throw itemException;
    	}
    }
    
}
