package util;

import java.util.ArrayList;
import java.util.Date;

import model.Cellphone;
import model.Headphones;
import model.Item;
import model.SimCard;

public class Util {

	
	
	public static String formatPrice(double price){
		return Constants.currencyFormat.format(price);
	}
	
	public static String formatDate(Date date){
		return Constants.dateFormat.format(date);
	}
	
	public static String itemsToString(ArrayList<Item> items){
		StringBuilder stb = new StringBuilder();
		
		stb.append("Cellphones\n");
		stb.append(String.format("%-20s %-15s %-15s %-15s %-15s %-15s\n", "IMEI", "Brand", "Model", "Color",
				"Purchase", "Sales"));
		for (Item item : items) {
			if (item instanceof Cellphone)
				stb.append(item.toFormatString() + "\n");
		}

		stb.append("\n\nHeadphones\n");
		stb.append(String.format("%-20s %-15s %-15s %-15s %-15s %-15s %-15s\n", "Serial No.", "Brand", "Model",
				"Color", "Microphone", "Purchase", "Sales"));
		for (Item item : items) {
			if (item instanceof Headphones)
				stb.append(item.toFormatString() + "\n");
		}

		stb.append("\n\nSim Cards\n");
		stb.append(
				String.format("%-20s %-15s %-15s %-15s %-15s\n", "ICCID", "Network", "Size", "Purchase", "Sales"));
		for (Item item : items) {
			if (item instanceof SimCard)
				stb.append(item.toFormatString() + "\n");
		}
		
		return stb.toString();
	}
}
