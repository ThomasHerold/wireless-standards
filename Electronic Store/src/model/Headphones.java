package model;

import util.Util;

public class Headphones extends Item {

	private String serial;
	private String brand;
	private String model;
	private String color;
	private boolean hasMicrophone;

	public Headphones(double purchasePrice, double sellingPrice, boolean sold,String serial, String brand, String model,
			String color, boolean hasMicrophone) {
		super(purchasePrice, sellingPrice,sold);
		this.serial = serial;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.hasMicrophone = hasMicrophone;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isHasMicrophone() {
		return hasMicrophone;
	}

	public void setHasMicrophone(boolean hasMicrophone) {
		this.hasMicrophone = hasMicrophone;
	}

	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append("Headphones");
		stb.append("\n  Serial No.: " + serial);
		stb.append("\n  Brand: " + brand);
		stb.append("\n  Model: " + model);
		stb.append("\n  Color: " + color);
		stb.append("\n  Headphones: " + (hasMicrophone ? "Yes" : "No"));
		stb.append("\n  Purchase Price: " + Util.formatPrice(getPurchasePrice()));
		stb.append("\n  Sale Price: " + Util.formatPrice(getSellingPrice()));
		return stb.toString();
	}

	public String toFormatString(){
		return String.format("%-20s %-15s %-15s %-15s %-15s %-15.2f %-15.2f", serial,brand,model,color,hasMicrophone? "Yes" : "No",getPurchasePrice(), getSellingPrice());
	}
}
