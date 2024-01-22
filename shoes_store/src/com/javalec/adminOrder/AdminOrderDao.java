package com.javalec.adminOrder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.adminStock.AdminStockDto;
import com.javalec.util.ShareVar;

public class AdminOrderDao {

	private final String url = ShareVar.dbName;
	private final String id = ShareVar.dbUser;
	private final String pw = ShareVar.dbPass;
	
	int oseq;
	String obrand;
	String oname;
	int oprice;
	int ocnt;
	int osize;
	String ocolor;
	String odate;
	
	
	public AdminOrderDao() {
		// TODO Auto-generated constructor stub
	}
	
	
	public AdminOrderDao(int oseq) {
		super();
		this.oseq = oseq;
	}
	

	public AdminOrderDao(String obrand, String oname, int oprice, int ocnt, int osize, String ocolor, String odate) {
		super();
		this.obrand = obrand;
		this.oname = oname;
		this.oprice = oprice;
		this.ocnt = ocnt;
		this.osize = osize;
		this.ocolor = ocolor;
		this.odate = odate;
	}


	public AdminOrderDao(int oseq, String obrand, String oname, int oprice, int osize, int ocnt, String ocolor) {
		super();
		this.oseq = oseq;
		this.obrand = obrand;
		this.oname = oname;
		this.oprice = oprice;
		this.osize = osize;
		this.ocnt = ocnt;
		this.ocolor = ocolor;
	}


	public ArrayList<AdminOrderDto> searchAction() {
		ArrayList<AdminOrderDto> dtoList = new ArrayList<AdminOrderDto>();
		
		String query1="select f.oseq, f.obrand, f.oname, f.oprice, f.ocnt, f.osize, f.ocolor from (select obrand, oname, min(oseq) as minoseq, osize, oprice, ocolor ";
		String query2="	from orderProd group by obrand, oname, oprice, osize, ocolor) as x inner join orderProd f on f.obrand = x.obrand and f.oname = x.oname ";
		String query3="	and f.osize = x.osize and f.ocolor = x.ocolor and f.oseq = x.minoseq where f.oprice != ''"+" and f.osize != '' order by f.obrand, f.oname";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query1 + query2 + query3);
			
