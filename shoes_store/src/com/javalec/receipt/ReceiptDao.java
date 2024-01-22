package com.javalec.receipt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.receipt.*;
import com.javalec.util.ShareVar;

public class ReceiptDao {
//	Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String ps_mysql = ShareVar.dbPass;
	
	int seqno;
	String brand;
	String name;
	int Price;
	int Size;
	int Quantity;
	String Color;
	
	
//	constructor
	
	public ReceiptDao() {
		// TODO Auto-generated constructor stub
	}
	public ReceiptDao(int seqno) {
		super();
		this.seqno = seqno;
	}

	public ReceiptDao(int seqno, String brand, String name, int Price, int Size, int Quantity, String Color) {
	super();
	this.seqno = seqno;
	this.brand = brand;
	this.name = name;
	this.Price = Price;
	this.Size = Size;
	this.Quantity = Quantity;
	this.Color = Color;
	}
	
	public ArrayList<ReceiptDto> selectList(){
		ArrayList<ReceiptDto> dtoList = new ArrayList<ReceiptDto>();
		String where1 = "select obrand, oname, oprice, osize ,pcnt, ocolor ";
		String where2 = "from orderprod a, purchase c, product b ";
		String where3 = "where a.oseq = b.oseq and b.pseq = c.pseq";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql  = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(where1 + where2 + where3);
			
			while(rs.next()) {
				
				String wkBrand = rs.getString(1);
				String wkName = rs.getString(2);
				int wkPrice = rs.getInt(3);
				int wkSize = rs.getInt(4);
				int wkQuantity = rs.getInt(5);
				String wkColor = rs.getString(6);
				
				ReceiptDto dto = new ReceiptDto(wkBrand, wkName, wkPrice, wkSize, wkQuantity, wkColor);
				dtoList.add(dto);

				
			}
			conn_mysql.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	
}
