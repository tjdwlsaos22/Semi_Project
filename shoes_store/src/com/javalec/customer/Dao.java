package com.javalec.customer;

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
import java.util.HashMap;

import javax.print.DocFlavor.INPUT_STREAM;

import com.javalec.util.ShareVar;
import com.mysql.cj.jdbc.Blob;

public class Dao {

	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql=ShareVar.dbUser;
	private final String pw_mysql=ShareVar.dbPass;
	
	String userid;
	String userpw;
	String username;
	String userphone;
	FileInputStream file;
	String filepath;
	
	public Dao() {
		// TODO Auto-generated constructor stub
	}

	public Dao(String userid, String userpw, String username, String userphone, FileInputStream file) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.userphone = userphone;
		this.file=file;
	}
	
	
	
		public Dao(String userid, String userpw, String username, String userphone, FileInputStream file, String filepath) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.userphone = userphone;
		this.file = file;
		this.filepath = filepath;
	}

		public Dao(String userid) {
		super();
		this.userid = userid;
	}
		
		

		public Dao(String userid, String userpw) {
			super();
			this.userid = userid;
			this.userpw = userpw;
		}
		
		
		

		public Dao(String userid, String userpw, String username) {
			super();
			this.userid = userid;
			this.userpw = userpw;
			this.username = username;
		}

		public boolean insertAction() {
			PreparedStatement ps = null; //?쓰려면 prepare사용 arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				
				String A = "insert into customer(userid,userpw,username,userphone,userImage,filepath)"; //실행할 쿼리
				String B=" values(?,?,?,?,?,?)";
				
				ps = conn_mysql.prepareStatement(A+B);
				ps.setString(1, userid);
				ps.setString(2, userpw);
				ps.setString(3, username);
				ps.setString(4, userphone);
				ps.setBinaryStream(5, file);
				ps.setString(6, filepath);
				ps.executeUpdate();
				
				conn_mysql.close();

				}
				
				
			catch (Exception e) {
				return false;
				// TODO: handle exception
			}return true;
			}
		
		
			
		public boolean check() {
			String login = "";
			Dto dto = null;  // arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				HashMap<String, String> memberlist = new HashMap<>();
				
				String where1 = "select userid,userpw from customer"; //실행할 쿼리
				
				ResultSet rs =stmt_mysql.executeQuery(where1);  //whereDefault 실행
				
				//while(rs.next()) {
				while(rs.next()) {
					String[] qTxt = {rs.getString(1),rs.getString(2)};
					memberlist.put(qTxt[0], qTxt[1]);
					
				}
				for(String key : memberlist.keySet()) {
					if(userid.equals(key.trim())) {
						login = "중복";
					}
					
					conn_mysql.close();
				}}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			if(login.equals("중복")) {
				return false;
			}else {
				return true;
			}}
		


		public ArrayList<Dto> confirm(String id, String pw){
			ArrayList<Dto> dtoList1 = new ArrayList<Dto>();
			String query = "select userid,userpw,username,userphone,userImage,filepath from customer "; //실행할 쿼리
			String query2 =" where userid='"+id+"' and  userpw='"+pw+"'";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				
				ResultSet rs =stmt_mysql.executeQuery(query+query2);
				
				while(rs.next()) {
					String wkid = rs.getString(1);
					String wkpw=rs.getString(2);
					String wkname = rs.getString(3);
					String wkphone = rs.getString(4);
					String wkpath = rs.getString(6);
					

					Dto dto = new Dto(wkid,wkpw,wkname,wkphone,wkpath);  // dto에 이 값을 한번에 저장한다.
					dtoList1.add(dto);
					
				} //파일 새롭게 만들기. 
				File file = new File(Integer.toString(ShareVar.filename));
				FileOutputStream output = new FileOutputStream(file); //출력 파일
				InputStream input = rs.getBinaryStream(5);
				byte[] buffer = new byte[1024]; //파일 크기 제한하기.
				while(input.read(buffer)>0) {
					output.write(buffer);
				}
				conn_mysql.close();
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}return dtoList1;
			
		}
			
		
			
			
		/*public boolean confirm() {
			String login = "";
			Dto dto = null;  // arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				HashMap<String, String> memberlist = new HashMap<>();
				
				String where1 = "select userid,userpw from customer"; //실행할 쿼리
				
				ResultSet rs =stmt_mysql.executeQuery(where1);  //whereDefault 실행
				
				//while(rs.next()) {
				while(rs.next()) {
					String[] qTxt = {rs.getString(1),rs.getString(2)};
					memberlist.put(qTxt[0], qTxt[1]);
					
				}
				for(String key : memberlist.keySet()) {
					if(userid.equals(key.trim())&&userpw.equals(memberlist.get(key))) {
						login = "성공";
					}
					
					conn_mysql.close();
				}}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			if(login.equals("성공")) {
				return false;
			}else {
				return true;
			}}*/
			
		
		
		public Dto select() {
			//데이터 하나씩만 받을거라 arraylist안씀
			
			Dto dto = null;  // arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			String where1 = "select userid,username from customer"; //실행할 쿼리
			String where2=" where userid="+userid;
			HashMap<String, String> memberlist = new HashMap<>();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				
				ResultSet rs =stmt_mysql.executeQuery(where1+where2);  //whereDefault 실행
				
				
				//while(rs.next()) {
				while(rs.next()) {
					String[] qTxt = {rs.getString(1),rs.getString(2)};
					memberlist.put(qTxt[0], qTxt[1]);
					
				}
					dto = new Dto(userid, username);  // dto에 이 값을 한번에 저장한다.
				conn_mysql.close();
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}return dto;
			
		

}
		public boolean check1() {
			String login = "";
			Dto dto = null;  // arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				HashMap<String, String> memberlist = new HashMap<>();
				
				String where1 = "select userid,userpw from customer"; //실행할 쿼리
				
				ResultSet rs =stmt_mysql.executeQuery(where1);  //whereDefault 실행
				
				//while(rs.next()) {
				while(rs.next()) {
					String[] qTxt = {rs.getString(1),rs.getString(2)};
					memberlist.put(qTxt[0], qTxt[1]);
					
				}
				for(String key : memberlist.keySet()) {
					if(userid.equals(key.trim())) {
						login = "중복";
					}
					
					conn_mysql.close();
				}}
			
			catch(Exception e) {
				e.printStackTrace();
			}
			if(login.equals("중복")) {
				return false;
			}else {
				return true;
			}}
		
		public boolean updateAction() {
			PreparedStatement ps = null; //?쓰려면 prepare사용 arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				
				String A = "update customer set userpw=?, username=?, userphone=?, userImage=?,filepath=?"; //실행할 쿼리
				String B=" where userid=?";
				ps = conn_mysql.prepareStatement(A+B);
				
				ps.setString(1, userpw);
				ps.setString(2, username);
				ps.setString(3, userphone);
				ps.setBinaryStream(4, file);
				ps.setString(5, filepath);
				ps.setString(6, userid);
				ps.executeUpdate();
				
				conn_mysql.close();

				}
				
				
			catch (Exception e) {
				return false;   //e.printStackTrace(); --오류정보를 확인할 수 있다.
				// TODO: handle exception
			}return true;}
		
		public Dao(String userid, String userpw, String username, String userphone) {
			super();
			this.userid = userid;
			this.userpw = userpw;
			this.username = username;
			this.userphone = userphone;
		}

		public boolean DeleteAction() {
			PreparedStatement ps = null; //?쓰려면 prepare사용 arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				
				String A = "delete from customer"; //실행할 쿼리
				String B=" where userid=?";
				
				ps = conn_mysql.prepareStatement(A+B);
				ps.setNString(1, userid);
				ps.executeUpdate();
				
				conn_mysql.close();

				}
				
				
			catch (Exception e) {
				return false;
				// TODO: handle exception
			}return true;
			
		}	
		public Dto Action() {
			//데이터 하나씩만 받을거라 arraylist안씀
			
			Dto dto = null;  // arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			String where1 = "select username,userphone from customer"; //실행할 쿼리
			String where2=" where userid="+ShareVar.userid.trim();
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				
				ResultSet rs =stmt_mysql.executeQuery(where1+where2);  //whereDefault 실행
				
				//while(rs.next()) {
				if(rs.next()) {
					String wkName=rs.getString(1);
					String wkTel=rs.getString(2);
					
					dto = new Dto(wkName,wkTel);  // dto에 이 값을 한번에 저장한다.
				}
				conn_mysql.close();
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}return dto;
			
		}
		
		public Dto Action2() {
			//데이터 하나씩만 받을거라 arraylist안씀
			
			Dto dto = null;  // arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			String where1 = "select userid,userpw,username,userphone,userImage,filepath from customer"; //실행할 쿼리
			String where2=" where userid='"+ShareVar.userid+"'";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				
				ResultSet rs =stmt_mysql.executeQuery(where1+where2);  //whereDefault 실행
				
				//while(rs.next()) {
				while(rs.next()) {
					String wkid = rs.getString(1);
					String wkpw=rs.getString(2);
					String wkname=rs.getString(3);
					String wkphone=rs.getString(4);
					String wkfilepath = rs.getString(6);
					
					//image
					File file = new File(Integer.toString(ShareVar.filename));
					FileOutputStream output = new FileOutputStream(file); //출력 파일
					InputStream input = rs.getBinaryStream(5);
					byte[] buffer = new byte[1024]; //파일 크기 제한하기.
					while(input.read(buffer)>0) {
						output.write(buffer);
					}
					
					dto = new Dto(wkid,wkpw,wkname,wkphone,wkfilepath);  // dto에 이 값을 한번에 저장한다.
				}
				conn_mysql.close();
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}return dto;
			
		}
		
		public boolean out() {
			PreparedStatement ps = null; //?쓰려면 prepare사용 arraylist안쓰고 null값으로 표현한다. //위에서 선언해야 try문 밖에서도 사용가능.
			
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				
				String A = "delete from customer"; //실행할 쿼리
				String B=" where userid=?";
				
				ps = conn_mysql.prepareStatement(A+B);
				ps.setString(1, userid);
				ps.executeUpdate();
				
				conn_mysql.close();

				}
				
				
			catch (Exception e) {
				return false;
				// TODO: handle exception
			}return true;
			
		}	
		public Dto pwch() {
			Dto dto = null;
			
			String where1 = "select userpw from customer";
			String where2 = " where userid="+userid;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();  //  db를 연결
				
				ResultSet rs =stmt_mysql.executeQuery(where1+where2);  //whereDefault 실행
				
				//while(rs.next()) {
				if(rs.next()) {
					String wkid = rs.getString(1);
					String wkpw = rs.getString(2);
		
					
					dto = new Dto(wkid,wkpw);  // dto에 이 값을 한번에 저장한다.
				}
				conn_mysql.close();
				
				
			}catch (Exception e) {
				// TODO: handle exception
			}return dto;
			
		}}