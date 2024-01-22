package com.javalec.customer;

import java.io.FileInputStream;

public class Dto {
	
	String userid;
	String userpw;
	String username;
	String userphone;
	FileInputStream file;
	String filepath;
	
	public FileInputStream getFile() {
		return file;
	}

	public void setFile(FileInputStream file) {
		this.file = file;
	}

	public Dto() {
		// TODO Auto-generated constructor stub
	}

	public Dto(String userid, String userpw, String username, String userphone, String filepath) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.userphone = userphone;
		this.filepath = filepath;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Dto(String userid, String userpw, String username, String userphone, FileInputStream file, String filepath) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.userphone = userphone;
		this.file = file;
		this.filepath = filepath;
	}

	public Dto(String userid, String userpw, String username, String userphone) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.userphone = userphone;
	}
	
	

	public Dto(String username, String userid) {
		super();
		this.username = username;
		this.userid = userid;
	}
	

	public Dto(String userid, String userpw, String username) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
	}

	public Dto(String userid) {
		super();
		this.userid = userid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	
	

}
