package com.coin.model;

import java.util.List;


public class CoinService {
	
	private CoinDAO_interface dao;
	
	
	public CoinService() {
		dao = new CoinDAO();
	}
	
	public CoinVO addCoinOrder(String mem_id,Integer deposit_coin,Integer amount ) {
		CoinVO coinVO = new CoinVO();
		coinVO.setDeposit_coin(deposit_coin);
		coinVO.setMem_id(mem_id);
		coinVO.setAmount(amount);
		String coin_id = dao.insert(coinVO);
		coinVO.setCoin_id(coin_id);
		return coinVO;
	}
	
	public CoinVO updateCoinOrder(Integer deposit_coin,Integer amount,String coin_id,Integer co_status) {
		CoinVO coinVO = new CoinVO();
		coinVO.setAmount(amount);
		coinVO.setCoin_id(coin_id);
		coinVO.setDeposit_coin(deposit_coin);
		coinVO.setCo_status(co_status);
		dao.update(coinVO);
		return coinVO;
	}
	
	public CoinVO getOneCoinOreder(String coin_id) {
		return dao.findByPrimaryKey(coin_id);
	}
	
	public List<CoinVO> getAll(){
		return dao.getAll();
	}
	
	public List<CoinVO> getMemCoinOrder(String mem_id){
		return dao.getAllByMem_Id(mem_id);
	}
}
