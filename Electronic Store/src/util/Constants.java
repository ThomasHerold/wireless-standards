package util;

import java.awt.Font;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public interface Constants {

	String USER_PASSWORD = "";
	
	String USERNAME = "root";
	String PASSWORD = "password";
	String DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "jdbc:mysql://localhost:3306/store";
	
	String STORE_NAME = "BEST ELECTRONICS";
	
	Font BUTTON_FONT = new Font(Font.SERIF, Font.BOLD, 15);
	Font TITLE_FONT = new Font(Font.SANS_SERIF, Font.ITALIC, 35);
	Font LABEL_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 15);
	Font Text_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 15);
	Border EMPTY_BORDER = new EmptyBorder(20, 20, 20, 20);
	Font TextAREA_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 13);
	
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
	SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
	
}
