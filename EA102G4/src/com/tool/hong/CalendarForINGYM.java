package com.tool.hong;

import java.util.*;

import com.godetail.model.GroupOrderDetailVO;
import com.grouphour.model.GroupHourVO;
import com.memTimeV.model.MemTimeVO;
import com.trainerPublicV.model.TrainerPublicVO;
import com.trainerreservation.model.TrainerReservationVO;

public class CalendarForINGYM {
	private java.sql.Date rdate;
	private String hr;
	
	
	
	
	
	public CalendarForINGYM() {
		
	}
	public CalendarForINGYM(GroupHourVO ghVO) {
		this.rdate=ghVO.getC_date();
		this.hr=ghVO.getHr();
	}
	public CalendarForINGYM(GroupOrderDetailVO godVO) {
		this.rdate=godVO.getRdate();
		this.hr=godVO.getHr();
	}
	public CalendarForINGYM(TrainerReservationVO trVO) {
		this.rdate=trVO.getRdate();
		this.hr=trVO.getHr();
	}
	public CalendarForINGYM(MemTimeVO mtVO) {
		this.rdate=mtVO.getrDate();
		this.hr=mtVO.getHr();
	}
	public CalendarForINGYM(TrainerPublicVO tpVO) {
		this.rdate=tpVO.getrDate();
		this.hr=tpVO.getHr();
	}
	
	public HashSet<CalendarForINGYM> getCalendarSetForMTVO(HashSet<MemTimeVO> set){
		HashSet<CalendarForINGYM> toolset = new HashSet<CalendarForINGYM>();
		for(MemTimeVO mtVO : set) {
			toolset.add(new CalendarForINGYM(mtVO));
		}		
		return toolset;
	}
	
	public HashSet<CalendarForINGYM> getCalendarSetForTPVO(HashSet<TrainerPublicVO> set){
		HashSet<CalendarForINGYM> toolset = new HashSet<CalendarForINGYM>();
		for(TrainerPublicVO tpVO : set) {
			toolset.add(new CalendarForINGYM(tpVO));
		}		
		return toolset;
	}
	
	public HashSet<CalendarForINGYM> getCalendarSetForGHVO(HashSet<GroupHourVO> set){
		HashSet<CalendarForINGYM> toolset = new HashSet<CalendarForINGYM>();
		for(GroupHourVO ghVO : set) {
			toolset.add(new CalendarForINGYM(ghVO));
		}		
		return toolset;
	}
	public HashSet<CalendarForINGYM> getCalendarSetTRVO(HashSet<TrainerReservationVO> set){
		HashSet<CalendarForINGYM> toolset = new HashSet<CalendarForINGYM>();
		for(TrainerReservationVO trVO : set) {
			toolset.add(new CalendarForINGYM(trVO));
		}		
		return toolset;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hr == null) ? 0 : hr.hashCode());
		result = prime * result + ((rdate == null) ? 0 : rdate.hashCode());
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
		CalendarForINGYM other = (CalendarForINGYM) obj;
		if (hr == null) {
			if (other.hr != null)
				return false;
		} else if (!hr.equals(other.hr))
			return false;
		if (rdate == null) {
			if (other.rdate != null)
				return false;
		} else if (!rdate.equals(other.rdate))
			return false;
		return true;
	}
	public java.sql.Date getRdate() {
		return rdate;
	}
	public void setRdate(java.sql.Date rdate) {
		this.rdate = rdate;
	}
	public String getHr() {
		return hr;
	}
	public void setHr(String hr) {
		this.hr = hr;
	}
	
}
