package com.javalec.purchaseShin;

import java.io.File;
import java.io.InputStream;
import java.sql.Date;

public class OrderDto {

	int prodnum;
	String obrand;
	String oname;
	int pcnt;
	int oprice;
	int oseq;
	int pseq;
	String pdate;
	InputStream input;
	String pfile;
	int psize;
	String ocolor;
	
	public OrderDto() {

	}

	public OrderDto(int prodnum, String obrand,String oname, int pcnt, int psize, String ocolor, int oprice, String pdate) {
		super();
		this.prodnum = prodnum;
		this.obrand = obrand;
		this.oname = oname;
		this.pcnt = pcnt;
		this.psize = psize;
		this.ocolor = ocolor;
		this.oprice = oprice;
		this.pdate = pdate;
	}
	
	
	public OrderDto(int oseq) {
		super();
		this.oseq = oseq;
	}
	
	public int getOseq() {
		return oseq;
	}

	public void setOseq(int oseq) {
		this.oseq = oseq;
	}

	public int getPsize() {
		return psize;
	}

	public void setPsize(int psize) {
		this.psize = psize;
	}

	public int getProdnum() {
		return prodnum;
	}

	public void setProdnum(int prodnum) {
		this.prodnum = prodnum;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public int getPcnt() {
		return pcnt;
	}

	public void setPcnt(int pcnt) {
		this.pcnt = pcnt;
	}

	public int getOprice() {
		return oprice;
	}

	public void setOprice(int oprice) {
		this.oprice = oprice;
	}

	public int getPseq() {
		return pseq;
	}

	public void setPseq(int pseq) {
		this.pseq = pseq;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getPfile() {
		return pfile;
	}

	public void setPfile(String pfile) {
		this.pfile = pfile;
	}

	public String getObrand() {
		return obrand;
	}

	public void setObrand(String obrand) {
		this.obrand = obrand;
	}

	public String getOcolor() {
		return ocolor;
	}

	public void setOcolor(String ocolor) {
		this.ocolor = ocolor;
	}
	
}
