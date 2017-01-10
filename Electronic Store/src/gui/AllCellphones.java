package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Cellphone;
import model.Item;
import util.Constants;
import util.DBUtil;
import util.GUIUtil;
import util.Util;

@SuppressWarnings("serial")
public class AllCellphones extends JPanel{
	
	private JTable table;
	private DefaultTableModel model;
	

	public AllCellphones() {
		setLayout(new BorderLayout());
		setBorder(Constants.EMPTY_BORDER);
		
		add(GUIUtil.getTitlePanel("Cellphones in Stock"), BorderLayout.NORTH);
		
		model = new DefaultTableModel(10, 5);
		model.setColumnIdentifiers(new Object[]{"IMEI", "Brand", "Model", "Color", "Purchase Price", "Sale Price"});
		table = new JTable(model);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		refresh();
	}
	
	public void refresh(){
		model.setRowCount(0);
		ArrayList<Item> items = DBUtil.getAllCellphones();
		for (Item item : items) {
			if(item.isSold())
				continue;
			Cellphone cellphone = (Cellphone) item;
			model.addRow(new Object[]{cellphone.getImei(), cellphone.getBrand(), cellphone.getModel(), cellphone.getColor(), Util.formatPrice(cellphone.getPurchasePrice()), Util.formatPrice(cellphone.getSellingPrice())});
		}
	}
	
	
}
