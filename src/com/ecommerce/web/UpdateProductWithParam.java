package com.ecommerce.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
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
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProductWithParam")
public class UpdateProductWithParam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProductWithParam() {
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

			// 3. execute query
			String query = "update eproduct set name=? where id=?;";
						
			// 4. create statement
			PreparedStatement pstm = conn.getConnection().prepareStatement(query);
			
			// 5. set parameter values.
			pstm.setString(1, "Asus Laptop XYZ");
			pstm.setInt(2, 3);
			
			int no = pstm.executeUpdate();
			out.println("<html><body>");
			if (no > 0) {
				out.println("<h2> Product update Successfully !</h2>");
			} else {
				out.println("<h2> Product cannot be updated !</h2>");
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
