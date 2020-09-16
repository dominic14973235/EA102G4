package com.coin.model;

import java.util.List;

public interface CoinDAO_interface {
	public String insert(CoinVO coinvo);
    public void update(CoinVO coinvo);
    public CoinVO findByPrimaryKey(String coin_id);
    public List<CoinVO> getAllByMem_Id(String mem_id);
    public List<CoinVO> getAll();
}
