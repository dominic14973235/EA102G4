package com.IClassOrder.model;

import java.util.List;

import com.mem.model.MemVO;

public interface IClassOrderDAO_interface {

	public void insert(IClassOrderVO iClassOrderVO);

	public void insert2(IClassOrderVO iClassOrderVO, MemVO memVO);

	public void update(IClassOrderVO iClassOrderVO);

	public void delete(String i_order_no);

	public List<IClassOrderVO>  findByMemId(String mem_ID);

	public List<IClassOrderVO>  findByClassNo(String i_class_no);

	public IClassOrderVO findPrimaryKey(String i_order_no);

	public List<IClassOrderVO> getAll();
}
