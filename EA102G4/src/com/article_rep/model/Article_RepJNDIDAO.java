package com.article_rep.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.art_comment.model.Art_CommentVO;

public class Article_RepJNDIDAO implements Article_RepDAO_interface{
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
		
		private static final String INSERT_STMT = "INSERT INTO ARTICLE_REP (ART_REP_NO, MEM_ID, ARTICLE_NO, REP_CONTENT) VALUES ('REP'||LPAD( ART_REP_NO.NEXTVAL,3,'0'), ?, ?, ?)"; //會員檢舉新增
		
		private static final String GET_ALL_STMT = "SELECT ART_REP_NO , MEM_ID, ARTICLE_NO, REP_CONTENT, REP_TIME, COM_STATUS FROM ARTICLE_REP";    //查詢全部檢舉留言

		private static final String GET_ONE_STMT = "SELECT * FROM ARTICLE_REP WHERE ART_REP_NO = ?";
		
		private static final String DELETE = "DELETE FROM ARTICLE_REP WHERE ART_REP_NO = ?";
		
		private static final String UPDATE = "UPDATE ARTICLE_REP SET  REP_CONTENT=? WHERE ART_REP_NO=?  ";
		
		@Override
		public void insert(Article_RepVO article_repVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, article_repVO.getMem_id());
				pstmt.setString(2, article_repVO.getArticle_no());
				pstmt.setString(3, article_repVO.getRep_content());

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
		public void update(Article_RepVO article_repVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, article_repVO.getRep_content());
				pstmt.setString(2, article_repVO.getArt_rep_no());
						

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
		public void delete(String art_rep_no) {
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();			
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, art_rep_no);	

				pstmt.executeUpdate();
			
				// Handle any SQL errors
			} catch (SQLException se) {
				if (con != null) {
					try {
						// 3●設定於當有exception發生時之catch區塊內
						con.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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

			Article_RepVO article_repVO = null;			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, art_rep_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// deptVO 也稱為 Domain objects
					article_repVO = new Article_RepVO();
					article_repVO.setArt_rep_no(rs.getString("Art_rep_no"));
					article_repVO.setMem_id(rs.getString("Mem_id"));
					article_repVO.setArticle_no(rs.getString("Article_no"));
					article_repVO.setRep_content(rs.getString("Rep_content"));
					article_repVO.setRep_time(rs.getTimestamp("Rep_time"));
					article_repVO.setCom_status(rs.getString("Com_status"));
				}

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

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					article_repVO = new Article_RepVO();
					article_repVO.setArt_rep_no(rs.getString("Art_rep_no"));
					article_repVO.setMem_id(rs.getString("Mem_id"));
					article_repVO.setArticle_no(rs.getString("Article_no"));
					article_repVO.setRep_content(rs.getString("Rep_content"));
					article_repVO.setRep_time(rs.getTimestamp("Rep_time"));
					article_repVO.setCom_status(rs.getString("Com_status"));
					list.add(article_repVO); // Store the row in the list
				}

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
