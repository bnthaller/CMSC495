package group2;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import group2.model.Item;
import group2.model.ItemException;
import group2.model.ItemStatus;
import group2.service.ItemService;
import group2.service.UserService;

public class TopShelfGui extends JDialog implements ActionListener  {
	private static final long serialVersionUID = -8617864607802138782L;
	
	private JTable table;
	private JButton btnDeleteItem;
	private JButton btnUpdateItem;
	private ItemTableModel itemTableModel = new ItemTableModel();
	private TableRowSorter<TableModel> sorter = null;
	private List<RowSorter.SortKey> sortKeys = new ArrayList<>(1);
	
	private boolean isLogOut = false;
	private String filterValue = "";

	private ItemService itemService = new ItemService();
	
	public TopShelfGui(JFrame parent) {
		super(parent, "Top Shelf", true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSettings = new JMenuItem("User Settings");
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
		
		JMenu mnNewMenu_1 = new JMenu("Help");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("About");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
						"Top Shelf v1.0\n\nBrett Day\nBrent Hall\nDavid Im\nChristopher Williams", 
						"About Top Shelf", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(.2);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		table = new JTable(itemTableModel);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int selected = table.getSelectedRow();
//				System.out.println(selected);
//				System.out.println("selection changed");
				setButtonState(btnDeleteItem, selected != -1);
				setButtonState(btnUpdateItem, selected != -1);
					
			}
		});      
		table.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	             if (me.getClickCount() == 2) {
	                JTable target = (JTable)me.getSource();
	                int selected = target.getSelectedRow(); // select a row
					int id = (int)table.getValueAt(selected, 0);
					
					try {
						Item selectedItem = itemService.getItemByItemId(id);
						System.out.println("id " + selectedItem.getId());
						PantryItem dlg = createPantryItemDialog(selectedItem);
						dlg.setVisible(true);
						if(dlg.getResult())
							refreshData(filterValue);
					} catch (ItemException ex) {
						JOptionPane.showMessageDialog(parent, ex.getMessage());
					}
	             }
	          }
	       });
		
		
		scrollPane.setViewportView(table);

		TableColumnModel tcm = table.getColumnModel();
		TableColumn tm = tcm.getColumn(4);
		tm.setCellRenderer(new ColumnColorRenderer());
		
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
				if(dlg.getResult()) 
					refreshData(filterValue);
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
						refreshData(filterValue);
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
				int[] selected = table.getSelectedRows();

				int dialogResult = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to delete " + selected.length + " items?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					try {
						for(int s : selected) {
							int id = (int)table.getValueAt(s, 0);
							System.out.println(selected);
							
								itemService.deleteItemById(id, UserService.currentUser);
							
						}
					} catch (ItemException ex) {
						JOptionPane.showMessageDialog(parent, ex.getMessage());
					}
					refreshData(filterValue);
				}

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
		cbProductType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filterValue = String.valueOf(cbProductType.getSelectedItem());
				refreshData(filterValue);
			}
		});
		
		panel_1.add(cbProductType);
		cbProductType.addItem("All");
		
		try {
			List<String> productTypes = itemService.getProductTypes();
			
			for (String pt : productTypes) {
				cbProductType.addItem(pt);
			}
		} catch (ItemException itemException) {
			JOptionPane.showMessageDialog(parent, itemException.getMessage());
		}
				
		JLabel lblSortBy = new JLabel("Sort By:", SwingConstants.LEFT);
		lblSortBy.setVisible(false);
		panel_1.add(lblSortBy);

		sorter = new TableRowSorter<TableModel>(itemTableModel);
		table.setRowSorter(sorter);
		
		JComboBox<String> cbSortBy = new JComboBox<String>();
		cbSortBy.setVisible(false);
		cbSortBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sortKeys.clear();
				sortKeys.add(new RowSorter.SortKey(cbSortBy.getSelectedIndex(), SortOrder.ASCENDING));
				sorter.setSortKeys(sortKeys);
			}
		});
		
		panel_1.add(cbSortBy);
		cbSortBy.addItem("ID");
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
		
		boolean showExpiredMessage = false;
		boolean showExpiringMessage = false;
		
		try {
			List<Item> items = itemService.getItems(UserService.currentUser, filterValue);
			StringBuilder expiredItemsNotice = new StringBuilder();
			StringBuilder expiringItemsNotice = new StringBuilder();
			
			for (Item item : items) {
				if (item.getStatus() == ItemStatus.EXPIRED) {
					showExpiredMessage = true;
					if (expiredItemsNotice.length() > 0) {
						expiredItemsNotice.append(", ");
					}
					expiredItemsNotice.append(item.getName());
				} else if (item.getStatus() == ItemStatus.EXPIRING) {
					showExpiringMessage = true;
					if (expiringItemsNotice.length() > 0) {
						expiringItemsNotice.append(", ");
					}
					expiringItemsNotice.append(item.getName());
				}
			}
						
			if (showExpiredMessage) {
				JOptionPane.showMessageDialog(parent, "<html><body><p style='width: 400px;'>Expired Items: " + expiredItemsNotice.toString() + "</p></body></html>");
			}
			
			if (showExpiringMessage) {
				JOptionPane.showMessageDialog(parent, "<html><body><p style='width: 400px;'>Expiring Items: " + expiringItemsNotice.toString() + "</p></body></html>");
			}
		} catch (ItemException itemException) {
			JOptionPane.showMessageDialog(parent, "Unable to load Items.");
		}
		
	}
	
	private PantryItem createPantryItemDialog(Item item) {
		PantryItem dlg = new PantryItem(this, item);
		return dlg;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		Password dlg = new Password(this);
		dlg.setVisible(true);
		
		if(dlg.getIsPasswordValidated()) {
			System.out.println("Displaying settings...");
			Settings s = new Settings(this);
			s.setFirstName(UserService.currentUser.getFirstName());
			s.setLastName(UserService.currentUser.getLastName());
			s.setExpiryLength(UserService.currentUser.getExpiryLength());
			s.setPassword(dlg.getPassword());
			s.setVisible(true);
		}
	}
	
	private void setButtonState(JButton btn, boolean enabled) {
		btn.setEnabled(enabled);
	}

	public boolean getIsLogOut() {
		return isLogOut;
	}
	
	private void refreshData(String filterValue) {
		itemTableModel.getData(filterValue);

	}

}

