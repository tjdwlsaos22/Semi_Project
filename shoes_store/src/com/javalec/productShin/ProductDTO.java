package com.javalec.productShin;

import java.io.InputStream;

public class ProductDTO {

//	Field
	int seqno, size, count, price, filePath;
	String brand, name, color;
	InputStream pfile;

//	constructor

	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(int size) {
		super();
		this.size = size;
	}
	

	public ProductDTO(String brand, String name, int price) {
		super();
		this.brand = brand;
		this.name = name;
		this.price = price;

	}

	public ProductDTO(String brand, String name, int price, InputStream pfile, int filePath) {
		super();
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.pfile = pfile;
		this.filePath = filePath;

	}

	public ProductDTO(int seqno, int count, int price, String brand, String name, String color) {
		super();
		this.seqno = seqno;
		// this.size = size;
		this.count = count;
		this.price = price;
		this.brand = brand;
		this.name = name;
		this.color = color;
	}

	public ProductDTO(String color) {
		super();
		this.color = color;
	}
	

	public ProductDTO(int seqno, String brand, String name, int price, int count, int size, String color) {
		super();
		this.seqno = seqno;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.count = count;
		this.size = size;
		this.color = color;
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

	public void setBrand(String kind) {
		this.brand = kind;
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

	public InputStream getPfile() {
		return pfile;
	}

	public void setPfile(InputStream pfile) {
		this.pfile = pfile;
	}

	public int getFilePath() {
		return filePath;
	}

	public void setFilePath(int filePath) {
		this.filePath = filePath;
	}

}
