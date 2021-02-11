package com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crud.model.User;

public class UserDao {
	
	private Connection conn;
	
	public UserDao() {
    	conn = UserDbcon.getConnection();
    }
	
	public void addUser(User userModel) {
        try {
        	String sql = "INSERT INTO users(userid, firstname,lastname, email)" +
    		" VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userModel.getId());
            ps.setString(2, userModel.getfName());
            ps.setString(3, userModel.getlName());
            ps.setString(4, userModel.getEmail());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void removeUser(int userId) {
        try {
        	String sql = "DELETE FROM users WHERE userid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
      }
	
	public void editUser(User userBean) {    	
    	try {
    		String sql = "UPDATE users SET firstname=?, lastname=?, email=?" +
            " WHERE userid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userBean.getfName());
            ps.setString(2, userBean.getlName());
            ps.setString(3, userBean.getEmail());
            ps.setInt(4, userBean.getId());
            ps.executeUpdate();            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
	public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
        	String sql = "SELECT * FROM users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User userBean = new User();
                userBean.setId(rs.getInt("userid"));
                userBean.setfName(rs.getString("firstname"));
                userBean.setlName(rs.getString("lastname"));
                userBean.setEmail(rs.getString("email"));
                users.add(userBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    } 
	
	public User getUserById(int userId) {
    	User userBean = new User();
        try {
        	String sql = "SELECT * FROM users WHERE userid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            	userBean.setId(rs.getInt("userid"));
            	userBean.setfName(rs.getString("firstname"));
            	userBean.setlName(rs.getString("lastname"));
            	userBean.setlName(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBean;
    }

}
