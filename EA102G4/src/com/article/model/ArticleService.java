package com.article.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.art_comment.model.Art_CommentVO;
import com.article_rep.model.Article_RepVO;


public class ArticleService {

	private ArticleDAO_interface art;

	public ArticleService() {
		art = new ArticleJDBCDAO();
	}
	
	public ArticleVO addArticle(String mem_id, String title, String art_content) {

		ArticleVO atricleVO = new ArticleVO();
	
		atricleVO.setMem_id(mem_id);
		atricleVO.setTitle(title);
		atricleVO.setArt_content(art_content);
		art.insert(atricleVO);

		return atricleVO;
	}
	
	public ArticleVO addArticle1(String work_id, String title, String art_content) {

		ArticleVO atricleVO1 = new ArticleVO();
		atricleVO1.setWork_id(work_id);
		atricleVO1.setTitle(title);
		atricleVO1.setArt_content(art_content);		
		art.insertwork(atricleVO1);


		return atricleVO1;
	}

//	//預留給 Struts 2 用的
//	public void addEmp(ArticleVO AtricleVO) {
//		art.insert(AtricleVO);
//	}
//	
	public ArticleVO updateArticle(String title, String art_content,  String article_no) {

		ArticleVO atricleVO = new ArticleVO();
		atricleVO.setTitle(title);
		atricleVO.setArt_content(art_content);
		atricleVO.setArticle_no(article_no);
		
		
		art.update(atricleVO);

		return atricleVO;
	}
	
	
	
	public ArticleVO updateArticle2(String men_id, String work_id, String title, String art_content, Integer link_count, Integer com_count, String a_status, String article_no) {

		ArticleVO atricleVO1 = new ArticleVO();
		
		atricleVO1.setMem_id(men_id);
		atricleVO1.setWork_id(work_id);
		atricleVO1.setTitle(title);
		atricleVO1.setArt_content(art_content);
		atricleVO1.setLink_count(link_count);
		atricleVO1.setCom_count(com_count);
		atricleVO1.setA_status(a_status);
		atricleVO1.setArticle_no(article_no);
		art.update2(atricleVO1);

		return atricleVO1;
	}
	
//	public ArticleVO deleteArticle(String article_no) {
//		ArticleVO atricleVO2 = new ArticleVO();
//		art.delete(article_no);
//		return atricleVO2;
//	}

	public List<ArticleVO> getAll() {
		return art.getAll();
	}

	public ArticleVO getOneArticle(String article_no) {
		return art.findByPrimaryKey(article_no);
	}

	public Set<Art_CommentVO> getArt_CommentsByArticle_no(String article_no) {
		return art.getArt_CommentsByArticle_no(article_no);
	}
	
	public Set<Article_RepVO> getArticle_RepsByArticle_no(String article_no) {
		return art.getArticle_RepsByArticle_no(article_no);
	}
	
	public void deleteArticle(String article_no) {
		art.delete(article_no);
	}
	
	public List<ArticleVO> getRepArt(){
		return art.getRepArt();
	}
	
	public void updateToNoShow(String article_no) {
		art.updateToNoShow(article_no);
	}

}
