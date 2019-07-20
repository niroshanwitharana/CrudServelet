package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBHandler;
import com.user.User;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  
		PrintWriter out= response.getWriter();
		out.println("<a href= 'index.jsp'> Add New User</a>");
		out.println("<h>Users List</h>");
		
		List<User>list= DBHandler.getAllUsers();
		
		out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Id</th><th>Name</th><th>Email</th><th>Password</th><th>Country</th><th>Edit</th><th>Delete</th></tr>");  
		
        for(User us:list){  
            out.print("<tr><td>"+us.getId()+"</td>"
            		+ "<td>"+us.getName()+"</td>"
            		+ "<td>"+us.getEmail()+"</td>"
            		+ "<td>"+us.getPassword()+"</td>"
            		+ "<td>"+us.getCountry()+"</td>"
            		+ "<td><a href='UpdateServlet?id="+us.getId()+"'>edit</a></td>"
            		+ "<td><a href='DeleteServlet?id="+us.getId()+"'>delete</a></td></tr>");  
           }  
           out.print("</table>");
        
           out.close();
           
   		doGet(request, response);
	}

}
