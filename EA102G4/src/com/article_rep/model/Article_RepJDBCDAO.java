package com.article_rep.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Article_RepJDBCDAO  implements Article_RepDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "INGYM";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO ARTICLE_REP (ART_REP_NO, MEM_ID, ARTICLE_NO, REP_CONTENT) VALUES ('REP'||LPAD( ART_REP_NO.NEXTVAL,3,'0'), ?, ?, ?)"; //會員檢舉新增
	
	private static final String GET_ALL_STMT = "SELECT ART_REP_NO , MEM_ID, ARTICLE_NO, REP_CONTENT, REP_TIME, COM_STATUS FROM ARTICLE_REP";    //查詢全部檢舉留言

	private static final String GET_ONE_STMT = "SELECT * FROM ARTICLE_REP WHERE ART_REP_NO = ?";
	
	private static final String DELETE = "DELETE FROM ARTICLE_REP WHERE ART_REP_NO = ?";
	
	private static final String UPDATE = "UPDATE ARTICLE_REP SET  REP_CONTENT=?, COM_STATUS=? WHERE ART_REP_NO=?  ";
	
	@Override
	public void insert(Article_RepVO article_repVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, article_repVO.getMem_id());
			pstmt.setString(2, article_repVO.getArticle_no());
			pstmt.setString(3, article_repVO.getRep_content());
			
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
public void update(Article_RepVO article_repVO) {

	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(UPDATE);

		
		pstmt.setString(1, article_repVO.getRep_content());
		pstmt.setString(2, article_repVO.getArt_rep_no());
		pstmt.setString(3, article_repVO.getCom_status());
				

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
public void delete(String art_rep_no) {

	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(DELETE);

		pstmt.setString(1, art_rep_no);

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
public Article_RepVO findByPrimaryKey(String art_rep_no) {

	Article_RepVO article_repVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setString(1, art_rep_no);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			
			article_repVO = new Article_RepVO();
			article_repVO.setArt_rep_no(rs.getString("Art_rep_no"));
			article_repVO.setMem_id(rs.getString("Mem_id"));
			article_repVO.setArticle_no(rs.getString("Article_no"));
			article_repVO.setRep_content(rs.getString("Rep_content"));
			article_repVO.setRep_time(rs.getTimestamp("Rep_time"));
			article_repVO.setCom_status(rs.getString("Com_status"));
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
	return article_repVO;
}

@Override
public List<Article_RepVO> getAll() {
	List<Article_RepVO> list = new ArrayList<Article_RepVO>();
	Article_RepVO article_repVO = null;

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
			article_repVO = new Article_RepVO();
			article_repVO.setArt_rep_no(rs.getString("Art_rep_no"));
			article_repVO.setMem_id(rs.getString("Mem_id"));
			article_repVO.setArticle_no(rs.getString("Article_no"));
			article_repVO.setRep_content(rs.getString("Rep_content"));
			article_repVO.setRep_time(rs.getTimestamp("Rep_time"));
			article_repVO.setCom_status(rs.getString("Com_status"));
			list.add(article_repVO); // Store the row in the list
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
		
		Article_RepJDBCDAO art = new Article_RepJDBCDAO();
		
		// 新增檢舉
//		Article_RepVO article_repVO = new Article_RepVO();
//		article_repVO.setMem_id("MEM0000022");
//		article_repVO.setArticle_no("ART001");
//		article_repVO.setRep_content("zzz無聊的文章");
//		art.insert(article_repVO);
//		System.out.println("新增成功");
//	
		


//		// 修改留言
		Article_RepVO article_repVO1 = new Article_RepVO();	

		article_repVO1.setRep_content("留言內容修klklklk改001");
		article_repVO1.setArt_rep_no("REP001");			
		article_repVO1.setCom_status("R1");	
		art.update(article_repVO1);
		
		System.out.println("修改成功");
		
		


		// 刪除
//		art.delete("REP003");
//		System.out.println("刪除成功");

		// 單一查詢
//		Article_RepVO Article_RepVO3 = art.findByPrimaryKey("REP002");
//		System.out.println("檢舉編號:"+article_repVO3.getArt_rep_no() );
//		System.out.println("文章編號:"+article_repVO3.getArt_rep_no());
//		System.out.println("會員編號(檢舉):"+article_repVO3.getMem_id() );
//		System.out.println("檢舉原因:"+article_repVO3.getRep_content() );
//		System.out.println("檢舉時間:"+article_repVO3.getRep_time() );
//		System.out.println("處理檢舉狀態:"+article_repVO3.getCom_status() );
//		
//		System.out.println("---------------------");
		
		
//		
		
//		// 查詢全部
//				List<Article_RepVO> list = art.getAll();
//				for (Article_RepVO aArticle_RepVO : list) {
//					System.out.println("留言編號:"+aArticle_RepVO.getArt_rep_no() );
//					System.out.println("文章編號:"+aArticle_RepVO.getArt_rep_no() );
//					System.out.println("會員編號(回文):"+aArticle_RepVO.getMem_id() );
//					System.out.println("留言內容:"+aArticle_RepVO.getRep_content() );
//					System.out.println("發佈時間:"+aArticle_RepVO.getRep_time() );
//					System.out.println("留言狀態:"+aArticle_RepVO.getCom_status() );
//					
//					System.out.println();
//					System.out.println("---------------------");
//				}
		
	}
}
