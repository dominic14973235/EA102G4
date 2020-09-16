package com.article.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.art_comment.model.Art_CommentVO;
import com.article_rep.model.Article_RepVO;






public class ArticleJDBCDAO implements ArticleDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "INGYM";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO ARTICLE (ARTICLE_NO, MEM_ID, TITLE, ART_CONTENT) VALUES ('ART'||LPAD( SEQ_ARTICLE_NO.NEXTVAL,3,'0'), ?, ?, ?)"; //會員文章新增
	private static final String INSERT_WORK_STMT = "INSERT INTO ARTICLE (ARTICLE_NO, WORK_ID, TITLE, ART_CONTENT) VALUES ('ART'||LPAD( SEQ_ARTICLE_NO.NEXTVAL,3,'0'), ?, ?, ?)"; //會員文章新增
	
	private static final String GET_ALL_STMT = "SELECT ARTICLE_NO , MEM_ID, WORK_ID, TITLE, ART_CONTENT, A_RELEASE, LINK_COUNT, COM_COUNT, A_STATUS FROM ARTICLE";    //查詢全部會員文章
	
	private static final String GET_ONE_STMT = "SELECT * FROM ARTICLE WHERE Article_NO = ?";
	
	
	private static final String GET_ARTICLE_REPsByARTICLE_NO_STMT = "SELECT ART_REP_NO,MEM_ID,REP_CONTENT,COM_STATUS,ARTICLE_NO FROM ARTICLE_REP WHERE ARTICLE_NO =? ORDER BY ART_REP_NO";

	
	private static final String GET_ART_COMMENTSByARTICLE_NO_STMT = "SELECT COM_NO,MEM_ID,MES_CONTENT,COM_RELEASE,COM_STATUS,ARTICLE_NO FROM ART_COMMENT WHERE ARTICLE_NO =? ORDER BY COM_NO";

	
