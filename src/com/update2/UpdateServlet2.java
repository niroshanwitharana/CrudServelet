package com.update2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbcon.DBHandler;
import com.user.User;

/**
 * Servlet implementation class UpdateServlet2
 */
@WebServlet("/UpdateServlet2")
public class UpdateServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet2() {
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
		
		String sid= request.getParameter("id");
		int id= Integer.parseInt(sid);
		
		String name=request.getParameter("name");  
		String email=request.getParameter("email");
        String password=request.getParameter("password");          
        String country=request.getParameter("country"); 
        
        User us= new User();
        us.setId(id);
        us.setName(name);
        us.setEmail(email);
        us.setPassword(password);
        us.setCountry(country);
        
        int status=DBHandler.update(us);
        if(status>0) {
        	response.sendRedirect("ViewServlet");
        }
        else {
        	out.println("Sorry ! Unable to update Record");
        }
        
		
		
		doGet(request, response);
	}

}
