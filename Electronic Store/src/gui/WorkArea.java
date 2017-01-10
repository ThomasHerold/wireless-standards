package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import util.Constants;
import util.GUIUtil;

@SuppressWarnings("serial")
public class WorkArea extends JFrame {

	private JButton btnPurchase, btnSales, btnInventory, btnExit, btnSearchTransaction, btnShowReport;

	public WorkArea() {

		setTitle("Work Area");

		add(GUIUtil.getTitlePanel(Constants.STORE_NAME), BorderLayout.NORTH);
		add(createButtonsPanel());

		addActionListeners();
	}

	public JPanel createButtonsPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

		btnPurchase = new JButton("Add Purchase");
		GUIUtil.formattedButton(btnPurchase);
		panel.add(btnPurchase);

		btnSales = new JButton("Add Sales");
		GUIUtil.formattedButton(btnSales);
		panel.add(btnSales);

		btnInventory = new JButton("View Inventory");
		GUIUtil.formattedButton(btnInventory);
		panel.add(btnInventory);

		btnSearchTransaction = new JButton("Search Transaction");
		GUIUtil.formattedButton(btnSearchTransaction);
		panel.add(btnSearchTransaction);
		
		btnShowReport = new JButton("Show Report");
		GUIUtil.formattedButton(btnShowReport);
		panel.add(btnShowReport);

		btnExit = new JButton("Exit");
		GUIUtil.formattedButton(btnExit);
		panel.add(btnExit);

		panel.setBorder(Constants.EMPTY_BORDER);
		panel.setOpaque(false);

		return panel;

	}

	public void addActionListeners() {
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddPurchase(WorkArea.this);
			}
		});

		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddSales(WorkArea.this);
			}
		});

		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewInventory(WorkArea.this);
			}
		});
		
		btnSearchTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SearchTransaction(WorkArea.this);
			}
		});
		
		btnShowReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowReport(WorkArea.this);
			}
		});

		btnExit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
