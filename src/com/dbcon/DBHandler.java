package com.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.user.User;

public class DBHandler {
	
	public static Connection getConnection() {
		
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/niro", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
		
		
	}

	public static int save(User us) {
		int status=0;
		
		Connection con= DBHandler.getConnection();
		try {
			PreparedStatement ps=con.prepareStatement("insert into user(uname, uemail, upass, ucounty) values(?,?,?,?)");
		
		
		ps.setString(1,us.getName());
		ps.setString(2,us.getEmail());
		ps.setString(3,us.getPassword());
		ps.setString(4,us.getCountry());
		
		status=ps.executeUpdate();
		
		con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	
	}


	public static int update(User us){
		
		int status=0;
		
		Connection con= DBHandler.getConnection();
		try {
			PreparedStatement ps= con.prepareStatement("update user set uname=?, uemail=?, upass=?, ucounty=? where uid=?");
		
		
		ps.setString(1,us.getName());
		ps.setString(2,us.getEmail());
		ps.setString(3,us.getPassword());
		ps.setString(4,us.getCountry());
		
		ps.setInt(5,us.getId());
		
		status=ps.executeUpdate();
		
		con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
		
	}
	
	public static int delete(int id) {
		
		int status=0;
		
		Connection con= DBHandler.getConnection();
		try {
			PreparedStatement ps= con.prepareStatement("delete user where uid=?");
		
			ps.setInt(1,id);
			ps.executeUpdate();
			con.close();
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return status;
		
	}
	
	public static User getUsersById(int id) {
		
		User us= new User();
		
		
		Connection con= DBHandler.getConnection();
		
		try {
			PreparedStatement ps= con.prepareStatement("select * from user where id=?");
			
			ps.setInt(1, id);
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				us.setId(rs.getInt(1));
				us.setName(rs.getString(2));
				us.setEmail(rs.getString(3));
				us.setPassword(rs.getString(4));
				us.setCountry(rs.getString(5));
				
				
			}
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return us;
		
		
	}
	
	public static List<User> getAllUsers(){
		
		List<User> list= new ArrayList<User>();
		
		Connection con= DBHandler.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("select * from user");
		
		 ResultSet rs=ps.executeQuery();  
		while(rs.next()) {
			
			User us= new User();
			
			us.setId(rs.getInt(1));
			us.setName(rs.getString(2));
			us.setEmail(rs.getString(3));
			us.setPassword(rs.getString(4));
			us.setCountry(rs.getString(5));
			list.add(us);
		}
		
		con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}
	
}