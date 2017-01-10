package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Transaction;
import util.Constants;
import util.DBUtil;
import util.GUIUtil;

@SuppressWarnings("serial")
public class SearchTransaction extends JDialog{

	
	private JTextField txtSearch;
	private JTextArea taOutput;
	private JButton btnSearch, btnExit;
	
	
	public SearchTransaction(JFrame parent) {
		super(parent, "Search Transaction");
		setModalityType(ModalityType.DOCUMENT_MODAL);

		add(GUIUtil.getTitlePanel("Search Transaction"), BorderLayout.NORTH);
		add(pnlSearch(), BorderLayout.CENTER);
		add(pnlTextArea(), BorderLayout.SOUTH);
		addActionListeners();
		pack();
		setVisible(true);
		
	}
	
	private JPanel pnlSearch(){
		JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10 ));
		pnl.setBorder(Constants.EMPTY_BORDER);
		
		JLabel lblSearch = new JLabel("Enetr transaction code to search");
		GUIUtil.formattedLabel(lblSearch);
		pnl.add(lblSearch);
		
		txtSearch = new JTextField(20);
		pnl.add(GUIUtil.formattedTextField(txtSearch));
		
		btnSearch = new JButton("Search");
		GUIUtil.formattedButton(btnSearch);
		pnl.add(btnSearch);
		
		btnExit = new JButton("Close");
		pnl.add(GUIUtil.formattedButton(btnExit));
		
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
	
	private void addActionListeners(){
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transaction transaction = DBUtil.getTransaction(txtSearch.getText());
				if(transaction == null)
					taOutput.setText("No transaction was found with this code");
				else
					taOutput.setText(transaction.toString());
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
}