class ItemTableModel extends AbstractTableModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3214768094639730363L;
	
	private String[] columnNames = {"ID", "Name", "Product Type", "Quantity", "Expiration Date"};
    private Object[][] data = null;
    private ItemService itemService = new ItemService();
    @SuppressWarnings("rawtypes")
	Class[] types = { Integer.class, String.class, String.class, Integer.class, LocalDate.class };

    public ItemTableModel() {
    	getData("");
    }
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
    	if(data == null)
    		return 0;
        return data.length;
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
//	@Override
    public Class<?> getColumnClass(int columnIndex) {
    	return this.types[columnIndex];
    }
    public void getData(String filterValue) {
    	try {
			List<Item> items =  itemService.getItems(UserService.currentUser, filterValue);
	
	    	data = new Object[items.size()][5];
	    	for(int i = 0; i < items.size(); ++i) {
//	    		System.out.println(items.get(i).getName());
	    		data[i][0] = items.get(i).getId();
	    		data[i][1] = items.get(i).getName();
	    		data[i][2] = items.get(i).getProductType();
	    		data[i][3] = items.get(i).getQuantity();
	    		data[i][4] = items.get(i).getExpiryDate();
	    	}

	    	fireTableDataChanged();
    	} catch (ItemException itemException) {
    		//throw itemException;
    	}
    }
    
}

class ColumnColorRenderer extends DefaultTableCellRenderer {
	   /**
	 * 
	 */
	private static final long serialVersionUID = -7863595639530831929L;
	   
	public ColumnColorRenderer() {
      super();
   }
	    
   public Component getTableCellRendererComponent (JTable table, Object value, 
		   boolean selected, boolean focused, int row, int column) {
        setEnabled(table == null || table.isEnabled()); // see question above

        LocalDate expiryDate = (LocalDate)value;
        
        if(expiryDate.isBefore(LocalDate.now())) {
        	setBackground(java.awt.Color.red);
        }
        else if(expiryDate.minusDays(UserService.currentUser.getExpiryLength()).isBefore(LocalDate.now())) {
        	setBackground(java.awt.Color.orange);
        }else {
        	setBackground(java.awt.Color.white);
        }

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        return this;
    }
}
