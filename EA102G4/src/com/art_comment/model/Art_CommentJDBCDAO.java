package com.art_comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.article.model.ArticleJDBCDAO;


public class Art_CommentJDBCDAO implements Art_CommentDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "INGYM";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO ART_COMMENT (COM_NO, ARTICLE_NO, MEM_ID, MES_CONTENT, COM_STATUS) VALUES ('COM'||LPAD( SEQ_COM_NO.NEXTVAL,6,'0'), ?, ?, ?,?)"; //會員留言新增
	
	private static final String GET_ALL_STMT = "SELECT COM_NO , ARTICLE_NO, MEM_ID, MES_CONTENT, COM_RELEASE, COM_STATUS FROM ART_COMMENT";    //查詢全部會員留言

	private static final String GET_ONE_STMT = "SELECT * FROM ART_COMMENT WHERE COM_NO = ?";
	
	private static final String DELETE = "DELETE FROM ART_COMMENT WHERE COM_NO = ?";
	
	private static final String UPDATE = "UPDATE ART_COMMENT SET  MES_CONTENT=? WHERE COM_NO=?  ";

	@Override
	public void insert(Art_CommentVO Art_CommentVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			System.out.println("JDBC");
			System.out.println("article_no = "+Art_CommentVO.getArticle_no());
			System.out.println("mem_id = "+Art_CommentVO.getMem_id());
			System.out.println("mes_content = "+Art_CommentVO.getMes_content());
			System.out.println("mes_content = "+Art_CommentVO.getCom_status());
			
			pstmt.setString(1, Art_CommentVO.getArticle_no());
			pstmt.setString(2, Art_CommentVO.getMem_id());
			pstmt.setString(3, Art_CommentVO.getMes_content());
			pstmt.setString(4, Art_CommentVO.getCom_status());
			
			pstmt.executeUpdate();
			
			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}


@Override
public void update(Art_CommentVO Art_CommentVO) {

	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE);

		
		pstmt.setString(1, Art_CommentVO.getMes_content());
		pstmt.setString(2, Art_CommentVO.getCom_no());
				

		pstmt.executeUpdate();

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

 }


@Override
public void delete(String com_no) {

	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(DELETE);

		pstmt.setString(1, com_no);

		pstmt.executeUpdate();

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}

  }

@Override
public Art_CommentVO findByPrimaryKey(String mem_no) {

	Art_CommentVO art_commentVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setString(1, mem_no);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			art_commentVO = new Art_CommentVO();
			art_commentVO.setCom_no(rs.getString("Com_no"));
			art_commentVO.setArticle_no(rs.getString("Article_no"));
			art_commentVO.setMem_id(rs.getString("Mem_id"));
			art_commentVO.setMes_content(rs.getString("Mes_content"));
			art_commentVO.setCom_release(rs.getTimestamp("Com_release"));
			art_commentVO.setCom_status(rs.getString("Com_status"));
			System.out.println("JDBC test");
		}

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	return art_commentVO;
}

@Override
public List<Art_CommentVO> getAll() {
	List<Art_CommentVO> list = new ArrayList<Art_CommentVO>();
	Art_CommentVO art_commentVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVO 也稱為 Domain objects
			art_commentVO = new Art_CommentVO();
			art_commentVO.setCom_no(rs.getString("Com_no"));
			art_commentVO.setArticle_no(rs.getString("Article_no"));
			art_commentVO.setMem_id(rs.getString("Mem_id"));
			art_commentVO.setMes_content(rs.getString("Mes_content"));
			art_commentVO.setCom_release(rs.getTimestamp("Com_release"));
			art_commentVO.setCom_status(rs.getString("Com_status"));
			list.add(art_commentVO); // Store the row in the list
		}

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	return list;
}

	public static void main(String[] args) {
		Art_CommentJDBCDAO art = new Art_CommentJDBCDAO();
		
		// 新增留言
//		Art_CommentVO art_commentVO1 = new Art_CommentVO();
//		art_commentVO1.setArticle_no("ART001");
//		art_commentVO1.setMem_id("MEM0000020");
//		art_commentVO1.setMes_content("zzz無聊的文章");
//		art_commentVO1.setCom_status("CA1");
//		art.insert(art_commentVO1);
//		System.out.println("新增成功");
//	
		


//		// 修改留言
//		Art_CommentVO art_commentVO2 = new Art_CommentVO();	
//
//		art_commentVO2.setMes_content("留言內容修改");
//		art_commentVO2.setCom_no("COM000006");			
//		
//		art.update(art_commentVO2);
//		
//		System.out.println("修改成功");
		
		


		// 刪除
		art.delete("COM000033");
		System.out.println("刪除成功");

		// 單一查詢
//		Art_CommentVO art_commentVO3 = art.findByPrimaryKey("COM000005");
//		System.out.println("留言編號:"+art_commentVO3.getCom_no() );
//		System.out.println("文章編號:"+art_commentVO3.getArticle_no());
//		System.out.println("會員編號(回文):"+art_commentVO3.getMem_id() );
//		System.out.println("留言內容:"+art_commentVO3.getMes_content() );
//		System.out.println("發佈時間:"+art_commentVO3.getCom_release() );
//		System.out.println("留言狀態:"+art_commentVO3.getCom_status() );
//		
//		System.out.println("---------------------");
//		
		
//		// 查詢全部
//				List<Art_CommentVO> list = art.getAll();
//				for (Art_CommentVO aArt_CommentVO : list) {
//					System.out.println("留言編號:"+aArt_CommentVO.getArticle_no() );
//					System.out.println("文章編號:"+aArt_CommentVO.getArticle_no() );
//					System.out.println("會員編號(回文):"+aArt_CommentVO.getMem_id() );
//					System.out.println("留言內容:"+aArt_CommentVO.getMes_content() );
//					System.out.println("發佈時間:"+aArt_CommentVO.getCom_release() );
//					System.out.println("留言狀態:"+aArt_CommentVO.getCom_status() );
//					
//					System.out.println();
//					System.out.println("---------------------");
//				}
	}
}