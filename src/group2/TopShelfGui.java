package group2;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.sql.Connection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TopShelfGui extends JDialog implements ActionListener {
	private static final long serialVersionUID = -8617864607802138782L;
//	private Connection sqlConn = null;
	private boolean isLogOut = false;
	private JTable table;
	

	public TopShelfGui(JFrame parent, Connection sqlConn) {
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
		
		
		JPanel pOptions = new JPanel();
		splitPane.setLeftComponent(pOptions);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		pOptions.setLayout(new GridBagLayout());
		
		JLabel lblProductType = new JLabel("Product Type:", SwingConstants.LEFT);
		pOptions.add(lblProductType, gbc);
		
		JComboBox<String> cbProductType = new JComboBox<String>();
		cbProductType.addItem("All");
		cbProductType.setMaximumSize(new Dimension(300, cbProductType.getPreferredSize().height));
		pOptions.add(cbProductType, gbc);
		
		JLabel lblSortBy = new JLabel("Sort By:", SwingConstants.LEFT);
		pOptions.add(lblSortBy, gbc);
		
		JComboBox<String> cbSortBy = new JComboBox<String>();
		cbSortBy.addItem("Name");
		cbSortBy.addItem("Product Type");
		cbSortBy.addItem("Quantity");
		cbSortBy.addItem("Expiration Date");
		cbSortBy.setMaximumSize(new Dimension(300, cbSortBy.getPreferredSize().height));
		pOptions.add(cbSortBy, gbc);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		String[] columns = {"Name", "Product Type", "Quantity", "Expiration Date"};
		String[][] data = { 
				{ "Item1", "Dairy", 	"16", "9/27/2020" },
				{ "Item2", "Produce", 	"27", "9/27/2020" },
				{ "Item3", "Meat", 		"36", "9/27/2020" },
				{ "Item4", "Dairy", 	"46", "9/27/2020" },
				{ "Item5", "Bread", 	"33", "9/27/2020" },
				{ "Item6", "Alcohol", 	"73", "9/27/2020" },
				
		};
		table = new JTable(data, columns);
		scrollPane.setViewportView(table);
		
	    this.setSize(800, 600);
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((int) ((dimension.getWidth() - getWidth()) / 2), 
				(int) ((dimension.getHeight() - getHeight()) / 2));
		
//		this.sqlConn = sqlConn;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Display settings...");
		
		Settings s = new Settings(this);
		s.setVisible(true);
	}
	
	public boolean getIsLogOut() {
		return isLogOut;
	}
}
