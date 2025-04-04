package com.sijan.demorest;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class LionRepository {

	Connection con = null;
	public LionRepository() {
		String url = "jdbc:mysql://localhost:3306/restdb";
        String user = "root";
        String password = "sijan.@34";
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection(url, user, password);  
	        
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException(e);
	    }
	}	
	public List<Lion> getLions(){
		List<Lion> lions = new ArrayList<Lion>();
		String query = "select * from lion";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
			Lion l = new Lion();
			l.setId(rs.getInt(1));
			l.setName(rs.getString(2));
			l.setPoints(rs.getInt(3));
			
			lions.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lions;
	}
	public Lion getLion(int id) {
		String query = "select * from lion where id ="+id;
		Lion l = new Lion();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next()) {
			l.setId(rs.getInt(1));
			l.setName(rs.getString(2));
			l.setPoints(rs.getInt(3));
			return l;
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public void create(Lion l1) {
		String query = "insert into lion values (?,?,?)";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, l1.getId());
			st.setString(2, l1.getName());
			st.setInt(3, l1.getPoints());
			st.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Lion l1) {
		String query = "update lion set name=?, points=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(3, l1.getId());
			st.setString(1, l1.getName());
			st.setInt(2, l1.getPoints());
			st.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(int id) {
		String query = "delete from lion where id=?";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, id);
			st.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