			while(rs.next()) {
				int wkSeq = rs.getInt(1);
				String weBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = rs.getInt(4);
				int wkCnt = rs.getInt(5);
				int wkSize = rs.getInt(6);
				String wkColor = rs.getString(7);
				
				AdminOrderDto dto = new AdminOrderDto(wkSeq, weBrand, wkName, wkPrice, wkCnt, wkSize, wkColor);
				dtoList.add(dto);
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	public AdminOrderDto cellClicked() {
		AdminOrderDto dto = null;
		String query = "select oseq, obrand, oname, oprice, ocnt, osize, ocolor from orderProd where oseq ="+oseq;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int wkSeq = rs.getInt(1);
				String weBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = rs.getInt(4);
				int wkCnt = rs.getInt(5);
				int wkSize = rs.getInt(6);
				String wkColor = rs.getString(7);
				
				 dto = new AdminOrderDto(wkSeq, weBrand, wkName, wkPrice, wkCnt, wkSize, wkColor);
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	public boolean insertAction() {
		PreparedStatement ps = null;
		String query = "insert into orderProd( obrand, oname, osize, ocnt, ocolor, oprice , odate) ";
		String query1 = " values(?,?,?,?,?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			ps = conn.prepareStatement(query + query1);
			
			ps.setString(1, obrand);
			ps.setString(2, oname);
			ps.setInt(3, osize);
			ps.setInt(4, ocnt);
			ps.setString(5, ocolor);
			ps.setInt(6, oprice);
			ps.setString(7, odate);
			
			ps.executeUpdate();
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateAction() {
		PreparedStatement ps = null;
		String query = "update orderProd set obrand=?, oname=?, osize=?, ocnt=?, ocolor=?, oprice=? where oseq = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			ps = conn.prepareStatement(query);
			
			ps.setString(1, obrand);
			ps.setString(2, oname);
			ps.setInt(3, osize);
			ps.setInt(4, ocnt);
			ps.setString(5, ocolor);
			ps.setInt(6, oprice);
			ps.setInt(7, oseq);
			
			ps.executeUpdate();
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteAction() {
		PreparedStatement ps = null;
		String query = "delete from orderProd where oseq = "+oseq;
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
	
	public boolean deleteActionSec() {
		PreparedStatement ps = null;
		String query = "delete from product where oseq = "+oseq;
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
	
	
	//브랜드로 검색
	public ArrayList<AdminOrderDto> searchConditionToBrand(String str) {
		ArrayList<AdminOrderDto> list = new ArrayList<AdminOrderDto>();
		
		String query1="select f.oseq, f.obrand, f.oname, f.oprice, f.ocnt, f.osize, f.ocolor from (select obrand, oname, min(oseq) as minoseq, osize, oprice, ocolor ";
		String query2="	from orderProd group by obrand, oname, oprice, osize, ocolor) as x inner join orderProd f on f.obrand = x.obrand and f.oname = x.oname ";
		String query3="	and f.osize = x.osize and f.ocolor = x.ocolor and f.oseq = x.minoseq where f.oprice != ''"+" and f.osize != '' and f.obrand like '%"+str+"%' order by f.obrand, f.oname, f.osize";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query1 + query2 + query3);

			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				String weBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = rs.getInt(4);
				int wkCnt = rs.getInt(5);
				int wkSize = rs.getInt(6);
				String wkColor = rs.getString(7);

				AdminOrderDto dto = new AdminOrderDto(wkSeq, weBrand, wkName, wkPrice, wkCnt, wkSize, wkColor);
				list.add(dto);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	//제품명으로 검색
	public ArrayList<AdminOrderDto> searchConditionToName(String str) {
		ArrayList<AdminOrderDto> list = new ArrayList<AdminOrderDto>();
		
		System.out.println("dfdf");
		String query1="select f.oseq, f.obrand, f.oname, f.oprice, f.ocnt, f.osize, f.ocolor from (select obrand, oname, min(oseq) as minoseq, osize, oprice, ocolor ";
		String query2="	from orderProd group by obrand, oname, oprice, osize, ocolor) as x inner join orderProd f on f.obrand = x.obrand and f.oname = x.oname ";
		String query3="	and f.osize = x.osize and f.ocolor = x.ocolor and f.oseq = x.minoseq where f.oprice != ''"+" and f.osize != '' and f.oname like '%"+str+"%' order by f.obrand, f.oname, f.osize";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query1 + query2 + query3);

			System.out.println("dfdf2");
			while (rs.next()) {
				int wkSeq = rs.getInt(1);
				String weBrand = rs.getString(2);
				String wkName = rs.getString(3);
				int wkPrice = rs.getInt(4);
				int wkCnt = rs.getInt(5);
				int wkSize = rs.getInt(6);
				String wkColor = rs.getString(7);

				AdminOrderDto dto = new AdminOrderDto(wkSeq, weBrand, wkName, wkPrice, wkCnt, wkSize, wkColor);
				list.add(dto);
			}
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//색상으로 검색
		public ArrayList<AdminOrderDto> searchConditionToColor(String str) {
			ArrayList<AdminOrderDto> list = new ArrayList<AdminOrderDto>();
			
			String query1="select f.oseq, f.obrand, f.oname, f.oprice, f.ocnt, f.osize, f.ocolor from (select obrand, oname, min(oseq) as minoseq, osize, oprice, ocolor ";
			String query2="	from orderProd group by obrand, oname, oprice, osize, ocolor) as x inner join orderProd f on f.obrand = x.obrand and f.oname = x.oname ";
			String query3="	and f.osize = x.osize and f.ocolor = x.ocolor and f.oseq = x.minoseq where f.oprice != ''"+" and f.osize != '' and f.ocolor like'%"+str+"%' order by f.obrand, f.oname, f.osize";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, id, pw);
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(query1 + query2 + query3);

				while (rs.next()) {
					int wkSeq = rs.getInt(1);
					String weBrand = rs.getString(2);
					String wkName = rs.getString(3);
					int wkPrice = rs.getInt(4);
					int wkCnt = rs.getInt(5);
					int wkSize = rs.getInt(6);
					String wkColor = rs.getString(7);

					AdminOrderDto dto = new AdminOrderDto(wkSeq, weBrand, wkName, wkPrice, wkCnt, wkSize, wkColor);
					list.add(dto);
				}
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
}
