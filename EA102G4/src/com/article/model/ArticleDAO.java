package com.article.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.art_comment.model.Art_CommentVO;
import com.article_rep.model.Article_RepVO;





public class ArticleDAO implements ArticleDAO_interface{
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

		private static final String INSERT_STMT = "INSERT INTO ARTICLE (ARTICLE_NO, MEM_ID, TITLE, ART_CONTENT) VALUES ('ART'||LPAD( SEQ_ARTICLE_NO.NEXTVAL,3,'0'), ?, ?, ?)"; //會員文章新增
		private static final String INSERT_WORK_STMT = "INSERT INTO ARTICLE (ARTICLE_NO, WORK_ID, TITLE, ART_CONTENT) VALUES ('ART'||LPAD( SEQ_ARTICLE_NO.NEXTVAL,3,'0'), ?, ?, ?)"; //會員文章新增
		
		private static final String GET_ALL_STMT = "SELECT ARTICLE_NO , MEM_ID, WORK_ID, TITLE, ART_CONTENT, A_RELEASE, LINK_COUNT, COM_COUNT, A_STATUS FROM ARTICLE";    //查詢全部會員文章
		
		private static final String GET_ONE_STMT = "SELECT * FROM ARTICLE WHERE Article_NO = ?";
		
		
		private static final String GET_ART_COMMENTSByARTICLE_NO_STMT = "SELECT COM_NO,MEM_ID,MES_CONTENT,COM_RELEASE,COM_STATUS,ARTICLE_NO FROM ART_COMMENT WHERE ARTICLE_NO =? ORDER BY COM_NO";

		
//		private static final String GET_Artic_ByArticleno_STMT = "SELECT ARTICLE_NO , MEM_ID, WORK_ID, TITLE, ART_CONTENT, A_RELEASE, LINK_COUNT, COM_COUNT, A_STATUS FROM ARTICLE  ORDER BY ARTICLE_NO";		
		
		
		private static final String DELETE = "DELETE FROM ARTICLE WHERE ARTICLE_NO = ?";
		
		private static final String UPDATE = "UPDATE ARTICLE SET  TITLE=?, ART_CONTENT=? WHERE ARTICLE_NO=?  ";

		private static final String UPDATE2 = "UPDATE ARTICLE SET MEM_ID=?, WORK_ID=?, TITLE=?, ART_CONTENT=? WHERE ARTICLE_NO=? ";
		
		private static final String GET_ARTICLE_REPsByARTICLE_NO_STMT = "SELECT ART_REP_NO,MEM_ID,REP_CONTENT,COM_STATUS,ARTICLE_NO FROM ARTICLE_REP WHERE ARTICLE_NO =? ORDER BY ART_REP_NO";


		private static final String DELETE_ART_COMMENTs = "DELETE FROM ART_COMMENT WHERE ARTICLE_NO = ?";
		
		private static final String DELETE_ARTICLE = "DELETE FROM ARTICLE WHERE ARTICLE_NO = ?";	
		//		private static final String article_no = null;

		private static final String GET_REP_ART ="SELECT * "+ " FROM ARTICLE WHERE A_STATUS = 'A0' ORDER BY ARTICLE_NO ";
		
		private static final String UPDATE_TO_NO_SHOW = "UPDATE ARTICLE SET A_STATUS = 'A0' WHERE ARTICLE_NO = ?";
		
