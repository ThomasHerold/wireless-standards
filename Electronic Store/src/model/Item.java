package model;

public abstract class Item {

	private double purchasePrice;
	private double sellingPrice;
	private boolean sold;

	public Item(double purchasePrice, double sellingPrice, boolean sold) {
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.sold = sold;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public abstract String toFormatString();

}
