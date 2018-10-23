package db;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

@WebServlet("/methods")
public class methods extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection con = null;
	static Statement st = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	static String s1, s2, s3, s4, s5, s6;

	public methods() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	public static void connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "1234");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int insert(HttpServletRequest req) {
		connection();
		int rt = 0;
		s1 = req.getParameter("username");
		s2 = req.getParameter("password");
		if (s1 != null && s2 != null) {
			try {
				st = con.createStatement();
				rt = st.executeUpdate("insert into db values('" + s1 + "','" + s2 + "')");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rt;
	}

	public static int update(HttpServletRequest req) {
		connection();
		int rt = 0;
		s1 = req.getParameter("username");
		s2 = req.getParameter("password");
		if (s1 != null && s2 != null) {
			try {
				ps = con.prepareStatement("update db set pass=? where name=?");
				ps.setString(1, s2);
				ps.setString(2, s1);
				rt = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rt;
	}

	public static int delete(HttpServletRequest req) {
		connection();
		int rt = 0;
		s1 = req.getParameter("username");
		if (s1 != null) {
			try {
				ps = con.prepareStatement("delete from db where name=?");
				ps.setString(1, s1);
				rt = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rt;
	}

	public static int show(HttpServletRequest req, HttpServletResponse res) throws IOException {
		connection();
		PrintWriter out = res.getWriter();
		int rowCount = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from db");

			out.println("<P ALIGN='center'><TABLE BORDER=1>");
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			out.println("<TR>");
			for (int i = 0; i < columnCount; i++) {
				out.println("<TH>" + rsmd.getColumnLabel(i + 1) + "</TH>");
			}
			out.println("</TR>");
			while (rs.next()) {
				rowCount++;
				out.println("<TR>");
				for (int i = 0; i < columnCount; i++) {
					out.println("<TD>" + rs.getString(i + 1) + "</TD>");
				}
				out.println("</TR>");
			}
			out.println("</TABLE></P>");
			/*
			 * while(rs.next()) {
			 * 
			 * }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowCount;
	}

}
