package com.article_rep.model;

import java.util.List;
import java.util.Set;



public interface Article_RepDAO_interface {
	public void insert(Article_RepVO article_repVO);
    public void update(Article_RepVO article_repVO);
    public void delete(String art_rep_no);
    public Article_RepVO findByPrimaryKey(String art_rep_no);
    public List<Article_RepVO> getAll();

}
