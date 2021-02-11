package com.crud.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crud.dao.UserDao;
import com.crud.model.User;

/**
 * Servlet implementation class UserServ
 */
public class UserServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String INSERT = "/user.jsp";
    private static String Edit = "/edit.jsp";
    private static String UserRecord = "/listUser.jsp";
    private UserDao dao;

    /**
     * Default constructor. 
     */
    public UserServ() {
        // TODO Auto-generated constructor stub
    	super();
        dao = new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		String redirect="";
        String uId = request.getParameter("userid");        
        String action = request.getParameter("action");
        
        if(!((uId)== null) && action.equalsIgnoreCase("insert"))
        {
        	int id = Integer.parseInt(uId);
        	User user = new User();
        	user.setId(id);
            user.setfName(request.getParameter("firstName"));
            user.setlName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
        	dao.addUser(user);
        	redirect = UserRecord;
            request.setAttribute("users", dao.getAllUsers());    
           	System.out.println("Record Added Successfully");
        }
        else if (action.equalsIgnoreCase("delete"))
        {
            String userId = request.getParameter("userId");
            int uid = Integer.parseInt(userId);
            dao.removeUser(uid);
            redirect = UserRecord;
            request.setAttribute("users", dao.getAllUsers());
            System.out.println("Record Deleted Successfully");
        }
        else if (action.equalsIgnoreCase("editform"))
        {        	
        	redirect = Edit;            
        } 
        else if (action.equalsIgnoreCase("edit"))
        {
        	String userId = request.getParameter("userId");
            int uid = Integer.parseInt(userId);            
            User user = new User();
        	user.setId(uid);
            user.setfName(request.getParameter("firstName"));
            user.setlName(request.getParameter("lastName"));
            user.setEmail(request.getParameter("email"));
            dao.editUser(user);
            request.setAttribute("user", user);
            redirect = UserRecord;
            System.out.println("Record updated Successfully");
         } 
         else if (action.equalsIgnoreCase("listUser"))
         {
            redirect = UserRecord;
            request.setAttribute("users", dao.getAllUsers());
         } 
         else 
         {
            redirect = INSERT;
         }
        
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
        
		}catch(NumberFormatException e) {
			System.out.print(e + " Give valid input");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
