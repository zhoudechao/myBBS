package com.controller.post;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authorization.IgnoreSecurityType;
import com.controller.base.BaseController;
import com.github.pagehelper.PageInfo;
import com.model.post.Post;
import com.model.post.PostExtend;
import com.service.post.PostService;
import com.util.DateUtil;

@Controller
@RequestMapping("/post")
@IgnoreSecurityType
public class PostController extends BaseController<Post> {
	@Autowired
	private PostService postService;
	
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
}
