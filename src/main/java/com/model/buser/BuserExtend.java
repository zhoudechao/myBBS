package com.model.buser;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

public class BuserExtend extends Buser {

	private int page;
	private int limit;
	private DateTime startTime;
	private DateTime endTime;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public DateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}
	public DateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}
	

}
