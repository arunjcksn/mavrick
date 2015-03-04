package com.mavrick.common.vo.order;

import java.util.Calendar;

import com.mavrick.common.vo.MaverickVO;

public class SlotDisplayVO implements MaverickVO{
private String stopId;
	
	private Calendar slotDate;
	
	private String startTime;
	
	private String endTime;
	
	private double slotPrice;

	public String getStopId() {
		return stopId;
	}

	public void setStopId(String stopId) {
		this.stopId = stopId;
	}

	public Calendar getSlotDate() {
		return slotDate;
	}

	public void setSlotDate(Calendar slotDate) {
		this.slotDate = slotDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public double getSlotPrice() {
		return slotPrice;
	}

	public void setSlotPrice(double slotPrice) {
		this.slotPrice = slotPrice;
	}

}