		@Override
		public void insert(ArticleVO articleVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setString(1, articleVO.getMem_id());
				pstmt.setString(2, articleVO.getTitle());
				pstmt.setString(3, articleVO.getArt_content());
				
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
		public void insertwork(ArticleVO articleVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_WORK_STMT);
				pstmt.setString(1, articleVO.getWork_id());
				pstmt.setString(2, articleVO.getTitle());
				pstmt.setString(3, articleVO.getArt_content());
				
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
		public void update(ArticleVO articleVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, articleVO.getTitle());
				pstmt.setString(2, articleVO.getArt_content());
				pstmt.setString(3, articleVO.getArticle_no());
				

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
		public void update2(ArticleVO articleVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE2);

				pstmt.setString(1, articleVO.getMem_id());
				pstmt.setString(2, articleVO.getWork_id());
				pstmt.setString(3, articleVO.getTitle());
				pstmt.setString(4, articleVO.getArt_content());
				pstmt.setInt(5, articleVO.getLink_count());
				pstmt.setInt(6, articleVO.getCom_count());
				pstmt.setString(7, articleVO.getA_status());
				pstmt.setString(8, articleVO.getArticle_no());

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
		public void delete(String article_no) {
			int updateCount_ART_COMMENTs = 0;

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();

				// 1●設定於 pstm.executeUpdate()之前
				con.setAutoCommit(false);

				// 先刪除員工
				pstmt = con.prepareStatement(DELETE_ART_COMMENTs);
				pstmt.setString(1, article_no);
				updateCount_ART_COMMENTs = pstmt.executeUpdate();
				// 再刪除部門
				pstmt = con.prepareStatement(DELETE_ARTICLE);
				pstmt.setString(1, article_no);
				pstmt.executeUpdate();

				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				System.out.println("刪除文章編號" + article_no + "時,共有留言會員" + updateCount_ART_COMMENTs
						+ "人同時被刪除");
				
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
		public ArticleVO findByPrimaryKey(String article_no) {

			ArticleVO articleVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, article_no);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain objects
					articleVO = new ArticleVO();
					articleVO.setArticle_no(rs.getString("Article_no"));
					articleVO.setMem_id(rs.getString("Mem_id"));
					articleVO.setWork_id(rs.getString("Work_id"));
					articleVO.setTitle(rs.getString("Title"));
					articleVO.setArt_content(rs.getString("Art_content"));
					articleVO.setA_release(rs.getTimestamp("A_release"));
					articleVO.setLink_count(rs.getInt("Link_count"));
					articleVO.setCom_count(rs.getInt("Com_count"));
					articleVO.setA_status(rs.getString("A_status"));
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
			return articleVO;
		}
		@Override
		public List<ArticleVO> getAll() {
			List<ArticleVO> list = new ArrayList<ArticleVO>();
			ArticleVO articleVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// articleVO 也稱為 Domain
					articleVO = new ArticleVO();
					articleVO.setArticle_no(rs.getString("Article_no"));
					articleVO.setMem_id(rs.getString("Mem_id"));
					articleVO.setMem_id(rs.getString("Work_id"));
					articleVO.setTitle(rs.getString("Title"));
					articleVO.setArt_content(rs.getString("Art_content"));
					articleVO.setA_release(rs.getTimestamp("A_release"));
					articleVO.setLink_count(rs.getInt("Link_count"));
					articleVO.setCom_count(rs.getInt("Com_count"));
					articleVO.setA_status(rs.getString("A_status"));
					list.add(articleVO); // Store the row in the list
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
		
		@Override
		public Set<Art_CommentVO> getArt_CommentsByArticle_no(String article_no) {
			Set<Art_CommentVO> set = new LinkedHashSet<Art_CommentVO>();
			Art_CommentVO art_commentVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ART_COMMENTSByARTICLE_NO_STMT);
				pstmt.setString(1, article_no);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					art_commentVO = new Art_CommentVO();
					art_commentVO.setCom_no(rs.getString("com_no"));
					art_commentVO.setMem_id(rs.getString("mem_id"));
					art_commentVO.setMes_content(rs.getString("mes_content"));
					art_commentVO.setCom_release(rs.getTimestamp("com_release"));
					art_commentVO.setCom_status(rs.getString("com_status"));
					art_commentVO.setArticle_no(rs.getString("article_no"));
					set.add(art_commentVO); // Store the row in the vector
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
			return set;
		
		}
		
		@Override
		public Set<Article_RepVO> getArticle_RepsByArticle_no(String article_no) {
			Set<Article_RepVO> set = new LinkedHashSet<Article_RepVO>();
			Article_RepVO aricle_repVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ARTICLE_REPsByARTICLE_NO_STMT);
				pstmt.setString(1, article_no);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					aricle_repVO = new Article_RepVO();
					aricle_repVO.setArt_rep_no(rs.getString("art_rep_no"));
					aricle_repVO.setMem_id(rs.getString("mem_id"));
					aricle_repVO.setRep_content(rs.getString("rep_content"));
					aricle_repVO.setRep_time(rs.getTimestamp("rep_time"));
					aricle_repVO.setCom_status(rs.getString("com_status"));
					aricle_repVO.setArticle_no(rs.getString("article_no"));
					set.add(aricle_repVO); // Store the row in the vector
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
			return set;
		
		}
		
		@Override
		public List getRepArt() {
			List<ArticleVO> reparticlelist = new ArrayList<ArticleVO>();
			ArticleVO aVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_REP_ART);
				pstmt.setString(1,"article_no");
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					aVO = new ArticleVO();
					aVO.setArticle_no(rs.getString("article_no"));
					reparticlelist.add(aVO);
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
			return reparticlelist;
		
		}
		
		@Override
		public void updateToNoShow(String article_no) {
		
			Connection con = null;
			PreparedStatement pstmt = null;
			
		
			try {
		
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_TO_NO_SHOW);		
				
				pstmt.setString(1, article_no);

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
		
}
	

				
	
