package model;

import util.Util;

public class Cellphone extends Item{

	private String imei;
	private String brand;
	private String model;
	private String color;
	
	public Cellphone(double purchasePrice, double sellingPrice, boolean sold, String imei, String brand, String model, String color) {
		super(purchasePrice, sellingPrice, sold);
		this.imei = imei;
		this.brand = brand;
		this.model = model;
		this.color = color;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
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
	
	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append("Cellphone");
		stb.append("\n  IMEI: "+imei);
		stb.append("\n  Brand: "+brand);
		stb.append("\n  Model: "+model);
		stb.append("\n  Color: "+color);
		stb.append("\n  Purchase Price: "+Util.formatPrice(getPurchasePrice()));
		stb.append("\n  Sale Price: "+Util.formatPrice(getSellingPrice()));
		return stb.toString();
	}
	
	public String toFormatString(){
		return String.format("%-20s %-15s %-15s %-15s %-15.2f %-15.2f", imei,brand,model,color,getPurchasePrice(), getSellingPrice());
	}
	
}
