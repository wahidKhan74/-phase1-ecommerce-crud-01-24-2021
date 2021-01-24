package com.ecommerce.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;

/**
 * Servlet implementation class AddProducts
 */
@WebServlet("/AddProducts")
public class AddProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			PrintWriter out = response.getWriter();

			// 1. load data from config.properties
			Properties properties = new Properties();
			properties.load(getServletContext().getResourceAsStream("/WEB-INF/config.properties"));

			// 2. create connection
			DBConnection conn = new DBConnection(properties.getProperty("url"), properties.getProperty("username"),
					properties.getProperty("password"));

			// 3. create statement
			Statement stm = conn.getConnection().createStatement();

			// 4. execute query
			String query = "INSERT INTO eproduct (name,price) values ('DELL Laptop XYZ', 7000);";
			int no = stm.executeUpdate(query);
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
