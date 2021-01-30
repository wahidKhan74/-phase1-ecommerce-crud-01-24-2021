package com.ecommerce.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class CallAddProductStoredProcedure
 */
@WebServlet("/CallAddProductStoredProcedure")
public class CallAddProductStoredProcedure extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallAddProductStoredProcedure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			PrintWriter out = response.getWriter();

			// 1. load data from config.properties
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

			// 2. create connection
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));
			
			// 3. create statement
			CallableStatement cstm = conn.getConnection().prepareCall("{call add_product(?,?)}");
			cstm.setString(1, "Random Product");
			cstm.setDouble(2, 9945);

			
			int no = cstm.executeUpdate();
			out.println("<html><body>");
			if(no>0) {
				out.println("<h2> Product added Successfully !</h2>");
			} else {
				out.println("<h2> Product cannot be added !</h2>");
			}
			
			out.println("</body></html>");
			conn.closeConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
