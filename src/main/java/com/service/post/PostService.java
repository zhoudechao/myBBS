package com.service.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.model.post.Post;
import com.model.post.PostExtend;
import com.service.base.BaseService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;
@Service
@Transactional
public class PostService extends BaseService<Post> {
	/**
	 * @Description: 跳转到帖子页面的时候刷新出帖子
	 * @param @param postExtend
	 * @param @return   
	 * @return PageInfo<Post>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月8日
	 */
	public PageInfo<Post> selectPost(PostExtend postExtend) {
		Example example=new Example(Post.class);
		Criteria criteria=example.createCriteria();
		if(StringUtil.isNotEmpty(postExtend.getPostBoardname())){
			String id="%"+postExtend.getPostBoardname()+"%";
			criteria.andLike("postBoardname", id);
		}
		if(StringUtil.isNotEmpty(postExtend.getPostTypename())){
			String id="%"+postExtend.getPostTypename()+"%";
			criteria.andLike("postTypename", id);
		}
		if(StringUtil.isNotEmpty(postExtend.getPostUsername())){
			String id="%"+postExtend.getPostUsername()+"%";
			criteria.andLike("postUsername", id);
		}
		if(postExtend.getStartTime() !=null && postExtend.getEndTime() !=null){
			criteria.andGreaterThanOrEqualTo("postCreatetime", postExtend.getStartTime())
			.andLessThanOrEqualTo("postCreatetime", postExtend.getEndTime());
		}
		PageInfo<Post> info = this.selectByExample(postExtend.getPage(), postExtend.getLimit(), example);
		return info;
	}

}
