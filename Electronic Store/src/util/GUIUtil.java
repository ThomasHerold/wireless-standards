package util;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIUtil {

	public static JLabel formattedLabel(JLabel lbl){
		lbl.setFont(Constants.LABEL_FONT);
		lbl.setHorizontalAlignment(SwingConstants.LEADING);
		lbl.setOpaque(false);
		return lbl;
	}
	
	public static JTextField formattedTextField(JTextField txt){
		txt.setFont(Constants.Text_FONT);
		txt.setMaximumSize(txt.getPreferredSize());
		return txt;
	}
	
	public static JButton formattedButton(JButton btn){
		btn.setFont(Constants.BUTTON_FONT);
		btn.setFocusable(false);
		btn.setContentAreaFilled(false);
		btn.setBackground(Color.LIGHT_GRAY);
		btn.setForeground(Color.BLACK);
		btn.setOpaque(true);
		return btn;
	}
	
	public static JPanel getTitlePanel(String title){
		JPanel pnlTitle = new JPanel();
		JLabel lblTitle = new JLabel(title);
		lblTitle.setFont(Constants.TITLE_FONT);
		pnlTitle.add(lblTitle);
		lblTitle.setForeground(Color.BLUE);
		pnlTitle.setBorder(Constants.EMPTY_BORDER);
		lblTitle.setOpaque(false);
		pnlTitle.setOpaque(false);
		return pnlTitle;
	}
	
	
}
