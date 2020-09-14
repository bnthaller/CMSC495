package group2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TopShelf {

	private JFrame frmTopShelf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TopShelf window = new TopShelf();
					if(window.frmTopShelf != null)
						window.frmTopShelf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TopShelf() {
		System.out.println("before login");
		boolean result = (new Login(frmTopShelf, "Login").getLoginResponse());
		if(result)
		{
			System.out.println("login success");
			initialize();
		}
		System.out.println("after login");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTopShelf = new JFrame();
		frmTopShelf.setTitle("Top Shelf");
		frmTopShelf.setBounds(100, 100, 450, 300);
		frmTopShelf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmTopShelf.setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("File");
		menuBar.add(mntmNewMenuItem);
		frmTopShelf.setVisible(true);
	}

}
