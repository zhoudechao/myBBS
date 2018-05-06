package com.controller.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authorization.IgnoreSecurityType;
import com.controller.base.BaseController;
import com.github.pagehelper.PageInfo;
import com.model.common.Common;
import com.service.common.CommonService;


@Controller
@RequestMapping("/common")
@IgnoreSecurityType
public class CommonController extends BaseController<Common>{
	@Autowired
	private CommonService commonService;
	/**
	 * @Description: 跳转到公共信息版块的页面
	 * @param @param request
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月26日
	 */
	/*@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model){
		//获取当前用户
		User user=(User) request.getSession().getAttribute("user");
		user=userService.get(user);
		model.addAttribute("user",user);
		return "views/common/commonList";
	}*/
	
	@SuppressWarnings("all")
	@ResponseBody
	@RequestMapping(value="/commonData")
	public Map<String, Object> commonData(Model model,int page,int limit,Common common){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			PageInfo<Common> info=commonService.selectCommon(common,page,limit);
			map.put("code", 0);
	        map.put("msg", "");
	        map.put("count", info.getTotal());
	        map.put("data", info.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Map<String, Object> save(Common common){
		int status=0;
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			if(common.getCommonId()!=null &&!common.getCommonId().equals("")){
				status=commonService.updateNotNull(common);
				map.put("status", String.valueOf(status));
			}else{
				common.setZt("1");
				//把当前系统时间转为datetime的格式存入数据库
				Date date=new Date();
				Timestamp timestamp=new Timestamp(date.getTime());
				common.setCommonCreattime(timestamp);
				status=commonService.save(common);
				map.put("status", String.valueOf(status));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Common common){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			int status=commonService.delete(common.getCommonId());
			map.put("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/setUse")
	public Map<String, Object> setUse(Common common){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			common=commonService.queryone(common);
			if(common.getZt().equals("0")){
				common.setZt("1");
			}else{
				common.setZt("0");
			}
			int status=commonService.updateNotNull(common);
			map.put("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
