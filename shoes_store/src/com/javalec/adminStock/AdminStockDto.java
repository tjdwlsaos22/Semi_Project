package com.javalec.adminStock;

import java.io.FileInputStream;
import java.sql.Date;

public class AdminStockDto {
	
	int oseq;
	String obrand;
	String oname;
	int oprice;
	int ocnt;
	int osize;
	String ocolor;
	Date odate;
	
	public AdminStockDto() {
		// TODO Auto-generated constructor stub
	}

	

	public AdminStockDto(int oseq, String obrand, String oname, int oprice, int ocnt, int osize, String ocolor) {
		super();
		this.oseq = oseq;
		this.obrand = obrand;
		this.oname = oname;
		this.oprice = oprice;
		this.ocnt = ocnt;
		this.osize = osize;
		this.ocolor = ocolor;
	}


	public int getOseq() {
		return oseq;
	}

	public void setOseq(int oseq) {
		this.oseq = oseq;
	}

	public String getObrand() {
		return obrand;
	}

	public void setObrand(String obrand) {
		this.obrand = obrand;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public int getOprice() {
		return oprice;
	}

	public void setOprice(int oprice) {
		this.oprice = oprice;
	}

	public int getOcnt() {
		return ocnt;
	}

	public void setOcnt(int ocnt) {
		this.ocnt = ocnt;
	}

	public int getOsize() {
		return osize;
	}

	public void setOsize(int osize) {
		this.osize = osize;
	}

	public String getOcolor() {
		return ocolor;
	}

	public void setOcolor(String ocolor) {
		this.ocolor = ocolor;
	}

	public Date getOdate() {
		return odate;
	}

	public void setOdate(Date odate) {
		this.odate = odate;
	}
	
	

}
