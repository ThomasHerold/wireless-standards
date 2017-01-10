package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Item;
import model.SimCard;
import util.Constants;
import util.DBUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class NewSimCard extends JDialog {

	private JButton btnAdd, btnClear, btnExit;
	private JTextField txtICCID, txtNetwork, txtPurchasePrice, txtSalesPrice;
	private JComboBox<String> cbSizes;

	private ArrayList<Item> items;
	private AddPurchase owner;
	
	public NewSimCard(AddPurchase owner, ArrayList<Item> items) {
		super(owner, "Add Sim Card to PO");
		this.owner = owner;
		setModalityType(ModalityType.DOCUMENT_MODAL);

		this.items = items;
		
		add(GUIUtil.getTitlePanel("Add Sim Card to Purchase Order"), BorderLayout.NORTH);
		add(pnlInfo(), BorderLayout.CENTER);
		add(pnlButtons(), BorderLayout.SOUTH);

		addActionListeners();

		pack();
		setVisible(true);
	}

	private JPanel pnlInfo() {
		JPanel pnl = new JPanel(new GridLayout(5, 2, 10, 10));
		pnl.setBorder(Constants.EMPTY_BORDER);

		pnl.add(GUIUtil.formattedLabel(new JLabel("ICCID of sim card")));
		txtICCID = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtICCID));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Network Provider")));
		txtNetwork = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtNetwork));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Card Size")));
		cbSizes = new JComboBox<>(new String[] { "Nano", "Regular", "Large" });
		cbSizes.setSelectedIndex(0);
		pnl.add(cbSizes);

		pnl.add(GUIUtil.formattedLabel(new JLabel("Purchase Price")));
		txtPurchasePrice = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtPurchasePrice));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Sale Price")));
		txtSalesPrice = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtSalesPrice));

		return pnl;
	}

	private JPanel pnlButtons() {
		JPanel pnl = new JPanel();

		btnAdd = new JButton("Add Sim Card");
		pnl.add(GUIUtil.formattedButton(btnAdd));

		btnClear = new JButton("Reset");
		pnl.add(GUIUtil.formattedButton(btnClear));

		btnExit = new JButton("Close");
		pnl.add(GUIUtil.formattedButton(btnExit));

		return pnl;
	}

	private void addActionListeners() {

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isEmpty()){
					JOptionPane.showMessageDialog(NewSimCard.this, "Please fill all the fields");
					return;
				}
				
				if(DBUtil.getSimCard(txtICCID.getText()) != null){
					JOptionPane.showMessageDialog(NewSimCard.this, "Please enter a unique serial number");
					return;
				}
				
				
				SimCard simCard = new SimCard(Double.parseDouble(txtPurchasePrice.getText()),
						Double.parseDouble(txtSalesPrice.getText()),false, txtICCID.getText(), txtNetwork.getText(),
						(String) cbSizes.getSelectedItem());
				items.add(simCard);
				owner.displayItems();
				JOptionPane.showMessageDialog(NewSimCard.this, "Sim card added to list");
				clear();
			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void clear() {
		txtICCID.setText("");
		txtNetwork.setText("");
		txtPurchasePrice.setText("");
		txtSalesPrice.setText("");
		cbSizes.setSelectedIndex(0);
	}
	
	public boolean isEmpty() {
		return txtICCID.getText().isEmpty() || txtNetwork.getText().isEmpty()
				|| txtPurchasePrice.getText().isEmpty() || txtSalesPrice.getText().isEmpty() || cbSizes.getSelectedIndex() == -1;
	}
}
