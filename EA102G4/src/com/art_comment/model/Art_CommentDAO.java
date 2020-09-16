package com.art_comment.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class Art_CommentDAO implements Art_CommentDAO_interface{
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		private static final String INSERT_STMT = "INSERT INTO ART_COMMENT (COM_NO, ARTICLE_NO, MEM_ID, MES_CONTENT,COM_STATUS) VALUES ('COM'||LPAD( SEQ_COM_NO.NEXTVAL,6,'0'), ?, ?, ?,?)"; //會員留言新增
		
		private static final String GET_ALL_STMT = "SELECT COM_NO , ARTICLE_NO, MEM_ID, MES_CONTENT, COM_RELEASE, COM_STATUS FROM ART_COMMENT";    //查詢全部會員留言

		private static final String GET_ONE_STMT = "SELECT * FROM ART_COMMENT WHERE COM_NO = ?";
		
		private static final String DELETE = "DELETE FROM ART_COMMENT WHERE COM_NO = ?";
		
		private static final String UPDATE = "UPDATE ART_COMMENT SET  MES_CONTENT=? WHERE COM_NO=?  ";
//		private static final String com_no = null;

		@Override
		public void insert(Art_CommentVO Art_CommentVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, Art_CommentVO.getArticle_no());
				pstmt.setString(2, Art_CommentVO.getMem_id());
				pstmt.setString(3, Art_CommentVO.getMes_content());   
				pstmt.setString(4, Art_CommentVO.getCom_status());
				pstmt.executeUpdate();
				

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

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, Art_CommentVO.getMes_content());
				pstmt.setString(2, Art_CommentVO.getCom_no());
				

				pstmt.executeUpdate();

				// Handle any driver errors
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setString(1, com_no);

				pstmt.executeUpdate();

				// Handle any driver errors
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, mem_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					art_commentVO = new Art_CommentVO();
					art_commentVO.setCom_no(rs.getString("Com_no"));
					art_commentVO.setArticle_no(rs.getString("Article_no"));
					art_commentVO.setMem_id(rs.getString("Mem_id"));
					art_commentVO.setMes_content(rs.getString("Mes_content"));
					art_commentVO.setCom_release(rs.getTimestamp("Com_release"));
					art_commentVO.setCom_status(rs.getString("Com_status"));


				}

				// Handle any driver errors
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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// articleVO 也稱為 Domain
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
		

}
			
				
	
