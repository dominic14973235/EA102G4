package com.individualClass.model;

import java.util.*;

public interface IndividualClassDAO_interface {
	public void insert(IndividualClassVO individualClassVO);

	public void update(IndividualClassVO individualClassVO);

	public void delete(String i_class_no);

	public IndividualClassVO findPrimaryKey(String i_class_no);

	public List<IndividualClassVO> findByPro(String pro_ID);

	public List<IndividualClassVO> findByTypeNo(String c_type_no);

	public List<IndividualClassVO> getAll();
}
