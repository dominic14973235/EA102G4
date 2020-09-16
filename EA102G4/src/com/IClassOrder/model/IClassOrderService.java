package com.IClassOrder.model;

import java.sql.Date;
import java.util.List;

import com.mem.model.MemVO;

public class IClassOrderService {

	private IClassOrderDAO_interface dao;

	public IClassOrderService() {
		dao = new IClassOrderDAO();
	}

	public IClassOrderVO addIndividualClass(String i_class_no, String mem_ID, Date rDate, String hr, Integer p_coin,
			Integer io_status) {

		IClassOrderVO iClassOrderVO = new IClassOrderVO();

		iClassOrderVO.setI_class_no(i_class_no);
		iClassOrderVO.setMem_ID(mem_ID);
		iClassOrderVO.setRDate(rDate);
		iClassOrderVO.setHr(hr);
		iClassOrderVO.setP_coin(p_coin);
		iClassOrderVO.setIo_status(io_status);
		dao.insert(iClassOrderVO);

		return iClassOrderVO;
	}
	
	public IClassOrderVO addIndividualClass2(String i_class_no, String mem_ID, Date rDate, String hr, Integer p_coin,
			Integer io_status) {

		IClassOrderVO iClassOrderVO = new IClassOrderVO();

		iClassOrderVO.setI_class_no(i_class_no);
		iClassOrderVO.setMem_ID(mem_ID);
		iClassOrderVO.setRDate(rDate);
		iClassOrderVO.setHr(hr);
		iClassOrderVO.setP_coin(p_coin);
		dao.insert(iClassOrderVO);

		return iClassOrderVO;
	}

	public void updateIClassVO(IClassOrderVO iClassOrderVO) {
		dao.update(iClassOrderVO);
	}

	public void deleteIClassOrderVO(String i_order_no) {
		dao.delete(i_order_no);
	}

	public IClassOrderVO getOneIClassOrderVO(String i_order_no) {
		return dao.findPrimaryKey(i_order_no);
	}

	public List<IClassOrderVO> getAll() {
		return dao.getAll();
	}
	
	public List<IClassOrderVO> getByMem(String mem_ID) {
		return dao.findByMemId(mem_ID);
	}
	
	public List<IClassOrderVO> findByClassNo(String i_class_no) {
		return dao.findByClassNo(i_class_no);
	}

	public void insert2(IClassOrderVO icVO, MemVO memVO) {
		dao.insert2(icVO,memVO);
	}

}
