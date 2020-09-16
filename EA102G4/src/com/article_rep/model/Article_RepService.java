package com.article_rep.model;

import java.util.List;



public class Article_RepService {
	private Article_RepDAO_interface art;

	public Article_RepService() {
		art = new Article_RepJDBCDAO();
	}
	
	public Article_RepVO addArticle_Rep( String mem_id, String article_no,  String rep_content) {

		Article_RepVO article_repVO = new Article_RepVO();
	
		article_repVO.setMem_id(mem_id);
		article_repVO.setArticle_no(article_no);
		article_repVO.setRep_content(rep_content);
		art.insert(article_repVO);

		return article_repVO;
	}
	
	

//	//預留給 Struts 2 用的
//	public void addEmp(ArticleVO AtricleVO) {
//		art.insert(AtricleVO);
//	}
//	
	public Article_RepVO updateArticle_Rep(String rep_content, String art_rep_no, String com_status) {

		Article_RepVO article_repVO = new Article_RepVO();
		article_repVO.setRep_content(rep_content);
		article_repVO.setArt_rep_no(art_rep_no);
		article_repVO.setArt_rep_no(com_status);		
		
		art.update(article_repVO);

		return article_repVO;
	}
	
	

	public List<Article_RepVO> getAll() {
		return art.getAll();
	}

	public Article_RepVO getOneArticle_Rep(String art_rep_no) {
		return art.findByPrimaryKey(art_rep_no);
	}


}
