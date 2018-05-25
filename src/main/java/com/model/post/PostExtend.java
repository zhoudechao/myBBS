package com.model.post;

import java.util.Date;
import java.util.List;

import com.model.board.Board;
import com.model.buser.Buser;
import com.model.reply.Reply;
import com.model.type.Type;

public class PostExtend extends Post {

	private Buser buser;
	private Board board;
	private Type type;
	private int page;
	private int limit;
	private Date startTime;
	private Date endTime;
	private int curr;       //前台传过来的当前页
	private List<Reply> listReply;
	
	
	public List<Reply> getListReply() {
		return listReply;
	}

	public void setListReply(List<Reply> listReply) {
		this.listReply = listReply;
	}
	public int getCurr() {
		return curr;
	}

	public void setCurr(int curr) {
		this.curr = curr;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Buser getBuser() {
		return buser;
	}

	public void setBuser(Buser buser) {
		this.buser = buser;
	}
	
}
