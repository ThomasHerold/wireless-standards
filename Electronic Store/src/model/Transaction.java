package model;
import java.util.ArrayList;
import java.util.Date;

import util.Util;

public class Transaction {

	private String transactionCode;
	private String name;
	private String email;
	private Date time;
	private boolean isPurchase;
	private ArrayList<Item> items;

	public Transaction(String transactionCode, String name, String email, boolean purchase) {
		this.transactionCode = transactionCode;
		this.name = name;
		this.email = email;
		time = new Date();
		this.isPurchase = purchase;
		items = new ArrayList<>();
	}

	public boolean isPurchase() {
		return isPurchase;
	}

	public void setPurchase(boolean isPurchase) {
		this.isPurchase = isPurchase;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append("Transaction Code: "+transactionCode);
		stb.append("\nName: "+name);
		stb.append("\nEmail: "+email);
		stb.append("\nTime: "+Util.formatDate(time));
		stb.append("\n\n");
		stb.append(Util.itemsToString(items));
		return stb.toString();
		
	}

	
}
