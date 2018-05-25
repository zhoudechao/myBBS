package com.service.post;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.post.PostMapper;
import com.model.post.Post;
import com.model.post.PostExtend;
import com.service.base.BaseService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;
@Service
@Transactional
public class PostService extends BaseService<Post> {
	@Autowired
	private  PostMapper postMapper;
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
	
	
	public PostExtend selectPostAndReply(int id){
		return postMapper.selectPostAndReply(id);
	}
	public List<Post> selectPostLimit(){
		return postMapper.selectPostLimit();
	}

	public List<Post> selectPostTop() {
		return postMapper.selectPostTop();
	}
	
	/**
	 * @Description: 分页查询出已结，未结，精华的帖子
	 * @param @param postExtend
	 * @param @return   
	 * @return PageInfo<Post>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月17日
	 */
	public PageInfo<Post> selectPostByPage(PostExtend postExtend){
		Example example=new Example(Post.class);
		Criteria criteria=example.createCriteria();
		if(postExtend.getPostIsend()!=null && postExtend.getPostIsend().equals("1")){
			criteria.andEqualTo("postIsend", "1");
		}
		if(postExtend.getPostIsend()!=null && postExtend.getPostIsend().equals("0")){
			criteria.andEqualTo("postIsend", "0");
		}
		if(postExtend.getPostIsbest()!=null && postExtend.getPostIsbest().equals("1")){
			criteria.andEqualTo("postIsbest", "1");
		}
		if(postExtend.getPostTypeid()!=null && !postExtend.getPostTypeid().equals("")){
			criteria.andEqualTo("postTypeid", postExtend.getPostTypeid());
		}
		return this.selectByExample(postExtend.getCurr(), postExtend.getLimit(), example);
	}
	
	public PageInfo selectPostByUserId(int id,int curr,int limit){
		PageHelper.startPage(curr, limit);
		List<Map<String, Object>> list=postMapper.selectPostByUserId(id);
		return new PageInfo<>(list);
	}
}
