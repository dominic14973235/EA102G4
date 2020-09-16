package com.worker.model;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WorkerPTool extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "INGYM";
	private static final String PASSWORD = "123456";
	private static final String SELECTPIC = "SELECT PHOTO FROM WORKER WHERE WORK_ID=?";

	Connection con;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pstmt = null;

		req.setCharacterEncoding("UTF-8");
		ServletOutputStream out = res.getOutputStream();

		try {
			String work_id = req.getParameter("work_id");
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SELECTPIC);
			pstmt.setString(1, work_id);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			if (rs.getBinaryStream("photo")!=null) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("photo"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
			System.out.println("exception");
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
}
