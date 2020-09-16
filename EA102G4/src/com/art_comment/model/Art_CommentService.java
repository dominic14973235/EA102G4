package com.art_comment.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public class Art_CommentService {

	private Art_CommentDAO_interface art;

	public Art_CommentService() {
		art = new Art_CommentJDBCDAO();
	}
	
	public Art_CommentVO addArt_Comment( String article_no, String mem_id, String mes_content, String com_status) {
		
		System.out.println("Service");
		System.out.println("article_no = "+article_no);
		System.out.println("mem_id = "+mem_id);
		System.out.println("mes_content = "+mes_content);
		
		
		Art_CommentVO art_commentVO = new Art_CommentVO();
	
		art_commentVO.setArticle_no(article_no);
		art_commentVO.setMem_id(mem_id);
		art_commentVO.setMes_content(mes_content);
		art_commentVO.setCom_status(com_status);
		art.insert(art_commentVO);

		return art_commentVO;
	}
	
	

//	//預留給 Struts 2 用的
//	public void addEmp(ArticleVO AtricleVO) {
//		art.insert(AtricleVO);
//	}
//	
	public Art_CommentVO updateArt_Comment(String mes_content, String com_no) {

		Art_CommentVO art_commentVO = new Art_CommentVO();
		art_commentVO.setMes_content(mes_content);
		art_commentVO.setCom_no(com_no);
				
		
		art.update(art_commentVO);

		return art_commentVO;
	}
	
	

	public List<Art_CommentVO> getAll() {
		return art.getAll();
	}

	public Art_CommentVO getOneArt_Comment(String com_no) {
		return art.findByPrimaryKey(com_no);
	}
	
	public void deleteArt_Comment(String com_no) {
		art.delete(com_no);
	}


}
