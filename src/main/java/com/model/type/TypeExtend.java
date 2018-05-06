package com.model.type;

import java.util.List;

import com.model.post.Post;

public class TypeExtend extends Type {

	private List<Post> list;

	public List<Post> getList() {
		return list;
	}

	public void setList(List<Post> list) {
		this.list = list;
	}

}
