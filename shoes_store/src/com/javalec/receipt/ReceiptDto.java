package com.javalec.receipt;

public class ReceiptDto {

	int seqno;
	String brand;
	String name;
	int Price;
	int Size;
	int Quantity;
	String Color;
	
	public ReceiptDto() {
		// TODO Auto-generated constructor stub
	}

	public ReceiptDto(String brand, String name, int Price, int Size, int Quantity, String Color) {
		super();
		this.brand = brand;
		this.name = name;
		this.Price = Price;
		this.Size = Size;
		this.Quantity = Quantity;
		this.Color = Color;
	}
	
	public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public String getbrand() {
		return brand;
	}

	public void setbrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int Price) {
		this.Price = Price;
	}

	public int getSize() {
		return Size;
	}

	public void setSize(int Size) {
		this.Size = Size;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int Quantity) {
		this.Quantity = Quantity;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String Color) {
		this.Color = Color;
	}
}
