package com.model.post;

import com.model.board.Board;
import com.model.buser.Buser;
import com.model.type.Type;

public class PostExtend extends Post {

	private Buser buser;
	private Board board;
	private Type type;
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
