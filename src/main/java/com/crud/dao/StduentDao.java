package com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.been.StudentBeen;
import com.crud.database.Database;

public class StduentDao {

	public boolean save(StudentBeen been) {
		Connection conn = Database.getConnetion();
				try {
				PreparedStatement pst = conn.prepareStatement("Insert into students "
						+ "(name,mobile,email) values(?,?,?)");
				pst.setString(1, been.getName());
				pst.setString(2, been.getMobile());
				pst.setString(3, been.getEmail());
				int i= pst.executeUpdate();
				if(i==1)
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return false;
	}

	public List<StudentBeen> getData() {
		List<StudentBeen> students = new ArrayList<StudentBeen>();
		try {
			PreparedStatement pst = Database.getConnetion().prepareStatement("Select id,name,mobile,email from students");
			ResultSet rs = pst .executeQuery();
			while(rs.next()) {
				StudentBeen been = new StudentBeen(rs.getInt("id"));
				been.setName( rs.getString("name") );
				been.setMobile( rs.getString("mobile") );
				been.setEmail( rs.getString("email") );
				students.add(been);
			}
			return students;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	public boolean delete(int id) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn =  Database.getConnetion();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE from students where id = ?");
			ps.setInt(1, id);
			
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public StudentBeen getStudent(int std_id) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Connection conn = Database.getConnetion();
		try {
			PreparedStatement ps = conn.prepareStatement("Select id ,name,mobile,email from students where id=?");
			ps.setInt(1, std_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				StudentBeen been = new StudentBeen(rs.getInt("id"), rs.getString("name"), rs.getString("mobile"), rs.getString("email"));
				
				return been;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(StudentBeen been) {
		Connection conn = Database.getConnetion();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE students set name = ?,mobile=?,email=? where id=?");
			ps.setString(1, been.getName());
			ps.setString(2, been.getMobile());
			ps.setString(3, been.getEmail());
			ps.setInt(4, been.getId());
			if(ps.executeUpdate()==1)
				return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
}



