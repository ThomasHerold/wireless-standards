package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Item;
import model.Transaction;
import util.Constants;
import util.DBUtil;
import util.GUIUtil;
import util.Util;

@SuppressWarnings("serial")
public class AddSales extends JDialog {

	private ArrayList<Item> items;

	private JTextArea taOutput;
	private JButton btnAddSim, btnAddCellphone, btnAddHeadphone, btnCreateOrder, btnClear, btnExit;

	private JTextField txtCode, txtName, txtEmail;

	public AddSales(JFrame parent) {
		super(parent, "New Sale");
		setModalityType(ModalityType.DOCUMENT_MODAL);

		items = new ArrayList<>();

		add(GUIUtil.getTitlePanel("New Sale"), BorderLayout.NORTH);

		add(pnlInfo(), BorderLayout.CENTER);
		add(pnlAddItem(), BorderLayout.EAST);

		JPanel pnlSouth = new JPanel(new BorderLayout(10, 10));
		pnlSouth.add(pnlTextArea());
		pnlSouth.add(pnlButtons(), BorderLayout.SOUTH);

		add(pnlSouth, BorderLayout.SOUTH);

		addActionListeners();

		displayItems();

		pack();
		setVisible(true);
	}

	private JPanel pnlInfo() {
		JPanel pnl = new JPanel(new GridLayout(3, 2, 10, 10));
		pnl.setBorder(Constants.EMPTY_BORDER);

		pnl.add(GUIUtil.formattedLabel(new JLabel("Transaction Code")));
		txtCode = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtCode));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Customer Name")));
		txtName = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtName));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Customer Email")));
		txtEmail = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtEmail));

		return pnl;
	}

	private JPanel pnlTextArea() {
		JPanel pnl = new JPanel();

		taOutput = new JTextArea(20, 150);
		taOutput.setEditable(false);
		taOutput.setFont(Constants.TextAREA_FONT);
		JScrollPane sp = new JScrollPane(taOutput);

		pnl.add(sp);

		return pnl;
	}

	private JPanel pnlAddItem() {
		JPanel pnl = new JPanel(new GridLayout(3, 0, 10, 10));
		pnl.setBorder(Constants.EMPTY_BORDER);

		btnAddCellphone = new JButton("Add Cellphone");
		pnl.add(GUIUtil.formattedButton(btnAddCellphone));

		btnAddHeadphone = new JButton("Add Headphones");
		pnl.add(GUIUtil.formattedButton(btnAddHeadphone));

		btnAddSim = new JButton("Add Sim Card");
		pnl.add(GUIUtil.formattedButton(btnAddSim));

		return pnl;
	}

	private JPanel pnlButtons() {
		JPanel pnl = new JPanel();

		btnCreateOrder = new JButton("Create Order");
		pnl.add(GUIUtil.formattedButton(btnCreateOrder));

		btnClear = new JButton("Reset");
		pnl.add(GUIUtil.formattedButton(btnClear));

		btnExit = new JButton("Close");
		pnl.add(GUIUtil.formattedButton(btnExit));

		return pnl;
	}

	private void addActionListeners() {
		btnAddCellphone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(AddSales.this, "Enter the IMEI number of cellphone to be sold: ");
				if (id != null) {
					Item item = DBUtil.getCellphone(id);
					if (item != null) {
						if (item.isSold())
							JOptionPane.showMessageDialog(AddSales.this, "The item has already been sold");
						else {
							items.add(item);
							displayItems();
						}

					} else
						JOptionPane.showMessageDialog(AddSales.this, "The item was not found");

				}

			}
		});

		btnAddHeadphone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(AddSales.this, "Enter the serial number of headphones to be sold: ");
				if (id != null) {
					Item item = DBUtil.getHeadphones(id);
					if (item != null) {
						if (item.isSold())
							JOptionPane.showMessageDialog(AddSales.this, "The item has already been sold");
						else {
							items.add(item);
							displayItems();
						}

					} else
						JOptionPane.showMessageDialog(AddSales.this, "The item was not found");

				}
			}
		});

		btnAddSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog(AddSales.this, "Enter the ICCID of sim card to be sold: ");
				if (id != null) {
					Item item = DBUtil.getSimCard(id);
					if (item != null) {
						if (item.isSold())
							JOptionPane.showMessageDialog(AddSales.this, "The item has already been sold");
						else {
							items.add(item);
							displayItems();
						}

					} else
						JOptionPane.showMessageDialog(AddSales.this, "The item was not found");

				}
			}
		});

		btnCreateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (items.size() == 0) {
					JOptionPane.showMessageDialog(AddSales.this, "The item's list is empty");
					return;
				}

				if (isEmpty()) {
					JOptionPane.showMessageDialog(AddSales.this, "Please fill all the fields");
					return;
				}

				Transaction transaction = new Transaction(txtCode.getText(), txtName.getText(), txtEmail.getText(),
						true);
				for (Item item : items) {
					transaction.addItem(item);
				}

				if (DBUtil.storeSale(transaction)) {
					JOptionPane.showMessageDialog(AddSales.this, "Purchase stored succesfully");
				} else
					JOptionPane.showMessageDialog(AddSales.this, "Error: Could not store the purchase");
			}
		});

		btnClear.addActionListener(new ActionListener() {
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

	public void displayItems() {
		taOutput.setText("");
		taOutput.setText(Util.itemsToString(items));
	}

	public void clear() {
		txtCode.setText("");
		txtName.setText("");
		txtEmail.setText("");
		items.clear();
		displayItems();
	}

	public boolean isEmpty() {
		return txtCode.getText().isEmpty() || txtEmail.getText().isEmpty() || txtName.getText().isEmpty();
	}

}
