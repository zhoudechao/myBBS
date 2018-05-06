package com.model.board;

import java.util.List;

import com.model.post.Post;

public class BoardExtend extends Board {
	private List<Post> list;

	public List<Post> getList() {
		return list;
	}

	public void setList(List<Post> list) {
		this.list = list;
	}
	
}
