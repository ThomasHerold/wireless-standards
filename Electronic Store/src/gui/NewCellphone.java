package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cellphone;
import model.Item;
import util.Constants;
import util.DBUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class NewCellphone extends JDialog {

	private JButton btnAdd, btnClear, btnExit;
	private JTextField txtIMEI, txtBrand, txtModel, txtColor, txtPurchasePrice, txtSalesPrice;

	private ArrayList<Item> items;
	private AddPurchase owner;

	public NewCellphone(AddPurchase owner, ArrayList<Item> items) {
		super(owner, "Add Cellphone");
		this.owner = owner;
		setModalityType(ModalityType.DOCUMENT_MODAL);
		this.items = items;

		add(GUIUtil.getTitlePanel("Add Cellphone to Purchase Order"), BorderLayout.NORTH);
		add(pnlInfo(), BorderLayout.CENTER);
		add(pnlButtons(), BorderLayout.SOUTH);

		addActionListeners();

		pack();
		setVisible(true);
	}

	private JPanel pnlInfo() {
		JPanel pnl = new JPanel(new GridLayout(6, 2, 10, 10));
		pnl.setBorder(Constants.EMPTY_BORDER);

		// txtSerial, txtBrand, txtModel, txtColor,

		pnl.add(GUIUtil.formattedLabel(new JLabel("IMEI")));
		txtIMEI = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtIMEI));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Brand")));
		txtBrand = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtBrand));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Model")));
		txtModel = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtModel));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Color")));
		txtColor = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtColor));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Purchase Price")));
		txtPurchasePrice = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtPurchasePrice));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Sales Price")));
		txtSalesPrice = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtSalesPrice));

		return pnl;
	}

	private JPanel pnlButtons() {
		JPanel pnl = new JPanel();

		btnAdd = new JButton("Add Cellphone");
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
					JOptionPane.showMessageDialog(NewCellphone.this, "Please fill all the fields");
					return;
				}
				
				if(DBUtil.getCellphone(txtIMEI.getText()) != null){
					JOptionPane.showMessageDialog(NewCellphone.this, "Please enter a unique imei");
					return;
				}
				
				Cellphone cellphone = new Cellphone(Double.parseDouble(txtPurchasePrice.getText()),
						Double.parseDouble(txtSalesPrice.getText()), false, txtIMEI.getText(), txtBrand.getText(),
						txtModel.getText(), txtColor.getText());
				items.add(cellphone);
				owner.displayItems();
				JOptionPane.showMessageDialog(NewCellphone.this, "Cellphone added to transaction items.");
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
		txtIMEI.setText("");
		txtBrand.setText("");
		txtColor.setText("");
		txtModel.setText("");
		txtPurchasePrice.setText("");
		txtSalesPrice.setText("");
	}

	public boolean isEmpty() {
		return txtIMEI.getText().isEmpty() || txtBrand.getText().isEmpty() || txtModel.getText().isEmpty()
				|| txtPurchasePrice.getText().isEmpty() || txtSalesPrice.getText().isEmpty();
	}

}
