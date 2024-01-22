package com.javalec.cartShin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.javalec.product.ProductDTO;
import com.javalec.util.ShareVar;

public class CartDao {

//	Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String ps_mysql = ShareVar.dbPass;
	
	int seqno, size, count, price;
	String brand, name, color;
	int cartNum;
	int cartCount;
	int totalCnt;
	int totalMoney;
	int pcnt;
	String pdate;
	String puserid;
	int pseq;
	
//	constructor
	
	public CartDao() {
		// TODO Auto-generated constructor stub
	}
	
	public CartDao(int seqno) {
		super();
		this.seqno = seqno;
	}
	
	public CartDao(int seqno, int cartCount) {
		super();
		this.seqno = seqno;
		this.cartCount = cartCount;
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


	public CartDao(int size, int price, String brand, String name, String color, int cartNum, int cartCount) {
		super();
		this.size = size;
		this.price = price;
		this.brand = brand;
		this.name = name;
		this.color = color;
		this.cartNum = cartNum;
		this.cartCount = cartCount;
	}

	public CartDao(int cartNum, int cartCount,  String brand, String name,int size, int price, String color) {
		super();
		this.cartNum = cartNum;
		this.cartCount = cartCount;
		this.brand = brand;
		this.name = name;
		this.size = size;
		this.price = price;
		this.color = color;
	}

	//BuyPage에서 장바구니 버튼 눌렀을 때, cart테이블에 정보 insert 처리 
	public boolean insertActionByCartBtnClicked() {
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			
			String query = "insert into cart (cartcount, userid, pseq) values (?,?,?)";
			
			ps = conn.prepareStatement(query);
			
			System.out.println("ShareVar.userId : "+ShareVar.userid);
			
			ps.setInt(1, cartCount);
			ps.setString(2, ShareVar.userid);
			ps.setInt(3, seqno);
			
			
			ps.executeUpdate();
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	//테이블에 정보조회되도록 select
	public ArrayList<CartDto> selecList() {
		ArrayList<CartDto> dtoList = new ArrayList<CartDto>();
		
		//cartnum, obrand, oname, osize, oprice, cnt를 불러와야
		String query = "select c.cartnum, o.obrand, o.oname, o.ocolor ,o.osize, (o.oprice * c.cartcount), c.cartcount from cart c, product p , orderProd o where c.userid='"+ShareVar.userid+"' and c.pseq = p.pseq and p.oseq = o.oseq";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			ResultSet rs = stmt_mysql.executeQuery(query);
			
			while(rs.next()) {
				
				int wkCartNum = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkName = rs.getString(3);
				String wkColor = rs.getString(4);
				int wkSize = rs.getInt(5);
				int wkPrice = rs.getInt(6);
				int wkCartCount = rs.getInt(7);
				
				CartDto dto = new CartDto(wkCartNum, wkBrand, wkName, wkColor, wkSize, wkPrice, wkCartCount);
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
	
	public boolean deleteAction(int cartNum) {
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			
			String query = "delete from cart where cartnum = "+cartNum;
			ps = conn.prepareStatement(query);
			
			ps.executeUpdate();
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		 return true;
	}
	
	
	public CartDto showTotalInfo() {
		CartDto dto = null;
		String query = "select sum(c.cartcount), sum(o.oprice * c.cartcount) from cart c, product p , orderProd o where c.userid='"+ShareVar.userid+"' and c.pseq = p.pseq and p.oseq = o.oseq";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(query);

			if (rs.next()) {
				int wkTotalCnt = rs.getInt(1);
				int wkTotalPrice = rs.getInt(2);

				dto = new CartDto(wkTotalCnt, wkTotalPrice);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}
	
	//Cart정보를 Purchase테이블에 insert 처리 
	public boolean insertCartInfo() {
		PreparedStatement ps = null;
		String query = "insert into purchase (pcnt, pdate, puserid, pseq) select cartcount, sysdate(), userid, pseq from cart where userid = '"+ShareVar.userid+"'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			ps = conn.prepareStatement(query);

			ps.executeUpdate();
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//장바구니에 넣은 수량만큼 구매하기 클릭 시 orderProd에서 수량 빼서 insert 처
//	public boolean updateorderProdInfo(String brand, String name, int cartCnt, int size, String color) {
//		PreparedStatement ps = null;
//		System.out.println("cartDao [size] : "+size);
//		System.out.println("cartDao [brand] : "+brand);
//		System.out.println("cartDao [color] : "+color);
//		System.out.println("cartDao [cartCnt] : "+cartCnt);
//		
//		System.out.println("updateorderProdInfo start");
//		String query1 = "insert into orderProd (obrand, oname, oprice, ocnt, osize, ocolor, odate) values ('"+brand+"', '"+name+"', ";
//		String query2 = " (select oprice from (select oprice from orderProd where obrand ='"+brand+"' and oname = '"+name+"' ";
//		String query3 = " and osize='"+size+"' and ocolor='"+color+"' order by oseq desc limit 1) as f), ";
//		String query4 = " ( select (o.ocnt- '"+cartCnt+"') from orderProd o inner join orderProd j on j.oseq = o.oseq ";
//		String query5 = " where o.obrand = '"+brand+"' and o.oname='"+name+"' and o.osize ='"+size+"' and o.ocolor='"+color+"' ";
//		String query6 = " order by o.oseq desc limit 1), '"+size+"', '"+color+"', sysdate()) ";
////		
////		String query1 = "update orderProd set ocnt = (select ocnt from (select ocnt from orderProd ";
////		String query2 =" where obrand ='"+brand+"' and oname ='"+name+"' and osize ='"+size+"' and ";
////		String query3 =" ocolor = '"+color+"' order by oseq desc limit 1) as f) - '"+cartCount+ "'";
////		String query4 =" where obrand ='"+brand+"' and oname ='"+name+"' and osize ='"+size+"' and ocolor = '"+color+"' ";
//		
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
//			ps = conn.prepareStatement(query1+query2+query3+query4+query5+query6);
//
//			ps.executeUpdate();
//			System.out.println("updateorderProdInfo end");
//			conn.close();
//		} catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
	
	//위에서 insert 된 orderPRod의 
	
	public boolean deleteCartInfo() {
		PreparedStatement ps = null;
		String query = "delete from cart where userid = '"+ShareVar.userid+"'";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url_mysql, id_mysql, ps_mysql);
			ps = conn.prepareStatement(query);
			
			ps.executeUpdate();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
