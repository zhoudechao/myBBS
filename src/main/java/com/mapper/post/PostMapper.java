package com.mapper.post;

import java.util.List;
import java.util.Map;

import com.model.post.Post;
import tk.mybatis.mapper.common.Mapper;

public interface PostMapper extends Mapper<Post> {
	/**
	 * @Description: 根据帖子ID查询出回复的内容
	 * @param @param id
	 * @param @return   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月11日
	 */
	List<Map<String, Object>> selectPostAndReply(int id);
	
	
	List<Post> selectPostTop();
	
	List<Post> selectPostLimit();
	
	int selectPostNum();
}