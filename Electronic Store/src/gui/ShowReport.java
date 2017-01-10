package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Constants;
import util.DBUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class ShowReport extends JDialog{

	
	public ShowReport(JFrame parent) {
		super(parent, "Report");
		setModalityType(ModalityType.DOCUMENT_MODAL);
		
		add(GUIUtil.getTitlePanel("Sales Report"), BorderLayout.NORTH);
		
		add(pnlReport());

		
		pack();
		setVisible(true);
		
		
	}
	
	public JPanel pnlReport(){
		JPanel pnl = new JPanel(new GridLayout(6, 2, 10, 10));
		pnl.setBorder(Constants.EMPTY_BORDER);
		
		pnl.add(GUIUtil.formattedLabel( new JLabel("Total Sales")));
		pnl.add(GUIUtil.formattedTextField( new JTextField(Constants.currencyFormat.format(DBUtil.totalSales()))));
		
		pnl.add(GUIUtil.formattedLabel( new JLabel("Total Purchases")));
		pnl.add(GUIUtil.formattedTextField( new JTextField(Constants.currencyFormat.format(DBUtil.totalPurchases()))));
	
		double profitLoss = DBUtil.totalProfitLoss();
		if(profitLoss > 0)
			pnl.add(GUIUtil.formattedLabel( new JLabel("Total Profit")));
		else
			pnl.add(GUIUtil.formattedLabel( new JLabel("Total Loss")));
		
		pnl.add(GUIUtil.formattedTextField( new JTextField(Constants.currencyFormat.format(Math.abs(profitLoss)))));
			
			
		
		pnl.add(GUIUtil.formattedLabel( new JLabel("Total Sales Today")));
		pnl.add(GUIUtil.formattedTextField( new JTextField(Constants.currencyFormat.format(DBUtil.totalSalesToday()))));
	
		pnl.add(GUIUtil.formattedLabel( new JLabel("Total Purchases Today")));
		pnl.add(GUIUtil.formattedTextField( new JTextField(Constants.currencyFormat.format(DBUtil.totalPurchaseToday()))));
	
		pnl.add(GUIUtil.formattedLabel( new JLabel("Total Inventory Sales Value")));
		pnl.add(GUIUtil.formattedTextField( new JTextField(Constants.currencyFormat.format(DBUtil.inventoryValue()))));
	
	
		return pnl;
	}
}	
