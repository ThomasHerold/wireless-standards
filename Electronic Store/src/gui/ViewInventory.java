package gui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class ViewInventory extends JDialog{

	
	
	public ViewInventory(JFrame parent) {
		super(parent, "Inventory");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		
		tabbedPane.add("All Cellphones", new AllCellphones());
		tabbedPane.add("All Headphones", new AllHeadphones());
		tabbedPane.add("All Sim Cards", new AllSimCards());
		tabbedPane.add("All Transactions", new AllTransactions());
		
		
		add(tabbedPane);
		setSize(800, 500);
		setVisible(true);
		
	}

}
