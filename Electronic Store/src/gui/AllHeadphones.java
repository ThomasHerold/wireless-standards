package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Headphones;
import model.Item;
import util.Constants;
import util.DBUtil;
import util.GUIUtil;
import util.Util;

@SuppressWarnings("serial")
public class AllHeadphones extends JPanel{
	
	private JTable table;
	private DefaultTableModel model;
	

	public AllHeadphones() {
		setLayout(new BorderLayout());
		setBorder(Constants.EMPTY_BORDER);
		
		add(GUIUtil.getTitlePanel("Headphones in Stock"), BorderLayout.NORTH);
		
		model = new DefaultTableModel(10, 5);
		model.setColumnIdentifiers(new Object[]{"Serial", "Brand", "Model", "Color", "Micophone", "Purchase Price", "Sale Price"});
		table = new JTable(model);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		refresh();
	}
	
	public void refresh(){
		model.setRowCount(0);
		ArrayList<Item> items = DBUtil.getAllHeadphones();
		for (Item item : items) {
			if(item.isSold())
				continue;
			Headphones headphones = (Headphones) item;
			model.addRow(new Object[]{headphones.getSerial(), headphones.getBrand(), headphones.getModel(), headphones.getColor(), headphones.isHasMicrophone()? "Yes": "No",Util.formatPrice(headphones.getPurchasePrice()), Util.formatPrice(headphones.getSellingPrice())});
		}
	}
	
	
}
