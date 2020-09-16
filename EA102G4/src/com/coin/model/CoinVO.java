package com.coin.model;

public class CoinVO implements java.io.Serializable{
	private String coin_id;
	private String mem_id;
	private Integer deposit_coin;
	private Integer amount;
	private java.sql.Timestamp coin_date;
	private Integer co_status;

	public Integer getCo_status() {
		return co_status;
	}

	public void setCo_status(Integer co_status) {
		this.co_status = co_status;
	}

	public String getCoin_id() {
		return coin_id;
	}

	public void setCoin_id(String coin_id) {
		this.coin_id = coin_id;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public Integer getDeposit_coin() {
		return deposit_coin;
	}

	public void setDeposit_coin(Integer deposit_coin) {
		this.deposit_coin = deposit_coin;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public java.sql.Timestamp getCoin_date() {
		return coin_date;
	}

	public void setCoin_date(java.sql.Timestamp coin_date) {
		this.coin_date = coin_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coin_date == null) ? 0 : coin_date.hashCode());
		result = prime * result + ((coin_id == null) ? 0 : coin_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoinVO other = (CoinVO) obj;
		if (coin_date == null) {
			if (other.coin_date != null)
				return false;
		} else if (!coin_date.equals(other.coin_date))
			return false;
		if (coin_id == null) {
			if (other.coin_id != null)
				return false;
		} else if (!coin_id.equals(other.coin_id))
			return false;
		return true;
	}

}
