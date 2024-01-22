package com.javalec.purchaseShin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.adminOrder.AdminOrderDto;
import com.javalec.util.ShareVar;

public class OrderDao {

	private final String url = ShareVar.dbName;
	private final String id = ShareVar.dbUser;
	private final String pw = ShareVar.dbPass;

	int prodnum;
	String obrand;
	String oname;
	int pcnt;
	int oprice;
	int pseq;
	int osize;
	String pdate;
	String pfile;
	String ocolor;

	public OrderDao() {
		// TODO Auto-generated constructor stub
	}

	public OrderDao(String obrand, String oname, int pcnt, int oprice, String tkDate) {
		super();
		this.obrand = obrand;
		this.oname = oname;
		this.pcnt = pcnt;
		this.oprice = oprice;
		this.pdate = tkDate;
	}

	public OrderDao(int prodnum) {
		super();
		this.prodnum = prodnum;
	}

	public ArrayList<OrderDto> searchAction() {
		ArrayList<OrderDto> dtoList = new ArrayList<OrderDto>();
		String query = "select prodnum, obrand, oname, (pcnt), osize, ocolor, (pcnt*oprice), pdate from product p, purchase pur , orderProd o where pur.pseq = p.pseq and p.oseq = o.oseq and  puserid = '"
				+ ShareVar.userid + "' order by pdate desc";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int wkProdnum = rs.getInt(1);
				String wkObrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkCnt = rs.getInt(4);
				int wkSize = rs.getInt(5);
				String wkColor = rs.getString(6);
				int wkPrice = rs.getInt(7);
				String wkDate = rs.getString(8);

				OrderDto dto = new OrderDto(wkProdnum, wkObrand, wkName, wkCnt, wkSize, wkColor, wkPrice, wkDate);
				dtoList.add(dto);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}

