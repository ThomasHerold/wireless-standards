package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Item;
import model.SimCard;
import util.Constants;
import util.DBUtil;
import util.GUIUtil;
import util.Util;

@SuppressWarnings("serial")
public class AllSimCards extends JPanel{
	
	private JTable table;
	private DefaultTableModel model;
	

	public AllSimCards() {
		setLayout(new BorderLayout());
		setBorder(Constants.EMPTY_BORDER);
		
		add(GUIUtil.getTitlePanel("Sim Cards in Stock"), BorderLayout.NORTH);
		
		model = new DefaultTableModel(10, 5);
		model.setColumnIdentifiers(new Object[]{"ICCID", "Network", "Size", "Purchase Price", "Sale Price"});
		table = new JTable(model);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		refresh();
	}
	
	public void refresh(){
		model.setRowCount(0);
		ArrayList<Item> items = DBUtil.getAllSimCards();
		for (Item item : items) {
			if(item.isSold())
				continue;
			SimCard simCard = (SimCard) item;
			model.addRow(new Object[]{simCard.getIccid(),simCard.getNetwork(),simCard.getSize(), Util.formatPrice(simCard.getPurchasePrice()), Util.formatPrice(simCard.getSellingPrice())});
		}
	}
	
	
}
