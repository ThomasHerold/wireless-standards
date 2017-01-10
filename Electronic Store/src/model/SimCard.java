package model;

import util.Util;

public class SimCard extends Item{

	private String iccid;
	private String network;
	private String size;
	
	public SimCard(double purchasePrice, double sellingPrice, boolean sold,String iccid, String network, String size) {
		super(purchasePrice, sellingPrice,sold);
		this.iccid = iccid;
		this.network = network;
		this.size = size;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append("Sim Card");
		stb.append("\n  ICCID: "+iccid);
		stb.append("\n  Network: "+network);
		stb.append("\n  Size: "+size);
		stb.append("\n  Purchase Price: "+Util.formatPrice(getPurchasePrice()));
		stb.append("\n  Sale Price: "+Util.formatPrice(getSellingPrice()));
		return stb.toString();
	}
	
	public String toFormatString(){
		return String.format("%-20s %-15s %-15s %-15.2f %-15.2f",iccid,network,size,getPurchasePrice(), getSellingPrice());
	}
}
