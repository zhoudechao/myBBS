package com.mapper.post;

import java.util.List;
import java.util.Map;

import com.model.post.Post;
import com.model.post.PostExtend;

import tk.mybatis.mapper.common.Mapper;

public interface PostMapper extends Mapper<Post> {
	PostExtend selectPostAndReply(int id);
	
	List<Post> selectPostTop();
	
	List<Post> selectPostLimit();
	
	List<Map<String, Object>> selectPostByUserId(int id);
}