	public ArrayList<OrderDto> selectProductName(String oname) {
		ArrayList<OrderDto> dtoList = new ArrayList<OrderDto>();
		String query = "select  prodnum, obrand, oname, (pcnt), osize, ocolor ,(pcnt*oprice), pdate from product p, purchase pur , orderProd o where pur.pseq = p.pseq and p.oseq = o.oseq and  puserid = '"
				+ ShareVar.userid + "' and oname like '%" + oname + "%' order by pdate desc";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int wkProdnum = rs.getInt(1);
				String wkObrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkCnt = rs.getInt(4);
				int wkSize = rs.getInt(5);
				String wkColor = rs.getString(6);
				int wkPrice = rs.getInt(7);
				String wkDate = rs.getString(8);

				OrderDto dto = new OrderDto(wkProdnum, wkObrand, wkName, wkCnt, wkSize, wkColor, wkPrice, wkDate);
				dtoList.add(dto);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	public ArrayList<OrderDto> selectPdate(String pdate) {
		ArrayList<OrderDto> dtoList = new ArrayList<OrderDto>();
		String query = "select  prodnum, obrand, oname, (pcnt), osize, ocolor, (pcnt*oprice), pdate from product p, purchase pur , orderProd o where pur.pseq = p.pseq and p.oseq = o.oseq and  puserid = '"
				+ ShareVar.userid + "' and pdate like '%" + pdate + "%' order by pdate desc";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int wkProdnum = rs.getInt(1);
				String wkObrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkCnt = rs.getInt(4);
				int wkSize = rs.getInt(5);
				String wkColor = rs.getString(6);
				int wkPrice = rs.getInt(7);
				String wkDate = rs.getString(8);

				OrderDto dto = new OrderDto(wkProdnum, wkObrand, wkName, wkCnt, wkSize, wkColor, wkPrice, wkDate);
				dtoList.add(dto);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	public ArrayList<OrderDto> selectBrand(String obrand) {
		ArrayList<OrderDto> dtoList = new ArrayList<OrderDto>();
		String query = "select  prodnum, obrand, oname, (pcnt), osize, ocolor ,(pcnt*oprice), pdate from product p, purchase pur , orderProd o where pur.pseq = p.pseq and p.oseq = o.oseq and  puserid = '"
				+ ShareVar.userid + "' and obrand like '%" + obrand + "%' order by pdate desc";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				int wkProdnum = rs.getInt(1);
				String wkObrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkCnt = rs.getInt(4);
				int wkSize = rs.getInt(5);
				String wkColor = rs.getString(6);
				int wkPrice = rs.getInt(7);
				String wkDate = rs.getString(8);

				OrderDto dto = new OrderDto(wkProdnum, wkObrand, wkName, wkCnt, wkSize, wkColor, wkPrice, wkDate);
				dtoList.add(dto);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
//	//seqnum 가져오기 
//	public int getProdNum() {
//		int num = 0;
//		
//		String query = "select prodnum from product p, purchase pur , orderProd o where pur.pseq = p.pseq and p.oseq = o.oseq and ";  
//		String query1 = "puserid = '"+ShareVar.userid+"' and  p.pseq = (select pseq from product pp, orderProd oo where oo.oseq = pp.oseq ";
//		String query2 = "and obrand ='"+obrand+"' and oname='"+oname+"' and osize = '"+osize+"') and pdate ='"+pdate+"'"; 
//		
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(url, id, pw);
//			Statement stmt = conn.createStatement();
//			
//			ResultSet rs = stmt.executeQuery(query+query1+query2);
//			
//			if(rs.next()) {
//				num = rs.getInt(1);
//			}
//			conn.close();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return num;
//	}
	
	//주문취소하기
	public boolean deleteAction() {
		PreparedStatement ps = null;
		String query = "delete from purchase where prodnum = "+prodnum;
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			ps = conn.prepareStatement(query);
			
			ps.executeUpdate();
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//주문 취소 시, orderProd에 제품 수량 증가시키기 
	public boolean insertToOrderProduct(String brand, String name, int cnt, int size, String color) {
		PreparedStatement ps = null;
		
		System.out.println("orderDao [brand] : "+brand);
		System.out.println("orderDao [name] : "+name);
		System.out.println("orderDao [size] : "+size);
		System.out.println("orderDao [color] : "+color);
		System.out.println("orderdao() : cnt : "+cnt);
		
		String query1 = "insert into orderProd (obrand, oname, oprice, ocnt, osize, ocolor, odate) ";
		String query2 = " values ('"+brand+"', '"+name+"', ";
		String query3 = " (select oprice from (select oprice from orderProd where obrand ='"+brand+"' and oname = '"+name+"' and osize='"+size+"' and ocolor='"+color+"' order by oseq desc limit 1) as f), ";
		String query4 = " ( select (o.ocnt+ '"+cnt+"') from orderProd o inner join orderProd j on j.oseq = o.oseq ";
		String query5 = " where o.obrand = '"+brand+"' and o.oname='"+name+"' and o.osize ='"+size+"' and o.ocolor='"+color+"' ";
		String query6 = "  order by o.oseq desc limit 1), '"+size+"', '"+color+"', sysdate()) ";
		
		//String query1 = "update orderProd set ocnt = (select ocnt from (select ocnt from orderProd where obrand ='"+brand+"' and oname ='"+name+"' and osize ='"+size+"' and ocolor = '"+color+"' order by oseq desc limit 1) as f)+"+cnt+" where obrand ='"+brand+"' and oname ='"+name+"' and osize ='"+size+"' and ocolor = '"+color+"' ";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			//ps = conn.prepareStatement(query1);
			ps = conn.prepareStatement(query1 + query2 + query3 + query4 + query5 + query6);
			
			ps.executeUpdate();
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	//orderProd에 insert된 oseq 조회
	public int getInsertedOseq(String brand, String name, int cnt, int size, String color) {
		int oseqNum = 0;
		String query = "select oseq from orderProd where obrand='"+brand+"' and oname='"+name+"' and osize='"+size+"' and ocolor='"+color+"' order by oseq desc limit 1";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {

				oseqNum = rs.getInt(1);
				OrderDto dto = new OrderDto(oseqNum);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oseqNum;
	}

	//위에서 조회한 oseq로 product에 제품등록
	public boolean insertProductQuery(int oseq, String brand, String name, int size) {
	PreparedStatement ps = null;
		
		System.out.println("ORDERDAO [insertProductQuery(]) start ");
		
		String query = "insert into product ( pfile, oseq) values((select pfile from product p, orderProd o where o.obrand = '"+brand+"' and o.oname='"+name+"' and o.osize='"+size+"' and o.oseq = p.oseq group by pfile), "+oseq+")";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			//ps = conn.prepareStatement(query1);
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
