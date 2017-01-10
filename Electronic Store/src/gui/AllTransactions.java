package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Transaction;
import util.Constants;
import util.DBUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class AllTransactions extends JPanel{
	
	private JTable table;
	private DefaultTableModel model;
	

	public AllTransactions() {
		setLayout(new BorderLayout());
		setBorder(Constants.EMPTY_BORDER);
		
		add(GUIUtil.getTitlePanel("All Transactions"), BorderLayout.NORTH);
		
		model = new DefaultTableModel(10, 5);
		model.setColumnIdentifiers(new Object[]{"Trans. Code", "Name", "Email", "Type", "# Items"});
		table = new JTable(model);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		refresh();
	}
	
	public void refresh(){
		model.setRowCount(0);
		for (Transaction transaction : DBUtil.getAllTransactions()) {
			model.addRow(new Object[] {transaction.getTransactionCode(), transaction.getName(), transaction.getEmail(), transaction.isPurchase()? "Purchase" : "Sold",transaction.getItems().size()});
		}
	}
	
	
}
