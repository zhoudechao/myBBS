package com.controller.buser;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.controller.base.BaseController;
import com.github.pagehelper.PageInfo;
import com.model.buser.Buser;
import com.model.buser.BuserExtend;
import com.service.buser.BuserService;

@Controller
@RequestMapping("/buser")
public class BuserController extends BaseController<Buser>{
	@Autowired
	private BuserService buserService;
	
	@SuppressWarnings("all")
	@ResponseBody
	@RequestMapping(value="/buserData")
	public Map<String, Object> boardData(Model model,BuserExtend buserExtend){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			PageInfo<Buser> info=buserService.selectBuser(buserExtend);
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
	@RequestMapping("/delete")
	public Map<String, Object> delete(Buser buser){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			int status=buserService.delete(buser.getUserId());
			map.put("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * @Description: 设置版块信息为可用或者不可用
	 * @param @param board
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月26日
	 */
	@ResponseBody
	@RequestMapping("/setUse")
	public Map<String, Object> setUse(Buser buser){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			buser=buserService.queryone(buser);
			if(buser.getZt().equals("0")){
				buser.setZt("1");
			}else{
				buser.setZt("0");
			}
			int status=buserService.updateNotNull(buser);
			map.put("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