//		private static final String GET_Artic_ByArticleno_STMT = "SELECT ARTICLE_NO , MEM_ID, WORK_ID, TITLE, ART_CONTENT, A_RELEASE, LINK_COUNT, COM_COUNT, A_STATUS FROM ARTICLE  ORDER BY ARTICLE_NO";		
	
	
	private static final String DELETE = "DELETE FROM ARTICLE WHERE ARTICLE_NO = ?";
	
	private static final String UPDATE = "UPDATE ARTICLE SET  TITLE=?, ART_CONTENT=? WHERE ARTICLE_NO=?  ";

	private static final String UPDATE2 = "UPDATE ARTICLE SET MEM_ID=?, WORK_ID=?, TITLE=?, ART_CONTENT=? WHERE ARTICLE_NO=? ";

	private static final String GET_REP_ART ="SELECT * "+ " FROM ARTICLE WHERE A_STATUS = 'A0' ORDER BY ARTICLE_NO ";
	
	private static final String UPDATE_TO_NO_SHOW = "UPDATE ARTICLE SET A_STATUS = 'A0' WHERE ARTICLE_NO = ?";
	
	@Override
	public void insert(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, articleVO.getMem_id());
			pstmt.setString(2, articleVO.getTitle());
			pstmt.setString(3, articleVO.getArt_content());
			
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
	public void insertwork(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;


		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_WORK_STMT);
			
			pstmt.setString(1, articleVO.getWork_id());
			pstmt.setString(2, articleVO.getTitle());
			pstmt.setString(3, articleVO.getArt_content());
			
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
	public void update(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, articleVO.getTitle());
			pstmt.setString(2, articleVO.getArt_content());
			pstmt.setString(3, articleVO.getArticle_no());
			

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
	public void update2(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String article_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, article_no);

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
	public ArticleVO findByPrimaryKey(String article_no) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, article_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
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
				list.add(articleVO); // Store the row in the list
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


	
	@Override
	public Set<Art_CommentVO> getArt_CommentsByArticle_no(String article_no) {
		Set<Art_CommentVO> set = new LinkedHashSet<Art_CommentVO>();
		Art_CommentVO art_commentVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_REP_ART);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				aVO = new ArticleVO();
				aVO.setArticle_no(rs.getString("article_no"));
				reparticlelist.add(aVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
		return reparticlelist;
	}
		@Override
		public void updateToNoShow(String article_no) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE_TO_NO_SHOW);

				pstmt.setString(1, article_no);
				
				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
			} catch (SQLException se) {
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
	
	
	public static void main(String[] args) {

		ArticleJDBCDAO art = new ArticleJDBCDAO();

		// 新增會員
		ArticleVO articleVO1 = new ArticleVO();
		articleVO1.setMem_id("MEM0000002");
		articleVO1.setTitle("健身的鍛鍊");
		articleVO1.setArt_content("<p><img src=/EA102G4/front-end/article/assets/img/blog/01.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"健身增大肌肉的秘訣：<br />\r\n" + 
				"<br />\r\n" + 
				"大重量、低次數、多組數、長位移、慢速度、高密度、念動一致、頂峰收縮、持續緊張、組間放松、多練大肌群、訓練後進食蛋白質、休息48小時、寧輕勿假。<br />\r\n" + 
				"<br />\r\n" + 
				"1.大重量、低次數：健美理論中用RM表示某個負荷量能連續做的最高重復次數。比如，練習者對一個重量只能連續舉起5次，則該重量就是5RM。研究表明：1-5RM的負荷訓練能使肌肉增粗，發展力量和速度；6-10RM的負荷訓練能使肌肉粗大，力量速度提高，但耐力增長不明顯；10-15RM的負荷訓練肌纖維增粗不明顯，但力量、速度、耐力均有長進；30RM的負荷訓練肌肉內毛細血管增多，耐久力提高，但力量、速度提高不明顯。可見，5-10RM的負荷重量適用於增大肌肉體積的健美訓練。<br />\r\n" + 
				"<br />\r\n" + 
				"2.多組數：什麼時候想起來要鍛煉了，就做上2～3組，這其實是浪費時間，根本不能長肌肉。必須專門抽出60～90分鐘的時間集中鍛煉某個部位，每個動作都做8～10組，才能充分刺激肌肉，同時肌肉需要的恢復時間越長。一直做到肌肉飽和為止，&ldquo;飽和度&rdquo;要自我感受，其適度的標准是：酸、脹、發麻、堅實、飽滿、擴張，以及肌肉外形上的明顯粗壯等。<br />\r\n" + 
				"<br />\r\n" + 
				"3.長位移：不管是劃船、臥推、推舉、彎舉，都要首先把啞鈴放得盡量低，以充分拉伸肌肉，再舉得盡量高。這一條與&ldquo;持續緊張&rdquo;有時會矛盾，解決方法是快速地通過&ldquo;鎖定&rdquo;狀態。不過，我並不否認大重量的半程運動的作用。<br />\r\n" + 
				"<br />\r\n" + 
				"4.慢速度：慢慢地舉起，在慢慢地放下，對肌肉的刺激更深。特別是，在放下啞鈴時，要控制好速度，做退讓性練習，能夠充分刺激肌肉。很多人忽視了退讓性練習，把啞鈴舉起來就算完成了任務，很快地放下，浪費了增大肌肉的大好時機。<br />\r\n" + 
				"<br />\r\n" + 
				"5.高密度：&ldquo;密度&rdquo;指的是兩組之間的休息時間，只休息1分鐘或更少時間稱為高密度。要使肌肉塊迅速增大，就要少休息，頻繁地刺激肌肉。&ldquo;多組數&rdquo;也是建立在&ldquo;高密度&rdquo;的基礎上的。鍛煉時，要像打仗一樣，全神貫注地投入訓練，不去想別的事。<br />\r\n" + 
				"<br />\r\n" + 
				"6.念動一致：肌肉的工作是受神經支配的，注意力密度集中就能動員更多的肌纖維參加工作。練某一動作時，就應有意識地使意念和動作一致起來，即練什麼就想什麼肌肉工作。例如：練立式彎舉，就要低頭用雙眼注視自已的雙臂，看肱二頭肌在慢慢地收縮。<br />\r\n" + 
				"<br />\r\n" + 
				"7.頂峰收縮：這是使肌肉線條練得十分明顯的一項主要法則。它要求當某個動作做到肌肉收縮最緊張的位置時，保持一下這種收縮最緊張的狀態，做靜力性練習，然後慢慢回復到動作的開始位置。我的方法是感覺肌肉最緊張時，數1～6，再放下來。<br />\r\n" + 
				"<br />\r\n" + 
				"8.持續緊張：應在整個一組中保持肌肉持續緊張，不論在動作的開頭還是結尾，都不要讓它松弛（不處於&ldquo;鎖定&rdquo;狀態），總是達到徹底力竭。<br />\r\n" + 
				"<br />\r\n" + 
				"9.組間放松：每做完一組動作都要伸展放松。這樣能增加肌肉的血流量，還有助於排除沉積在肌肉裡的廢物，加快肌肉的恢復，迅速補充營養。<br />\r\n" + 
				"<br />\r\n" + 
				"10.多練大肌群：多練胸、背、腰臀、腿部的大肌群，不僅能使身體強壯，還能夠促進其他部位肌肉的生長。有的人為了把胳膊練粗，只練胳膊而不練其他部位，反而會使二頭肌的生長十分緩慢。建議你安排一些使用大重量的大型復合動作練習，如大重量的深蹲練習，它們能促進所有其他部位肌肉的生長。這一點極其重要，可悲的是至少有90％的人都沒有足夠重視，以致不能達到期望的效果。因此，在訓練計劃裡要多安排硬舉、深蹲、臥推、推舉、引體向上(拉單槓)這5個經典復合動作。<br />\r\n" + 
				"<br />\r\n" + 
				"11.訓練後進食蛋白質：在訓練後的30～90分鐘裡，蛋白質的需求達高峰期，此時補充蛋白質效果最佳。<br />\r\n" + 
				"<br />\r\n" + 
				"12.休息48小時：局部肌肉訓練一次後需要休息48～72小時才能進行第二次訓練。如果進行高強度力量訓練，則局部肌肉兩次訓練的間隔72小時也不夠，尤其是大肌肉塊。不過腹肌例外，腹肌不同於其他肌群，必須經常對其進行刺激，每星期至少要練4次，每次約15分鐘；選三個對你最有效的練習，只做3組，每組20&mdash;25次，均做到力竭；每組間隔時間要短，不能超過1分鐘。<br />\r\n" + 
				"<br />\r\n" + 
				"13.寧輕勿假：這是一個不是秘訣的秘訣。許多初學健美的人特別重視練習重量和動作次數，不太注意動作是否變形。健美訓練的效果不僅僅取決於負重的重量和動作次數，而且還要看所練肌肉是否直接受力和受刺激的程度。如果動作變形或不到位，要練的肌肉沒有或只是部分受力，訓練效果就不大，甚至出偏差。事實上，在所有的法則中，動作的正確性永遠是第一重要的。寧可用正確的動作舉起比較輕的重量，也不要用不標准的動作舉起更重的重量。不要與人攀比，也不要把健身房的嘲笑掛在心上</p>");	
		art.insert(articleVO1);
		System.out.println("新增成功");
		
		
		articleVO1.setMem_id("MEM0000003");
		articleVO1.setTitle("健身飲食");
		articleVO1.setArt_content("<p>大多數人會說只有下班才有空去健身房。下班先回家，等開飯。吃完飯，休息一個小時左右才能鍛鍊。器械加有氧都兩個小時以上，訓練完回家都晚上九&mdash;十點了。所以大部分人訓練後都是喝點蛋白粉、吃點水果什麼的，最多加點增肌粉，就睡覺了。一是沒時間，二是怕長胖不敢多吃。其實訓練後是身體對營養物質需求最旺盛的時候。這個時段身體代謝最快，不容易儲存脂肪，需要補充大量的碳水和蛋白質。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img src=/EA102G4/front-end/article/assets/img/blog/e01.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>蛋白粉和簡單碳水只能滿足訓練後1&mdash;2個小時的需要，但從最後一餐到第二天早上起床後進餐，中間有十幾個小時不能進食。不但糖原儲備得不到充分補充會影響第二天的體能狀態，而且器械訓練破壞的肌肉纖維錯過了營養吸收的最佳時機，長時間得不到充足的營養補充修復也會分解。這樣不但長不了肌肉，長期如此還會營養不良導致越練越瘦或者身體虛弱。</p>\r\n" + 
				"\r\n" + 
				"<p>我的上班族健身飲食心得就是：儘量把訓練時間提前，有條件的最好早上或中午訓練，這樣從訓練後到晚上睡覺前之間能多吃幾頓，充分地補充機體所需要的營養，同時也可以避免睡覺前吃大量碳水而導致脂肪過多的堆積。如果你只有下班後才有時間，那麼建議你訓練前先不要吃正式的晚飯。下班前或下班後吃一點麵條、饅頭、全麥麵包類的複合碳水(不要太多)，就能提供足夠的能量進行大強度器械訓練了。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img src=/EA102G4/front-end/article/assets/img/blog/e02.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>比方說你下午五點補充一點複合碳水，那麼下午五點半或六點就可以到健身房開始器械訓練。器械訓練抓緊時間，高效率地完成訓練任務不拖拉(最多一小時)。訓練後立即吃香蕉等簡單碳水加增肌粉，這個時候應該不超過晚上七點或七點半，或者更早。七點半前回家吃正式晚餐(有肉有飯有蔬菜)十點左右可以再補充一次，以低脂高蛋白食物為主(脂肪含量比較多的人就可以去掉複合碳水)。如果睡覺時間比較晚，比方說晚上十二點或凌晨一點才睡的人，睡前再喝一杯蛋白粉。請注意，雖然訓練後需要大量食物補充，但也要注意是少吃多餐，切忌胡吃海塞。</p>");	
		art.insert(articleVO1);
		System.out.println("新增成功");
		
		articleVO1.setMem_id("MEM0000004");
		articleVO1.setTitle("健身的好處");
		articleVO1.setArt_content("<p><strong><img alt=\"運動，健身，體育，重訓。 \" src=\"https://www.pro360.com.tw/wp-content/uploads/2019/04/iStock-960658902-1.jpg\" width=\"600\" /></strong></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><strong>▲肌肉量決定身體的代謝率，肌肉比起脂肪所消耗的熱量多了5~10倍。</strong></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>隨著運動健身的風潮越來越興盛，也越來越多人得知想要好身材就要「增肌減脂」。市面上的體組成計顯示的「肌肉量」有所不同，你知道你量出來的數值代表什麼嗎？肌肉增加還能為你帶來什麼樣的好處？想增肌不只要認真健身，還有其他重要因素影響肌肉的生長！就讓 Cofit 營養師一一為你解答吧。</p>\r\n" + 
				"\r\n" + 
				"<p><strong>肌肉量是什麼？</strong></p>\r\n" + 
				"\r\n" + 
				"<p>大部分家用簡易體脂計上的肌肉量，是身體肌肉，包含骨骼肌、心肌、平滑肌的總和；肌肉量則是要以「身體總水重＋蛋白質重」來計算。健身族群通常所說的「增肌」所強調的是「骨骼肌」，增肌期間，可以透過定期量測去密切觀察骨骼肌重的增長變化！</p>\r\n" + 
				"\r\n" + 
				"<p><strong>肌肉的重要性</strong></p>\r\n" + 
				"\r\n" + 
				"<p>大家常聽到肌肉量決定了身體代謝率，其實也是跟骨骼肌有關係喔。只有骨骼肌可以透過訓練增加，在體內佔比越高，也會讓代謝率增加。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"健身（圖／取自免費圖庫Pexels） \" src=\"https://pics.ettoday.net/images/4035/d4035628.jpg\" width=\"600\" /></p>\r\n" + 
				"\r\n" + 
				"<p><strong>▲年輕持之以恆增肌，避免罹患肌少症及骨質疏鬆症。</strong></p>\r\n" + 
				"\r\n" + 
				"<p>肌肉消耗的能量是脂肪的5~10倍，所佔的體積卻只有脂肪的1/4，是讓你外表看起來精實、體內代謝良好的關鍵之一。至於肌肉的其他好處包含保護關節，在進行活動時的衝擊可以減少傷害；而隨著年齡的增長，若是你沒有維持正確的運動習慣，肌肉量會一直流失、代謝率也會逐漸下降，若是成為肌少症患者，更是會造成生活上的不便與障礙！</p>\r\n" + 
				"\r\n" + 
				"<p>以下是透過肌力訓練讓肌肉量增加的好處：</p>\r\n" + 
				"\r\n" + 
				"<p><strong>1.增加基礎代謝率（BMR)，讓身體不易堆積脂肪。</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>2.提升運動能力，避免運動傷害。</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>3.增進骨質密度，使骨骼強壯、延緩老化。</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>4.使生活減少因久坐或久站造成的肌肉疲勞。</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>5.降低心血管等慢性疾病的風險。</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>6.避免骨質疏鬆或罹患肌少症。</strong></p>\r\n" + 
				"\r\n" + 
				"<p><strong>7.身形線條更好看。</strong><br />\r\n" + 
				"&nbsp;</p>");	
		art.insert(articleVO1);
		System.out.println("新增成功");
		
		articleVO1.setMem_id("MEM0000005");
		articleVO1.setTitle("健身前的飲食習慣");
		articleVO1.setArt_content("<p>在說到&ldquo;營養&ldquo;這個話題的時候，我們大多數人都知道運動後需要補充能量，<strong>但你知道健身前應該怎麼吃嗎？去健身房多久前該進食、吃什麼、吃多少</strong>，這些都會影響你在鍛鍊時的表現和身體的感覺。介紹給大家一些運動前飲食的基礎知識，幫助你更高效地運動。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2>去健身房之前應該吃什麼？</h2>\r\n" + 
				"\r\n" + 
				"<p>雖然有些人喜歡空腹鍛鍊，但在去健身房之前，飲食中攝取足夠量的高量營養素（歐美健身屆常說的&ldquo;MACROS&rdquo;）至關重要。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"健身前吃什麼 健身前怎麼吃\" src=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/700x385-04-1_1585231139.jpg\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3><strong>碳水化合物</strong></h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p><a href=\"https://www.myprotein.tw/nutrition/carbohydrates.list\"><strong>碳水化合物</strong></a>是肌肉的主要能量來源。在鍛鍊期間，我們的肌肉從食物中的碳水化合物中提取能量（葡萄糖）以立即使用，或者從肌肉中額外的能量存儲（糖原）中獲取能量。全穀物，水果和蔬菜中，我們都可以攝取碳水化合物，而碳水既可以為我們全天活動（包括大腦）的所有肌肉提供能量，也能夠幫助身體最大程度地增加糖原的儲存，因此，運動前攝取適量碳水，我們可以更努力更長久地運動，並且碳水幫助補充糖原，有助於加快運動後的肌肉恢復。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3><strong>蛋白質</strong></h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p>大多數運動員都知道<strong>蛋白質</strong>是鍛鍊後增長肌肉的關鍵，但健身前攝取蛋白質也有好處。研究表明，在健身前先消耗蛋白質，有助於鍛鍊後肌肉的增長和恢復。在健身前先消耗蛋白質，這將使你的身體有時間消化蛋白質並將其分解為氨基酸，從而有助於肌肉的建立和修復。</p>\r\n" + 
				"\r\n" + 
				"<p>另一項研究表明，不論是在健身前或是健身後，補充相同劑量的蛋白質都具有一樣的益處。定期攝取足夠的蛋白質也可以幫助你從上一次鍛鍊中恢復，使身體做好準備, 以達到最佳狀態再次進行鍛鍊。</p>\r\n" + 
				"\r\n" + 
				"<p>Myprotein 提供一系列優質<a href=\"https://www.myprotein.tw/nutrition/protein.list\"><strong>蛋白粉</strong></a>選擇，幫助您在健身前方便快捷高效地補充蛋白質。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"蛋白粉 蛋白質\" sizes=\"(max-width: 1024px) 100vw, 1024px\" src=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345.jpg\" srcset=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345.jpg 1024w, https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345-300x168.jpg 300w, https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345-768x430.jpg 768w, https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Post-Workout-Shake-1800x1200-min_1200x672_acf_cropped_1200x672_acf_cropped_1200x672_acf_cropped-1024x573_1585231345-700x392.jpg 700w\" width=\"1024\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3><strong>脂肪</strong></h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p>不知大家有沒有發現，健身時，機器上可能會顯示 &ldquo;正在燃燒的卡路里&rdquo;？你可以把這些運動視為強度較低、時間較長的運動，例如慢跑 20分鐘。在運動中，當我們的肌肉有足夠的氧氣去使用脂肪，脂肪就被消耗燃燒以提供能量。並且有益心臟的脂肪在身體健康中扮演重要的角色，因此規律攝取健康的脂肪非常很重要。</p>\r\n" + 
				"\r\n" + 
				"<p><a href=\"https://www.myprotein.tw/nutrition/fibre-essential-fats.list\"><strong>健康脂肪&nbsp;</strong></a>的另一個好處是飽腹感 &ndash; 它有助於減慢消化速度，並給人帶來更長久的滿足感，這可以防止暴飲暴食。一種常見的健康脂肪就是MCT 油，它是易於消化的脂肪補充品。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3><strong>健身補充品</strong></h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p><strong>健身前攝取補充品&nbsp;</strong>可以更好地幫助你達到訓練目標。如果你想在舉重等訓練中提高力量和表現，那麼肌酸這一健身補劑被證明是有效且安全的。</p>\r\n" + 
				"\r\n" + 
				"<p>如果你想在運動前半小時補充一點能量，那麼含有咖啡因和維生素B的健身粉末是很好的選擇。這些鍛鍊前補充劑可以延緩疲勞感並提高運動時的表現，帶來更好的健身效果。</p>\r\n" + 
				"\r\n" + 
				"<p>無論在鍛鍊前服用什麼補充劑，都要確保按照產品說明進行操作，例如是隨餐還是空腹服用，喝水或其他飲料搭配等，以獲得理想的效果。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\" 健身前怎麼吃\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Blog-Drinkingwater-Male-700x385_1585231431.jpg\" srcset=\"https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Blog-Drinkingwater-Male-700x385_1585231431.jpg 700w, https://blogscdn.thehut.net/app/uploads/sites/510/2019/10/Blog-Drinkingwater-Male-700x385_1585231431-300x165.jpg 300w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>健身前多久吃東西？</h3>\r\n" + 
				"\r\n" + 
				"<hr />\r\n" + 
				"<p>綜合以上需要考慮的種種因素，去健身之前，我們該間隔多久進食呢？</p>\r\n" + 
				"\r\n" + 
				"<p><strong>建議在鍛鍊前2-3小時</strong>，並確保這一餐包含有三個 Macros 高量營養素（碳水化合物，充足的蛋白質和健康的脂肪）。</p>");	
		art.insert(articleVO1);
		System.out.println("新增成功");
		
		articleVO1.setMem_id("MEM0000006");
		articleVO1.setTitle("雞胸肉菜單");
		articleVO1.setArt_content("<p><strong>雞胸肉</strong>是減肥、養肌者最受歡迎的食材之一，低脂低熱量、富含蛋白質，又不需擔心發胖。但，雞胸肉烹煮後、很容易柴掉，教你如何做出厲害、多汁的美味雞胸肉料理，天天都好吃好瘦。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>雞胸肉受歡迎的主要原因，比起其他部位雞腿、雞翅都來的低脂，擁有高蛋白質量的特性，已成為增肌<a href=\"https://blog.worldgymtaiwan.com/diet-how-to-avoid-fat-storage\" target=\"_blank\">減脂</a>的好幫手。但也因為低脂，烹調時一不小心，雞胸肉就會變柴變澀。<br />\r\n" + 
				"&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>因此，想要雞肉口感好，「雞胸肉鎖汁」方法，一定要銘記在心，讓你煮一次、就上手。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2><strong>雞胸肉鎖汁5撇步</strong></h2>\r\n" + 
				"\r\n" + 
				"<p>雞胸肉好吃的5撇步，首先第1項到第4項，選擇一種方法可以！</p>\r\n" + 
				"\r\n" + 
				"<h3><br />\r\n" + 
				"1.冷凍的雞肉，慢慢退冰再烹煮。</h3>\r\n" + 
				"\r\n" + 
				"<p>雞胸肉不要在冷凍狀態的時候立即放入鍋子煎煮，或是用微波爐解凍。<br />\r\n" + 
				"正確做法，前一天從冷凍放入冷藏，要吃30分鐘前再拿出來回到室溫。這樣再烹煮的時候，肉質不會收縮過快，讓水分跑光光。</p>\r\n" + 
				"\r\n" + 
				"<h3><br />\r\n" + 
				"2.泡鹽水</h3>\r\n" + 
				"\r\n" + 
				"<p>水和鹽巴的比例=100毫升水：5.5公克鹽巴，鹽水的滲透壓作用，讓肉吃進水分，提高10%含水量，所以會比較嫩。這是我自己最愛用的方法，至少醃一個晚上，效果最好。<br />\r\n" + 
				"<br />\r\n" + 
				"小編是香料控，喜歡另外加入大量的香料(用滷味袋子裝)，像是乾燥百里香、辣椒乾、五角乾、乾燥肉桂捲...等，連同雞肉一起泡在鹽水，隔天拿出來烹煮，會很香喔！</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>3.沾蛋白</h3>\r\n" + 
				"\r\n" + 
				"<p>蛋白可以形成保護層，防止肌肉水分流失。<br />\r\n" + 
				"這方法特別適合將雞切小塊後，和蛋液、調味料一起拌勻，下鍋煎。再加入蔬菜炒一炒，超好吃。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>4.加入優格來醃製</h3>\r\n" + 
				"\r\n" + 
				"<p>「酸」能使肉中的蛋白質產生變化，讓肉質變嫩。<br />\r\n" + 
				"這也是常用的方法，前一天將冷藏的雞肉、優格、香料醃在一起，要吃的時候，旁邊放蔬菜，一起放入烤箱悶烤，鮮嫩又多汁(流口水)！</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>5.用「悶」的方法烹煮</h3>\r\n" + 
				"\r\n" + 
				"<p>記住一個原則，雞胸肉一定要用悶的讓它全熟；無論你用電鍋、用煎鍋、用水煮或是烤箱，要善用餘溫悶煮的好處！<br />\r\n" + 
				"<br />\r\n" + 
				"【電鍋法】一片雞胸肉加半杯水，電鍋跳起來後，悶10分鐘<br />\r\n" + 
				"【悶煎法】鍋內放油，中火加熱，雞胸肉雙面各煎3分鐘成金黃色，轉文火蓋上鍋蓋，悶煎7分鐘就可以囉！<br />\r\n" + 
				"【水煮法】水滾之後，放入雞胸肉，水一滾立即關火，悶10分鐘。<br />\r\n" + 
				"【悶烤法】烤箱預熱170度c，放入雞胸肉烤5分鐘，烤箱關火，用餘溫悶烤10分鐘。</p>\r\n" + 
				"\r\n" + 
				"<p><br />\r\n" + 
				"前置作業一定要做(如果忘記做，至少雞肉+鹽巴醃5分鐘)，而第5項是烹調技巧，學會了美味輕鬆上菜！</p>\r\n" + 
				"\r\n" + 
				"<p><br />\r\n" + 
				"重頭戲來了，減重、增肌的人趕快筆記。</p>\r\n" + 
				"\r\n" + 
				"<p><br />\r\n" + 
				"對健康飲食、有偏執傾向，加上時常健身，需要營養的食物，尤其是蛋白質，所以幾乎都是每天自己帶便當；針對「雞胸肉」這道主食的變化，接下來會介紹5道「平日便當餐」以及分享2道「雞胸肉早午餐」，讓你的增肌減脂餐，不怕吃膩。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>這幾道菜單設計，多以「乾式香料」做調味，能增添食物原味、也不怕增加多餘熱量；另外，會善用另一個超級食物─「藜麥或傳統燕麥」，讓你增加口感和飽足感。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2><strong>雞胸肉7日料理菜單</strong></h2>\r\n" + 
				"\r\n" + 
				"<h3>菜單1【水煮香料雞胸肉佐燕麥】</h3>\r\n" + 
				"\r\n" + 
				"<p>《食材》雞胸肉1片、花椰菜1杯、傳統<a href=\"https://blog.worldgymtaiwan.com/%E7%87%95%E9%BA%A5%E6%80%8E%E9%BA%BC%E5%90%83%E6%89%8D%E4%B8%8D%E6%9C%83%E8%83%96\" target=\"_blank\">燕麥</a>乾的40g、調味料(墨西哥香料、辣椒片、胡椒、鹽)<br />\r\n" + 
				"<br />\r\n" + 
				"《前置作業》前一天，雞胸肉泡鹽水<br />\r\n" + 
				"<br />\r\n" + 
				"《作法》<br />\r\n" + 
				"Step1.水煮法烹煮雞胸肉和花椰菜。傳統燕麥+水1杯，電鍋蒸15分鐘。<br />\r\n" + 
				"Step2.食材都熟了，放進便當盒，撒上調味料，就可以帶出門了。<br />\r\n" + 
				"<br />\r\n" + 
				"Tip：這個便當可以吃「常溫」，不需要再加熱，很方便。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"水煮香料雞胸肉佐藜麥\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=700&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=350&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=700&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1050&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1400&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1750&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=2100&amp;name=%E6%B0%B4%E7%85%AE%E9%A6%99%E6%96%99%E9%9B%9E%E8%83%B8%E8%82%89%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>菜單2【橙汁起司烤雞胸】</h3>\r\n" + 
				"\r\n" + 
				"<p>《食材》雞胸肉1片、<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E8%98%86%E7%AD%8D7%E5%A4%A7%E5%A5%BD%E8%99%95\" target=\"_blank\">蘆筍</a>、柳橙1顆切片、帕瑪森乾酪、調味料(紅椒粉、胡椒、鹽)<br />\r\n" + 
				"<br />\r\n" + 
				"《前置作業》前一天，雞胸肉泡鹽水<br />\r\n" + 
				"<br />\r\n" + 
				"《作法》<br />\r\n" + 
				"Step1.雞胸肉均勻裹上調味料(紅椒粉可以放多一點)。烤盤由下而上堆疊柳橙片&gt;雞胸肉&gt;一片柳橙，淋一點油，蘆筍放旁邊。<br />\r\n" + 
				"Step2. 用悶烤法，烤完再撒上<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/10%E7%A8%AE%E5%81%A5%E5%BA%B7%E8%B5%B7%E5%8F%B8\" target=\"_blank\">帕馬森起司</a>。<br />\r\n" + 
				"<br />\r\n" + 
				"Tip：這個便當需要「加熱再吃(強火微波120秒)」，帕瑪森會加熱時融化。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"橙汁起司烤雞胸\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=700&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=350&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=700&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=1050&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=1400&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=1750&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg?width=2100&amp;name=%E6%A9%99%E6%B1%81%E8%B5%B7%E5%8F%B8%E7%83%A4%E9%9B%9E%E8%83%B8.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>菜單3【鮮菇藜麥烤雞胸肉】</h3>\r\n" + 
				"\r\n" + 
				"<p>《食材》雞胸肉1片、蘆筍、鴻喜菇(換成喜歡的菇類都可以，例如：<a href=\"https://blog.worldgymtaiwan.com/healthy-food-pleurotus-eryngii-meal\" target=\"_blank\">杏鮑菇</a>)、<a href=\"https://blog.worldgymtaiwan.com/%E8%97%9C%E9%BA%A5%E9%A3%9F%E8%AD%9C\" target=\"_blank\">藜麥</a>40g、調味料(番茄醬、紅椒粉、胡椒、鹽)<br />\r\n" + 
				"<br />\r\n" + 
				"《前置作業》前一天，雞胸肉泡鹽水<br />\r\n" + 
				"<br />\r\n" + 
				"《作法》<br />\r\n" + 
				"Step1.藜麥+1/2杯水，電鍋蒸15分鐘。雞胸肉均勻裹上調味料(番茄醬少一點，紅椒粉可以放多一點)。烤盤內擺放雞胸肉，淋上一點油，蘆筍放旁邊。<br />\r\n" + 
				"Step2. 用悶烤法，雞肉烤熟，會有湯汁，可以用來炒菇。然後就可以裝進便當囉！<br />\r\n" + 
				"<br />\r\n" + 
				"《Tip》這個便當可以「吃常溫」，加熱吃(強火微波120秒)也不錯。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"鮮菇藜麥烤雞胸肉\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=700&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=350&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=700&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=1050&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=1400&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=1750&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg?width=2100&amp;name=%E9%AE%AE%E8%8F%87%E8%97%9C%E9%BA%A5%E7%83%A4%E9%9B%9E%E8%83%B8%E8%82%89.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>菜單4【香煎優格雞胸】</h3>\r\n" + 
				"\r\n" + 
				"<p>《食材》雞胸肉1片、蘆筍、紅蘿蔔(切條)、傳統燕麥40g、調味料(<a href=\"https://blog.worldgymtaiwan.com/%E5%B8%8C%E8%87%98%E5%84%AA%E6%A0%BC%E6%B8%9B%E8%82%A5%E9%A3%9F%E8%AD%9C\" target=\"_blank\">優格</a>、紅椒粉、胡椒、鹽)<br />\r\n" + 
				"<br />\r\n" + 
				"《前置作業》前一天，雞肉均勻裹上調味料，優格可以多一點，放冰箱醃製。<br />\r\n" + 
				"<br />\r\n" + 
				"《作法》<br />\r\n" + 
				"Step1.傳統燕麥+水1杯，電鍋蒸15分鐘。雞胸肉用悶煎法，醃醬全部放進去鍋內煎。<br />\r\n" + 
				"Step2.蘆筍+紅蘿蔔，淋上一些油，放進烤箱10分鐘(先預熱170度c)，所有食材熟了，就能裝盤。<br />\r\n" + 
				"<br />\r\n" + 
				"《Tip》這個便當可以「吃常溫」，或加熱吃(強火微波120秒)。<br />\r\n" + 
				"<img alt=\"香煎優格雞胸\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=700&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=350&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=700&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=1050&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=1400&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=1750&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg?width=2100&amp;name=%E9%A6%99%E7%85%8E%E5%84%AA%E6%A0%BC%E9%9B%9E%E8%83%B8.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>菜單5【菇菇雞佐藜麥】</h3>\r\n" + 
				"\r\n" + 
				"<p>《食材》雞胸肉1片、蘑菇切片、藜麥40g、調味料(紅椒粉、胡椒、鹽)<br />\r\n" + 
				"<br />\r\n" + 
				"《前置作業》前一天，雞胸肉泡鹽水<br />\r\n" + 
				"<br />\r\n" + 
				"《作法》<br />\r\n" + 
				"Step1.藜麥+1/2杯水，電鍋蒸15分鐘。雞胸肉裹上調味料，用悶煎法。<br />\r\n" + 
				"Step2.煎雞胸肉的醬汁，再繼續炒洋菇。熟的食材裝盤就可以囉！<br />\r\n" + 
				"<br />\r\n" + 
				"《Tip》這個便當可以「吃常溫」，或加熱吃(強火微波120秒)。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"菇菇雞佐藜麥\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=700&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=350&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=700&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1050&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1400&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=1750&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg?width=2100&amp;name=%E8%8F%87%E8%8F%87%E9%9B%9E%E4%BD%90%E8%97%9C%E9%BA%A5.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>菜單6【嫩雞瑪格麗特薄餅】</h3>\r\n" + 
				"\r\n" + 
				"<p>《食材》墨西哥餅餅皮、雞胸肉、番茄糊、起司、油漬mozzarella起司(可用披薩起司、橄欖油）、生菜。<br />\r\n" + 
				"<br />\r\n" + 
				"《作法》<br />\r\n" + 
				"Step1. 烤箱170度c預熱。雞胸肉切小塊加鹽拌勻，備用。<br />\r\n" + 
				"Step2. 鍋子內放一點油，中火加熱，雞肉下鍋煎3分鐘，加入番茄糊，拌炒至雞肉全熟起鍋，備用。<br />\r\n" + 
				"Step3. 餅皮上擺放食材：起司絲、番茄雞肉、mozzarella起司，另加一點洋蔥粉調味，放入烤箱5分鐘，出爐後可以淋一些橄欖油和生菜再上桌。<br />\r\n" + 
				"<br />\r\n" + 
				"《Tip》餅皮最外圍預留2公分不要沾到料，烤後可以保持酥脆。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"嫩雞瑪格麗特薄餅\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=700&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=350&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=700&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=1050&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=1400&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=1750&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg?width=2100&amp;name=%E5%AB%A9%E9%9B%9E%E7%91%AA%E6%A0%BC%E9%BA%97%E7%89%B9%E8%96%84%E9%A4%85.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>菜單7【酥脆吐司雞肉捲】</h3>\r\n" + 
				"\r\n" + 
				"<p>《食材》雞胸肉1片、蘑菇、吐司、羊奶乳酪、瑪茲瑞拉起司片、洋蔥粉、帕瑪森起司、義式蔬菜醬(或番茄醬)。<br />\r\n" + 
				"<br />\r\n" + 
				"《作法》<br />\r\n" + 
				"Step1. 雞肉和蘑菇切小塊，分別炒熟，備用。<br />\r\n" + 
				"Step2.吐司去邊，桿平。製作吐司捲，由下而上：吐司＞瑪茲瑞拉起司片＞蔬菜醬＞雞肉＞蘑菇＞羊奶乳酪＞洋蔥粉，將吐司慢慢的捲入所有內餡，開口朝下，備用。<br />\r\n" + 
				"Step3.平底鍋中火加熱，放入帕瑪森起司，吐司捲開口朝下，放在帕瑪森起司上，帕瑪森起司煎至金黃色再翻面煎吐司，煎4個面就完成囉！<br />\r\n" + 
				"<br />\r\n" + 
				"《Tip》<br />\r\n" + 
				"吐司要用大片一點的，任何你喜歡的口味都可以。<br />\r\n" + 
				"帕瑪森起司主要是『封口』作用，想減少熱量，不加也ok。<br />\r\n" + 
				"<img alt=\"酥脆吐司雞肉捲\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=700&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=350&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=700&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=1050&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=1400&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=1750&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg?width=2100&amp;name=%E9%85%A5%E8%84%86%E5%90%90%E5%8F%B8%E9%9B%9E%E8%82%89%E6%8D%B2.jpg 2100w\" width=\"700\" /></p>");	
		art.insert(articleVO1);
		System.out.println("新增成功");
		
		articleVO1.setMem_id("MEM0000007");
		articleVO1.setTitle("健身飲食和健身");
		articleVO1.setArt_content("<p>大多數人會說只有下班才有空去健身房。下班先回家，等開飯。吃完飯，休息一個小時左右才能鍛鍊。器械加有氧都兩個小時以上，訓練完回家都晚上九&mdash;十點了。所以大部分人訓練後都是喝點蛋白粉、吃點水果什麼的，最多加點增肌粉，就睡覺了。一是沒時間，二是怕長胖不敢多吃。其實訓練後是身體對營養物質需求最旺盛的時候。這個時段身體代謝最快，不容易儲存脂肪，需要補充大量的碳水和蛋白質。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img src=/EA102G4/front-end/article/assets/img/blog/G1.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>蛋白粉和簡單碳水只能滿足訓練後1&mdash;2個小時的需要，但從最後一餐到第二天早上起床後進餐，中間有十幾個小時不能進食。不但糖原儲備得不到充分補充會影響第二天的體能狀態，而且器械訓練破壞的肌肉纖維錯過了營養吸收的最佳時機，長時間得不到充足的營養補充修復也會分解。這樣不但長不了肌肉，長期如此還會營養不良導致越練越瘦或者身體虛弱。</p>\r\n" + 
				"\r\n" + 
				"<p>我的上班族健身飲食心得就是：儘量把訓練時間提前，有條件的最好早上或中午訓練，這樣從訓練後到晚上睡覺前之間能多吃幾頓，充分地補充機體所需要的營養，同時也可以避免睡覺前吃大量碳水而導致脂肪過多的堆積。如果你只有下班後才有時間，那麼建議你訓練前先不要吃正式的晚飯。下班前或下班後吃一點麵條、饅頭、全麥麵包類的複合碳水(不要太多)，就能提供足夠的能量進行大強度器械訓練了。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><img src=/EA102G4/front-end/article/assets/img/blog/e01.jpg alt=\"\">" + 
				"<br />\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>比方說你下午五點補充一點複合碳水，那麼下午五點半或六點就可以到健身房開始器械訓練。器械訓練抓緊時間，高效率地完成訓練任務不拖拉(最多一小時)。訓練後立即吃香蕉等簡單碳水加增肌粉，這個時候應該不超過晚上七點或七點半，或者更早。七點半前回家吃正式晚餐(有肉有飯有蔬菜)十點左右可以再補充一次，以低脂高蛋白食物為主(脂肪含量比較多的人就可以去掉複合碳水)。如果睡覺時間比較晚，比方說晚上十二點或凌晨一點才睡的人，睡前再喝一杯蛋白粉。請注意，雖然訓練後需要大量食物補充，但也要注意是少吃多餐，切忌胡吃海塞。</p>");	
		art.insert(articleVO1);
		System.out.println("新增成功");
		
		articleVO1.setMem_id("MEM0000008");
		articleVO1.setTitle("減脂菜單");
		articleVO1.setArt_content("<p>先給你一個觀念，就是「減脂&ne;減重」，減重很簡單，一天少吃2餐就減少體重了，但保證復胖極快！因為減重初期都是減去體內的水分，甚至營養不足還還<a href=\"https://blog.worldgymtaiwan.com/disease-prevention-six-ways-causes-muscle-loss\" target=\"_blank\">減去肌肉(沒有肌肉胖更快啊！</a>??)，你以為瘦了，其實沒有！</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"看過來！ 專屬外食族「減脂菜單」秘訣大公開【2020更新】\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%914.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>那麼為何現在減脂菜單這麼流行？想想看，上班經常久坐、不運動、飲食內容不對&hellip;，這些壞習慣，導致你<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/20%E7%A8%AE%E5%BC%B7%E5%8A%9B%E6%8E%92%E6%AF%92%E9%A3%9F%E7%89%A9\" target=\"_blank\">吃進一堆毒素</a>和增加脂肪，脂肪是包含看的到的皮下脂肪和看不到的內臟脂肪。特別提醒，<a href=\"https://blog.worldgymtaiwan.com/tag/%E5%81%A5%E5%BA%B7/%E7%BE%8E%E9%AB%94/4%E6%8B%9B%E6%B6%88%E9%99%A4%E5%85%A7%E8%87%9F%E8%84%82%E8%82%AA\" target=\"_blank\">內臟脂肪會危害到健康</a>，很多人卻渾然不知。</p>\r\n" + 
				"\r\n" + 
				"<p>外食族真的很難吃到完全的減脂餐，所以為大家整理實用的減脂菜單，三餐各3種餐點隨你搭配。請務必記得，飲食控制不是餓肚子，而是吃身體要的營養，器官才能利用消耗掉熱量喔！</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2><strong>「減脂菜單」讓中午放飯時間 成為外食族的期待</strong></h2>\r\n" + 
				"\r\n" + 
				"<h3>1.早餐店類(當午餐也可以喔！)</h3>\r\n" + 
				"\r\n" + 
				"<p>??盡量都不要「塗醬」、飲品以無糖為主、不選醃製過肉類。</p>\r\n" + 
				"\r\n" + 
				"<ul>\r\n" + 
				"	<li><a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E8%B1%86%E6%BC%BF%E6%B8%9B%E8%82%A5\" target=\"_blank\">豆漿</a>+蔬食捲餅+荷包蛋1~2顆</li>\r\n" + 
				"	<li>黑咖啡+肉蛋吐司(不塗醬)+蔬菜沙拉(不加美乃滋)或水果一份。</li>\r\n" + 
				"	<li>薏仁漿(或其他穀類漿)+鮪魚蛋餅+蔬菜沙拉(不加美乃滋)或水果一份。</li>\r\n" + 
				"</ul>\r\n" + 
				"\r\n" + 
				"<p>黑咖啡+里肌蛋蔬菜三明治。因為白吐司屬於精緻的澱粉，少吃一點比較好，所以只吃一片。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"減脂早餐-1\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=700&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=350&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=700&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=1050&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=1400&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=1750&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg?width=2100&amp;name=%E6%B8%9B%E8%84%82%E6%97%A9%E9%A4%90-1.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>2.便利商店類</h3>\r\n" + 
				"\r\n" + 
				"<ul>\r\n" + 
				"	<li>燕麥飲+<a href=\"https://blog.worldgymtaiwan.com/%E7%A9%BA%E8%85%B9%E5%90%83%E9%A6%99%E8%95%89-%E5%BC%8A%E5%A4%9A%E6%96%BC%E5%88%A9\" target=\"_blank\">香蕉</a>一根+鮮蔬烤雞三明治</li>\r\n" + 
				"	<li>無糖<a href=\"https://blog.worldgymtaiwan.com/%E5%84%AA%E6%A0%BC-%E5%84%AA%E9%85%AA%E4%B9%B3-%E5%B8%8C%E8%87%98%E5%84%AA%E6%A0%BC%E5%B7%AE%E5%88%A5%E5%9C%A8%E5%93%AA%E8%A3%A1\" target=\"_blank\">優酪乳</a>+綜合水果盒+鮮蔬烤雞捲餅</li>\r\n" + 
				"	<li>溫泉蛋一包(2份)+<a href=\"https://blog.worldgymtaiwan.com/%E5%9C%B0%E7%93%9C%E6%B8%9B%E8%82%A5%E6%96%B9%E6%B3%95\" target=\"_blank\">地瓜</a>+蔬菜沙拉</li>\r\n" + 
				"</ul>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>這是常吃的組合，給大家參考</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"便利商店減脂餐\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=700&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=350&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=700&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=1050&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=1400&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=1750&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg?width=2100&amp;name=%E4%BE%BF%E5%88%A9%E5%95%86%E5%BA%97%E6%B8%9B%E8%84%82%E9%A4%90.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>3.自助餐便當類</h3>\r\n" + 
				"\r\n" + 
				"<ul>\r\n" + 
				"	<li><a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E9%AB%98%E9%BA%97%E8%8F%9C1/6%E6%B8%9B%E8%82%A5%E5%AD%B8\" target=\"_blank\">高麗菜</a>+空心菜+炒菇類+蔥花蛋+清蒸鱈魚+糙米飯</li>\r\n" + 
				"	<li>小黃瓜+青椒甜椒+水蓮+番茄炒蛋+蒜泥白肉+<a href=\"https://blog.worldgymtaiwan.com/%E7%B4%AB%E7%B1%B3%E9%BB%91%E7%B1%B3%E5%AF%8C%E5%90%AB%E8%8A%B1%E9%9D%92%E7%B4%A0\" target=\"_blank\">紫米</a>飯</li>\r\n" + 
				"	<li><a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E8%8A%B1%E6%A4%B0%E8%8F%9C%E6%B8%9B%E9%87%8D\" target=\"_blank\">花椰菜</a>+<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E7%8E%89%E7%B1%B3%E7%AD%8D%E4%BD%8E%E7%86%B1%E9%87%8F%E5%8F%88%E5%8A%A9%E7%98%A6\" target=\"_blank\">玉米筍</a>紅蘿蔔+海帶<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E9%BB%91%E6%9C%A8%E8%80%B3%E9%80%99%E6%A8%A3%E5%90%83%E4%B8%8D%E6%98%AF%E6%AF%92\" target=\"_blank\">木耳</a>+蒸蛋+滷雞腿+地瓜飯</li>\r\n" + 
				"</ul>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p><a href=\"https://blog.worldgymtaiwan.com/%E8%87%AA%E5%8A%A9%E9%A4%90%E6%B8%9B%E8%82%A5%E6%B3%95\" target=\"_blank\">自助餐夾菜原則</a></p>\r\n" + 
				"\r\n" + 
				"<ul>\r\n" + 
				"	<li>配菜類盡量夾上面的，以及避免炒茄子、乾煸四季豆，或是醬料太多的菜色，這類菜色熱量較高，且吸附大量油脂。</li>\r\n" + 
				"	<li>另外，素料或加工食品，能不碰則不碰，因為加工食品的添加物含量高、熱量也高，例如：素肉、百頁豆腐、香腸。</li>\r\n" + 
				"</ul>\r\n" + 
				"\r\n" + 
				"<p>主菜，盡量別挑選油炸類，如果別無選擇，那麼可以選擇好去皮的炸雞腿，否則吃下裹粉炸過的外皮，攝取的油脂、熱量超高！<br />\r\n" + 
				"<br />\r\n" + 
				"推薦很方便的Uber eats 和Food Ponda外送服務，健康餐用外送超方便，甚至還可以預先訂餐。能夠生活在21世紀，真是太棒了！</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"健康餐-1\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=700&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=350&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=700&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=1050&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=1400&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=1750&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg?width=2100&amp;name=%E5%81%A5%E5%BA%B7%E9%A4%90-1.jpg 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h2><strong>快速料理不挨餓 自己煮「一周減脂餐」最健康！</strong></h2>\r\n" + 
				"\r\n" + 
				"<p>上班中午休息時間，不一定要天天吃外食，你也可以偶爾自備便當，帶到公司加熱，或是放在保溫袋裡，不僅自己帶最健康，還能省錢呢！</p>\r\n" + 
				"\r\n" + 
				"<h3>1.輕食便當</h3>\r\n" + 
				"\r\n" + 
				"<p>懶得煮、不想開伙，那麼就來做三明治或生菜沙拉當午餐吧！雞肉沙拉、鮪魚生菜全麥三明治，或是用個電鍋蒸蛋也不錯，很快速就能解決。不過別忘了，隱形殺手醬料類不要加太多，像是美乃滋的熱量超高啊！加太多，減肥計畫直接破功。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"懶得煮、不想開伙，那麼就來做三明治或生菜沙拉當午餐吧！\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%911.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>另外，蒸蛋配全麥三明治、沙拉，還可以選擇帶水果，多多補充維生素，很適合不愛吃蔬菜的人。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>2.義大利麵</h3>\r\n" + 
				"\r\n" + 
				"<p>誰說吃<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E7%BE%A9%E5%A4%A7%E5%88%A9%E9%BA%B5%E8%B6%8A%E5%90%83%E8%B6%8A%E7%98%A6\" target=\"_blank\">義大利麵</a>很胖？選對醬料就不胖！除了蔬菜與蛋白質的搭配，醬料可以選擇清炒或番茄(紅醬)口味，熱量比白醬、青醬、橘醬低很多，因為這些醬料含有大量的奶油，是減肥期的大忌！此外，義大利麵也別加大量的起司焗烤，這只會讓總熱量又提升一大層。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"減脂菜單-義大利麵\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%912.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>而義大利麵在配菜方面，可以水煮花椰菜、玉米筍，或是選擇帶蔬菜沙拉，然後配上嫩煎<a href=\"https://blog.worldgymtaiwan.com/%E9%9B%9E%E8%83%B8%E8%82%89%E8%8F%9C%E5%96%AE\" target=\"_blank\">雞胸肉</a>、里肌肉，就會是一餐幸福滿分的減脂餐了！</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>3.海鮮類</h3>\r\n" + 
				"\r\n" + 
				"<p>海鮮類大多是高蛋白質、低脂肪，而且煮法快速又簡單，例如：蝦子、蛤蜊、貝類、魚類&hellip;等，是減肥族的好夥伴，只要簡單水煮或汆燙，就可以食用了！畢竟海鮮料理會有鹽味，如果覺得沒什麼味道，可以加點胡椒鹽或海鹽調味。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"減脂菜單-海鮮類\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%913.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>特別是，海鮮類一定要推薦「<a href=\"https://blog.worldgymtaiwan.com/healthy-food-salmon\" target=\"_blank\">鮭魚</a>」，你會發現許多健身部落客或是網紅們，主食常常選擇煎鮭魚，因為鮭魚含有豐富的DHA，還有含有健康的油脂「Omega-3」，是有助於把人體內的壞脂肪分解的好脂肪，有助於燃燒更多卡路里，還能改善心血管疾病。而且鮭魚的料理方式超多樣，不管是煎、烤、生魚片，都很美味。另外，鯖魚與鯡魚也富含健康魚油「Omega-3」脂肪酸，適量吃，一樣能幫助減重！</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>4.鮮蔬水果類</h3>\r\n" + 
				"\r\n" + 
				"<p>相信大家都知道，想要減肥成功，蔬果類很重要！因為人體每天最不可或缺的，就是豐富蔬菜水果，不過水果類盡量選擇<a href=\"https://blog.worldgymtaiwan.com/are-low-gi-foods-good-for-weight-loss\" target=\"_blank\">低GI</a>、高營養密度的下手，例如：<a href=\"https://blog.worldgymtaiwan.com/healthy-food-best-time-to-eat-kiwi-for-weight-loss\" target=\"_blank\">奇異果</a>、<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E4%B8%80%E5%A4%A9%E4%B8%80%E9%A1%86%E8%98%8B%E6%9E%9C-%E9%86%AB%E7%94%9F%E9%81%A0%E9%9B%A2%E6%88%91\" target=\"_blank\">蘋果</a>、<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E8%8A%AD%E6%A8%82%E6%9C%80%E5%8F%97%E6%AD%A1%E8%BF%8E%E7%9A%8415%E5%80%8B%E5%8A%9F%E6%95%88\" target=\"_blank\">芭樂</a>&hellip;等；至於蔬菜類，除了綠色蔬菜，也可以搭配菇類，因為菇類的蛋白質含量也很高，且又是低熱量、高纖維的好食材，不僅增加飽足感，也有助於降低膽固醇、減少脂肪吸收。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>5.壽司飯捲</h3>\r\n" + 
				"\r\n" + 
				"<p><a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E4%B8%80%E5%BC%B5%E8%A1%A8%E9%81%BF%E9%96%8B%E5%A3%BD%E5%8F%B8%E7%86%B1%E9%87%8F%E5%9C%B0%E9%9B%B7\" target=\"_blank\">壽司</a>的變化性也很大，除了常見的海苔飯捲，還有手捲、握壽司、豆皮壽司&hellip;等，重點是，<a href=\"https://blog.worldgymtaiwan.com/tag/%E9%A3%B2%E9%A3%9F/%E6%8A%97%E6%80%A7%E6%BE%B1%E7%B2%89%E8%AE%93%E4%BD%A0%E8%BC%95%E9%AC%86%E4%BA%AB%E7%98%A6\" target=\"_blank\">壽司的米飯是抗性澱粉</a>，低熱量外，吃了又有飽足感；而餡料的部分，可以選擇當季、<a href=\"https://blog.worldgymtaiwan.com/%E8%9B%8B%E7%99%BD%E8%B3%AA%E4%B8%8D%E8%83%BD%E9%9A%A8%E4%BE%BF%E5%90%83-12%E7%A8%AE%E7%B2%BE%E7%98%A6%E8%9B%8B%E7%99%BD%E8%B3%AA%E5%8A%A9%E5%A2%9E%E8%82%8C%E6%B8%9B%E8%84%82\" target=\"_blank\">新鮮的魚類，補充豐富的蛋白質</a>、Omega3脂肪酸。而海苔壽司，外層的壽司營養高，脂肪低，長期吃可以增強免疫力、抗老，且降低心血管疾病的風險。</p>\r\n" + 
				"\r\n" + 
				"<p><img alt=\"減脂菜單-壽司飯捲\" sizes=\"(max-width: 700px) 100vw, 700px\" src=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png\" srcset=\"https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=350&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 350w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=700&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 700w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=1050&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 1050w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=1400&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 1400w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=1750&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 1750w, https://blog.worldgymtaiwan.com/hs-fs/hubfs/%E6%96%87%E7%AB%A0%E5%B0%88%E7%94%A8%E5%9C%96%E7%89%87/%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png?width=2100&amp;name=%E7%9C%8B%E9%81%8E%E4%BE%86%EF%BC%81%20%E5%B0%88%E5%B1%AC%E5%A4%96%E9%A3%9F%E6%97%8F%E3%80%8C%E6%B8%9B%E8%84%82%E8%8F%9C%E5%96%AE%E3%80%8D%E7%A7%98%E8%A8%A3%E5%A4%A7%E5%85%AC%E9%96%8B%E3%80%902020%E6%9B%B4%E6%96%B0%E3%80%915.png 2100w\" width=\"700\" /></p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>壽司的好處多，但也要注意這幾點，小心別踩雷！像是豆皮壽司，豆皮是屬於由炸物，再經過醬汁滷過，1份熱量將近350大卡！另外，有些壽司會拌上美乃滋，或是加上起司、焗烤，這些高熱量的配料或醬料，能少吃就少吃。配料可以改吃蘿蔔絲、生薑，有助於消化，幫助新陳代謝。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<h3>6.水煮餐+健康油脂</h3>\r\n" + 
				"\r\n" + 
				"<p>當然「水煮餐」，是最典型減脂菜單，不過，這麼說還少加一句，必須加點<a href=\"https://blog.worldgymtaiwan.com/tag/%E5%81%A5%E5%BA%B7/%E7%BE%8E%E9%AB%94/%E5%A5%BD%E5%A3%9E%E8%84%82%E8%82%AA%E5%B0%8D%E8%BA%AB%E9%AB%94%E7%9A%84%E5%BD%B1%E9%9F%BF\" target=\"_blank\">「健康油脂」</a>，會更好。因為有營養師證實「過度吃水煮是無法有效減肥」，少了油脂、同時也缺少飽足感，會讓身體想吃更多食物，而且無油脂等於無法幫腸道潤滑，容易產生便秘問題。</p>\r\n" + 
				"\r\n" + 
				"<p>&nbsp;</p>\r\n" + 
				"\r\n" + 
				"<p>因此，在水煮餐上，可以加入好油，例如不飽和脂肪酸：葵花油、橄欖油，是常常被廣泛拿來在減肥餐上使用的，適量攝取，能夠改善膚質、降膽固醇，搭配菜色美味又健康。</p>");	
		art.insert(articleVO1);
		System.out.println("新增成功");
		
		// 新增員工
//		ArticleVO articleVO01 = new ArticleVO();
//		articleVO01.setWork_id("WORK00080");
//		articleVO01.setTitle("官方文章新增1.1");
//		articleVO01.setArt_content("官方文章內容2.1...");
//		
//		art.insertwork(articleVO01);
//		System.out.println("新增成功");

//		// 修改會員
//		ArticleVO articleVO2 = new ArticleVO();	
//
//		articleVO2.setTitle("文章修改");
//		articleVO2.setArt_content("文章內容修改");		
//		articleVO2.setArticle_no("ART001");
//		
//		art.update(articleVO2);
//		
//		System.out.println("修改成功");
		
//		// 修改官方
//		ArticleVO articleVO2 = new ArticleVO();	
//
//		articleVO2.setTitle("文章修改");
//		articleVO2.setArt_content("文章內容修改V1");		
//		articleVO2.setArticle_no("ART013");
//		
//		art.update(articleVO2);
//		
//		System.out.println("修改成功");

		// 刪除
//		art.delete("ART013");
//		System.out.println("刪除成功");

		// 單一查詢
//		ArticleVO articleVO3 = art.findByPrimaryKey("ART009");
//		System.out.println("文章編號:"+articleVO3.getArticle_no() );
//		System.out.println("會員編號:"+articleVO3.getMem_id());
//		System.out.println("官方編號:"+articleVO3.getWork_id() );
//		System.out.println("文章標題:"+articleVO3.getTitle() );
//		System.out.println("文章內容:"+articleVO3.getArt_content() );
//		System.out.println("上傳時間:"+articleVO3.getA_release() );
//		System.out.println("讚數:"+articleVO3.getLink_count() );
//		System.out.println("留言數:"+articleVO3.getCom_count() );
//		System.out.println("文章狀態:"+articleVO3.getA_status());
//		System.out.println("---------------------");

		// 查詢全部
//				List<ArticleVO> list = art.getAll();
//				for (ArticleVO aArticle : list) {
//					System.out.println("文章編號:"+aArticle.getArticle_no() );
//					System.out.println("會員編號:"+aArticle.getMem_id() );
//					System.out.println("官方編號:"+aArticle.getWork_id() );
//					System.out.println("文章標題:"+aArticle.getTitle() );
//					System.out.println("文章內容:"+aArticle.getArt_content() );
//					System.out.println("上傳時間:"+aArticle.getA_release() );
//					System.out.println("讚數:"+aArticle.getLink_count() );
//					System.out.println("留言數:"+aArticle.getCom_count() );
//					System.out.println("文章狀態:"+aArticle.getA_status() );
//					System.out.println();
//					System.out.println("---------------------");
//				}
		
		
		// 查詢某部門的員工
//		Set<Art_CommentVO> set = art.getArt_CommentsByArticle_no("ART001");
//		for (Art_CommentVO aart_commentVO : set) {
//			System.out.print("留言編號:"+aart_commentVO.getCom_no() + ",");
//			System.out.print("會員編號:"+aart_commentVO.getMem_id() + ",");
//			System.out.print("留言內容:"+aart_commentVO.getMes_content() + ",");
//			System.out.print("留言時間:"+aart_commentVO.getCom_release() + ",");
//			System.out.print("留言狀態:"+aart_commentVO.getCom_status() + ",");
//			System.out.print("文章編號:"+aart_commentVO.getArticle_no() + ",");
//			System.out.println();								
//	}
				
	}	
	
}


