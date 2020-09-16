package com.art_comment.model;

import java.util.List;
import java.util.Set;

public interface Art_CommentDAO_interface {
	public void insert(Art_CommentVO art_commentVO);
    public void update(Art_CommentVO art_commentVO);
    public void delete(String com_no);
    public Art_CommentVO findByPrimaryKey(String com_no);
    public List<Art_CommentVO> getAll();
    //查詢某部門的員工(一對多)(回傳 Set)
    //public Set<Art_CommentVO> getEmpsByDeptno(String com_no);
}
