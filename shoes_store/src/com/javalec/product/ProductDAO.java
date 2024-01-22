package com.javalec.product;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.util.ShareVar;

public class ProductDAO {

//	Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String ps_mysql = ShareVar.dbPass;

	int seqno, size, count, price;
	String brand, name, color;
	FileInputStream file;

//	constructor

	public ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDAO(String color) {
		super();
		this.color = color;
	}

	public ProductDAO(String brand, String name, int price) {
		super();
		this.brand = brand;
		this.name = name;

		// TODO Auto-generated constructor stub
	}

	public ProductDAO(String brand, String name, int price, FileInputStream file) {
		super();
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.file = file;
	}

//	Method

//	검색 결과를 Table 로 보내자
	public ArrayList<ProductDTO> selecList() {
		ArrayList<ProductDTO> dtoList = new ArrayList<ProductDTO>();
		String where1 = "select obrand, oname, (select oprice from orderProd group by oprice) ";
		String where2 = "from orderprod group by obrand, oname order by obrand";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(where1 + where2);

			while (rs.next()) {

				String wkBrand = rs.getString(1);
				String wkName = rs.getString(2);
				int wkPrice = rs.getInt(3);

				ProductDTO dto = new ProductDTO(wkBrand, wkName, wkPrice);
				dtoList.add(dto);
			}
			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
//	comboBox에서 선택 후 검색버튼 눌렀을 경우
	public ArrayList<ProductDTO> conditionQueryAction(String conditionQueryName, String tfSelect) {
		ArrayList<ProductDTO> dtoList = new ArrayList<ProductDTO>();
		String select = "select obrand, oname, (select oprice from orderProd group by oprice) ";
		String from = "from orderprod ";
		String where = "where " + conditionQueryName +" like '%" + tfSelect + "%' group by obrand, oname order by obrand";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(select + from + where);
			while(rs.next()) {
				
				String wkBrand = rs.getString(1);
				String wkName = rs.getString(2);
				int wkPrice = rs.getInt(3);

				ProductDTO dto = new ProductDTO(wkBrand, wkName, wkPrice);
				dtoList.add(dto);
				
//				System.out.println(dtoList);
				
			}
			conn_mysql.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
		
	}
	
	
	

//	Table 에서 Row 를 click 했을 경우
	public ProductDTO tableClick() {

		ProductDTO dto = null;

		String select1 = "select obrand, oname, ";
		String select2 = "(select oprice from orderProd group by oprice),  ";
		String select3_1 = "(select pfile from product p inner join purchase pur on p.pseq = pur.pseq inner join orderProd o on o.oseq = p.oseq ";
		String select3_2 = "where o.obrand='" + brand + "' and o.oname = '" + name + "' group by pfile) ";
		String from = "from orderProd oo ";
		String where = "where oo.obrand = '" + brand + "' and oo.oname = '" + name
				+ "' group by obrand, oname order by obrand";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(select1 + select2 + select3_1 + select3_2 + from + where);

			if (rs.next()) {

				String wkBrand = rs.getString(1);
				String wkName = rs.getString(2);
				int wkPrice = rs.getInt(3);

//				file
				ShareVar.filename = ShareVar.filename + 1;
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file);
				InputStream input = rs.getBinaryStream(4);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}

				dto = new ProductDTO(wkBrand, wkName, wkPrice);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

//	선택한 Brand 와 Name 값에 대한 Color 값 보내기
	public ArrayList<ProductDTO> comboColorAction(String cbBrand, String cbName) {

		ProductDTO dto = null;

		ArrayList<ProductDTO> dtoList = new ArrayList<ProductDTO>();
		String Query = "select ocolor from orderprod where obrand = '" + cbBrand + "' and oname = '" + cbName
				+ "' group by ocolor";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(Query);

			while (rs.next()) {

//				System.out.println("1111111");
				String wkColor = rs.getString(1);

//				System.out.println("wkColor ; " + wkColor);
				dto = new ProductDTO(wkColor);
				dtoList.add(dto);
//				System.out.println("22222");

			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;

	}
	
//	선택한 Brand 와 Name 값에 대한 Size 값 보내기
	public ArrayList<ProductDTO> comboSizeAction(String cbBrand, String cbName) {

		ProductDTO dto = null;

		ArrayList<ProductDTO> dtoList = new ArrayList<ProductDTO>();
		String Query = "select osize from orderprod where obrand = '" + cbBrand + "' and oname = '" + cbName
				+ "' group by osize";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(Query);

			while (rs.next()) {

//				System.out.println("1111111");
				int wkSize = rs.getInt(1);

//				System.out.println("wkColor ; " + wkSize);
				dto = new ProductDTO(wkSize);
				dtoList.add(dto);
//				System.out.println("22222");

			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;

	}


}
