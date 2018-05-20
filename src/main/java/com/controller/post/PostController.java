package com.controller.post;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authorization.IgnoreSecurityType;
import com.controller.base.BaseController;
import com.github.pagehelper.PageInfo;
import com.model.buser.Buser;
import com.model.post.Post;
import com.model.post.PostExtend;
import com.service.buser.BuserService;
import com.service.post.PostService;
import com.service.type.TypeService;
import com.util.DateUtil;

@Controller
@RequestMapping("/post")
@IgnoreSecurityType
public class PostController extends BaseController<Post> {
	@Autowired
	private PostService postService;
	
	@Autowired
	private BuserService buserService;
	@Autowired
	private TypeService typeService;
	/**
	 * @Description: 后台查询出帖子的数据
	 * @param @param postExtend
	 * @param @param startTime1
	 * @param @param endTime1
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月17日
	 */
	@ResponseBody
	@RequestMapping("/postData")
	public Map<String, Object> postData(PostExtend postExtend,String startTime1,String endTime1){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			if(startTime1!=null && !startTime1.equals("")){
				Date date = DateUtil.formatDateStirng(startTime1);
				Timestamp timestamp=new Timestamp(date.getTime());
				postExtend.setStartTime(timestamp);
			}
			if(endTime1!=null && !endTime1.equals("")){
				Date date = DateUtil.formatDateStirng(endTime1);
				Timestamp timestamp=new Timestamp(date.getTime());
				postExtend.setEndTime(timestamp);
			}
			PageInfo<Post> info=postService.selectPost(postExtend);
			map.put("code", 0);
	        map.put("msg", "");
	        map.put("count", info.getTotal());
	        map.put("data", info.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * @Description: 根据帖子ID查询出回复的内容
	 * @param @param id
	 * @param @return   
	 * @return List<Map<String,Object>>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月11日
	 */
	@ResponseBody
	@RequestMapping(value="/getPost/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8"
			,consumes="application/json;charset=UTF-8")
	public List<Map<String, Object>> getPost(@PathVariable(value="id") Integer id){
		if(id !=null){
			List<Map<String, Object>> list = postService.selectPostAndReply(id);
			return list;
		}
		return null;
	}
	/**
	 * @Description: 查询出顶置的4条帖子
	 * @param @return   
	 * @return List<Post>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月17日
	 */
	@ResponseBody
	@RequestMapping(value="/selectPostTop",method=RequestMethod.GET,produces="application/json;charset=UTF-8"
			,consumes="application/json;charset=UTF-8")
	public List<Post> selectPostTop(){
		return postService.selectPostTop();
	}
	/**
	 * @Description: 查询出首页的前20条帖子
	 * @param @return   
	 * @return List<Post>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月17日
	 */
	@ResponseBody
	@RequestMapping(value="/selectPostLimit",method=RequestMethod.GET,produces="application/json;charset=UTF-8"
			,consumes="application/json;charset=UTF-8")
	public List<Post> selectPostLimit(){
		return postService.selectPostLimit();
	}
	
	/**
	 * @Description: 前台查询出帖子的数据
	 * @param @param postExtend
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月17日
	 */
	@ResponseBody
	@RequestMapping(value="/selectPostByPage",method=RequestMethod.POST)
	public Map<String, Object> selectPostByPage(PostExtend postExtend){
		PageInfo<Post> info=postService.selectPostByPage(postExtend);
		Map<String, Object> map=new HashMap<String, Object>();
		if(info!=null){
			map.put("count", info.getTotal());
			map.put("data", info.getList());
		}
		return map;
	}
	
	/**
	 * @Description: 分页查询出我发表的帖子的数量
	 * @param @param id
	 * @param @param curr
	 * @param @param limit
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月20日
	 */
	@ResponseBody
	@RequestMapping(value="/getPostByUserId/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8"
			,consumes="application/json;charset=UTF-8")
	public Map<String, Object> getPostByUserId(@PathVariable(value="id") Integer id,
			Integer curr,Integer limit){
		Map<String, Object> map=new HashMap<String, Object>();
		if(id !=null){
			PageInfo info = postService.selectPostByUserId(id,curr,limit);
			map.put("count", info.getTotal());
			map.put("data", info.getList());
			return map;
		}
		return null;
	}
	
	/**
	 * @Description: 发表帖子
	 * @param @param post
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月20日
	 */
	@ResponseBody
	@RequestMapping(value="/addPost",method=RequestMethod.POST)
	public Map<String, Object> addPost(Post post){
		if(post.getPostUserid()!=null){
			String userName = buserService.selectUserNameById(post.getPostUserid());
			if(userName!=null){
				post.setPostUsername(userName);
			}
		}
		if(post.getPostTypeid()!=null){
			String typeName=typeService.selectTypeNameById(post.getPostTypeid());
			if(typeName!=null){
				post.setPostTypename(typeName);
			}
		}
		Date date=new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		post.setPostCreatetime(timestamp);
		int i = postService.save(post);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", i);
		return map;
	}
	
	/**
	 * @Description: 根据帖子ID查询出帖子内容
	 * @param @param id
	 * @param @return   
	 * @return Post  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月20日
	 */
	@ResponseBody
	@RequestMapping(value="/getPostById/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8"
			,consumes="application/json;charset=UTF-8")
	public Post getPostById(@PathVariable(value="id") Integer id){
		if(id != null){
			Post post = postService.selectByKey(id);
			return post;
		}
		return null;
	}
	/**
	 * @Description:修改帖子
	 * @param @param post
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月20日
	 */
	@ResponseBody
	@RequestMapping(value="/putPost",method=RequestMethod.PUT)
	public Map<String, Object> putPost(Post post){
		Map<String, Object> map = new HashMap<String, Object>();
		int i = postService.updateByKey(post);
		map.put("status", i);
		return map;
	}
	
	/**
	 * @Description: 实现更新操作时部分字段不用更新
	 * @param @param postId
	 * @param @param map   
	 * @return void  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年5月20日
	 */
	@ModelAttribute
	public void getPost(@RequestParam(value="postId",required=false) Integer postId,
			Map<String, Object> map){
		if(postId!=null){
			Post post=postService.selectByKey(postId);
			map.put("post", post);
		}
	}
}
