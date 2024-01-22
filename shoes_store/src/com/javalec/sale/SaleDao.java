package com.javalec.sale;

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

import com.javalec.util.ShareVar;

public class SaleDao {

	private final String url = ShareVar.dbName;
	private final String id = ShareVar.dbUser;
	private final String pw = ShareVar.dbPass;
	
	int pcnt;
	String pdate;
	int oprice;
	int pseq;
	int oseq;
	String brand;
	

	public SaleDao() {
		// TODO Auto-generated constructor stub
	}

	
	public SaleDao(String pdate) {
		super();
		this.pdate = pdate;
	}

	public SaleDao(String pdate, String brand) {
		super();
		this.pdate = pdate;
		this.brand = brand;
	}


	public ArrayList<SaleDto> searchAction() {
		ArrayList<SaleDto> dtoList = new ArrayList<SaleDto>();
		
		String query ="select DATE(pdate) as date, sum(pcnt) ,sum(pcnt * oprice) from orderProd o, purchase pur, product prod where pur.pseq = prod.pseq and prod.oseq = o.oseq group by date order by date desc";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String date = rs.getString(1);
				int wkToalCnt = rs.getInt(2);
				int wkTotalSale = rs.getInt(3);
				
				SaleDto dto = new SaleDto(date, wkTotalSale,  wkToalCnt);
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	

	
	//테이블 선택했을 때 
	public SaleDto cellClicked() {
		SaleDto dto = null; 
		
		String query = "select DATE(pdate) as date, sum(pcnt) ,sum(pcnt * oprice) from orderProd o, purchase pur, product prod where pur.pseq = prod.pseq and prod.oseq = o.oseq and  date(pdate) ='"+pdate+"' group by date";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				String date = rs.getString(1);
				int wkTotalCnt = rs.getInt(2);
				int wkTotalSale = rs.getInt(3);
				
				dto = new SaleDto(date, wkTotalCnt, wkTotalSale);
			}
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//브랜드 값 가져오기 
	public  ArrayList<SaleDto> selectCbValue() {
		ArrayList<SaleDto> dtoList = new ArrayList<SaleDto>();
		
		String query = "select obrand from orderProd group by obrand";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String wkBrand = rs.getString(1);
				
				SaleDto dto = new SaleDto(wkBrand);
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
		
	}
	
	
	//브랜드 별 매출액, 판매수량 
	public ArrayList<SaleDto>  selectSaleByBrand() {
		ArrayList<SaleDto> dtoList = new ArrayList<SaleDto>();
		
		String query = "select sum(pcnt), sum(pcnt*o.oprice) from purchase pur, product prod, orderProd o where o.oseq = prod.oseq and prod.pseq = pur.pseq and o.obrand like '%"+brand+"%' and date(pur.pdate) ='"+pdate+"' group by date(pdate)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				int	wkTotalCnt  = rs.getInt(1);
				int wkTotalSale = rs.getInt(2);
				
				SaleDto dto = new SaleDto(wkTotalCnt, wkTotalSale);
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
	public ArrayList<SaleDto> btnSearchClicked(String date) {
		ArrayList<SaleDto> dtoList = new ArrayList<SaleDto>();
		
		String query = "select DATE(pdate) as date, sum(pcnt) ,sum(pcnt * oprice) from orderProd o, purchase pur, product prod where pur.pseq = prod.pseq and prod.oseq = o.oseq and  date(pdate) ='"+date+"' group by date";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, id, pw);
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String wkDate = rs.getString(1);
				int wkTotalSale = rs.getInt(3);
				int wkToalCnt = rs.getInt(2);
				
				SaleDto dto = new SaleDto(wkDate, wkTotalSale,  wkToalCnt);
				dtoList.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
}
