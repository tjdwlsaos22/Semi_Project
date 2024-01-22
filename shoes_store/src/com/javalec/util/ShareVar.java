package com.javalec.util;

import java.util.ArrayList;
import java.util.List;

import com.javalec.product.ProductDTO;

public class ShareVar {
	public static String dbName = "jdbc:mysql://127.0.0.1/shoes?serverTimezone=UTC&characterEncoding=utf8&useSSL=FALSE";
	   public static String dbUser = "root";
	   public static String dbPass = "qwer1234";
	   public static int filename = 0;
	   public static String userid="";
	   public static String password="";
	   public static String name="";
	   public static String phone="";
	   public static List<List<String>> prodList = new ArrayList<>();
}

