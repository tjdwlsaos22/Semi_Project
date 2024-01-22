package com.javalec.cartShin;

public class CartDto {

	
	//	Field
	
	int seqno, size, count, price;
	String brand, name, color;
	int cartNum;
	int cartCount;
	int totalCnt;
	int totalMoney;
    
	
	//	constructor
	
	public CartDto() {
		
	}

	public CartDto(int cartNum, String brand, String name, String color,  int size, int price, int cartCount) {
		super();
		this.cartNum = cartNum;
		this.brand = brand;
		this.name = name;
		this.color = color;
		this.size = size;
		this.price = price;
		this.cartCount = cartCount;
	}

	public CartDto(int seqno, String name, String brand, int size,  int price,  int count) {
		super();
		this.seqno = seqno;
		this.name = name;
		this.brand = brand;
		this.size = size;
		this.price = price;
		this.count = count;
	}
	
	public CartDto(int seqno, int size, int count, int price, String brand, String name, String color) {
		super();
		this.seqno = seqno;
		this.size = size;
		this.count = count;
		this.price = price;
		this.brand = brand;
		this.name = name;
		this.color = color;
	}
	
	public CartDto(int cartNum, int cartCount, String brand, String name, int size, int price, String color) {
		super();
		this.cartNum = cartNum;
		this.cartCount = cartCount;
		this.brand = brand;
		this.name = name;
		this.size = size;
		this.price = price;
		this.color = color;
	}
	
	
    public CartDto(int totalCnt, int totalMoney) {
		super();
		this.totalCnt = totalCnt;
		this.totalMoney = totalMoney;
	}

	//	Method
	public int getSeqno() {
		return seqno;
	}

	public void setSeqno(int seqno) {
		this.seqno = seqno;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCartNum() {
		return cartNum;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public int getCartCount() {
		return cartCount;
	}

	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}
	
}
