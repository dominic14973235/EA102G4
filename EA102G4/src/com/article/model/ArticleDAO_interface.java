 package com.article.model;

import java.util.*;

import com.art_comment.model.Art_CommentVO;
import com.article_rep.model.Article_RepVO;


public interface ArticleDAO_interface {
	public void insert(ArticleVO articleVO);
	public void insertwork(ArticleVO articleVO);
	
	public void update(ArticleVO articleVO);
	
	public void update2(ArticleVO articleVO);
	
	public void delete(String article_no);
	public ArticleVO findByPrimaryKey(String article_no);
	public List<ArticleVO> getAll();
	
	 //查詢某文章的留言(一對多)(回傳 Set)
    public Set<Art_CommentVO> getArt_CommentsByArticle_no(String article_no);
    
    //查詢某文章的檢舉(一對多)(回傳 Set)
    public Set<Article_RepVO> getArticle_RepsByArticle_no(String article_no);
    
    public List getRepArt();
    
//    public void update_rep(String article_no);
    
    public void updateToNoShow(String article_no);
	
    
    
}
