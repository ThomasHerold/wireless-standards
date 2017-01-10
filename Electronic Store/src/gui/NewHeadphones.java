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

import model.Headphones;
import model.Item;
import util.Constants;
import util.DBUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class NewHeadphones extends JDialog {

	private JButton btnAdd, btnClear, btnExit;
	private JTextField txtSerial, txtBrand, txtModel, txtColor, txtPurchasePrice, txtSalesPrice;
	private JComboBox<String> cbMicrophone;

	private ArrayList<Item> items;
	private AddPurchase owner;

	public NewHeadphones(AddPurchase owner, ArrayList<Item> items) {
		super(owner, "Add Headphones");
		this.owner = owner;
		setModalityType(ModalityType.DOCUMENT_MODAL);
		this.items = items;

		add(GUIUtil.getTitlePanel("Add Headphones to Purchase Order"), BorderLayout.NORTH);
		add(pnlInfo(), BorderLayout.CENTER);
		add(pnlButtons(), BorderLayout.SOUTH);

		addActionListeners();

		pack();
		setVisible(true);
	}

	private JPanel pnlInfo() {
		JPanel pnl = new JPanel(new GridLayout(7, 2, 10, 10));
		pnl.setBorder(Constants.EMPTY_BORDER);

		// txtSerial, txtBrand, txtModel, txtColor,

		pnl.add(GUIUtil.formattedLabel(new JLabel("Serail No.")));
		txtSerial = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtSerial));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Brand")));
		txtBrand = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtBrand));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Model")));
		txtModel = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtModel));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Color")));
		txtColor = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtColor));

		pnl.add(GUIUtil.formattedLabel(new JLabel("Microphone")));
		cbMicrophone = new JComboBox<>(new String[] { "No", "Yes" });
		cbMicrophone.setSelectedIndex(0);
		pnl.add(cbMicrophone);

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

		btnAdd = new JButton("Add Headphones");
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
					JOptionPane.showMessageDialog(NewHeadphones.this, "Please fill all the fields");
					return;
				}
				
				if(DBUtil.getHeadphones(txtSerial.getText()) != null){
					JOptionPane.showMessageDialog(NewHeadphones.this, "Please enter a unique serial number");
					return;
				}
				
				
				Headphones headphones = new Headphones(Double.parseDouble(txtPurchasePrice.getText()),
						Double.parseDouble(txtSalesPrice.getText()),false,  txtSerial.getText(), txtBrand.getText(),
						txtModel.getText(), txtColor.getText(), cbMicrophone.getSelectedIndex() == 0 ? false: true);
				items.add(headphones);
				owner.displayItems();
				JOptionPane.showMessageDialog(NewHeadphones.this, "Headphones added to transaction items.");
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
		txtSerial.setText("");
		txtBrand.setText("");
		txtColor.setText("");
		txtModel.setText("");
		txtPurchasePrice.setText("");
		txtSalesPrice.setText("");
		cbMicrophone.setSelectedIndex(0);
	}
	
	public boolean isEmpty() {
		return txtSerial.getText().isEmpty() || txtBrand.getText().isEmpty() || txtModel.getText().isEmpty()
				|| txtPurchasePrice.getText().isEmpty() || txtSalesPrice.getText().isEmpty() || cbMicrophone.getSelectedIndex() == -1;
	}

}
