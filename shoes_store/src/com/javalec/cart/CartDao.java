package com.javalec.cart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.product.ProductDTO;
import com.javalec.util.ShareVar;

public class CartDao {

//	Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String ps_mysql = ShareVar.dbPass;
	
	int seqno, size, count, price;
	String brand, name, color;
	FileInputStream file;
	
	
//	constructor
	
	public CartDao() {
		// TODO Auto-generated constructor stub
	}

	public CartDao(int seqno) {
		super();
		this.seqno = seqno;
	}

	public CartDao(int seqno, String name, String brand, int size, int price, int count) {
		super();
		this.seqno = seqno;
		this.name = name;
		this.brand = brand;
		this.size = size;
		this.price = price;
		this.count = count;
	}
	
	
//	Method
	
//	검색 결과를 Table 로 보내자
	
	
	public ArrayList<CartDto> selecList() {
		ArrayList<CartDto> dtoList = new ArrayList<CartDto>();
		String whereDefault = "select pseq, pname, pbrand, psize, price, pcount from product";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(whereDefault);
			
			while(rs.next()) {
				
				int wkSeq = rs.getInt(1);
				String wkName = rs.getString(2);
				String wkBrand = rs.getString(3);
				int wkSize = rs.getInt(4);
				int wkPrice = rs.getInt(5);
				int wkCount = rs.getInt(6);
				
				CartDto dto = new CartDto(wkSeq, wkName, wkBrand, wkSize, wkPrice, wkCount);
				dtoList.add(dto);
			}
			conn_mysql.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	//	Table 에서 Row 를 click 했을 경우
	public CartDto tableclick() {
		
		CartDto dto = null;
		
		String where1 = "select seq, name, brand, size, price, count, pfile " ;
		String where2 = "from product " ;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(where1 + where2);
			
			if(rs.next()) {
				int wkSeqno = rs.getInt(1);
				String wkName = rs.getString(2);
				String wkBrand = rs.getString(3);
				int wkSize = rs.getInt(4);
				int wkPrice = rs.getInt(5);
				int wkCount = rs.getInt(6);
				
//				file
				ShareVar.filename = ShareVar.filename + 1;
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(8);
				byte[] buffer = new byte[1024];
				
				while(input.read(buffer)>0) {
					output.write(buffer);
				}
				
				dto = new CartDto(wkSeqno, wkName, wkBrand, wkSize, wkPrice, wkCount);
			}
			
			conn_mysql.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
		
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
