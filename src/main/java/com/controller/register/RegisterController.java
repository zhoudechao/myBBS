package com.controller.register;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.buser.Buser;
import com.service.buser.BuserService;
@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private BuserService buserService;
	private static final Logger log = Logger.getLogger(RegisterController.class);
	
	@ResponseBody
	@RequestMapping(value="/addBuser",method=RequestMethod.POST,produces="application/json;charset=UTF-8"
			,consumes="application/json;charset=UTF-8")
	public Map<String, Object> addBuser(@RequestBody Buser buser){
		Map<String, Object> map=new HashMap<String, Object>();
		if(buser.getUserName()!=null && buser.getUserPassword() !=null){
			//查重
			Buser buser2=buserService.queryone(buser);
			if(buser2!=null && buser2.getUserName().equals(buser.getUserName())){
				map.put("msg", "该用户已经存在！");
			}else{
				Date date=new Date();
				Timestamp timestamp=new Timestamp(date.getTime());
				buser.setUserJoindate(timestamp);
				buser.setUserLogins(1);
				try {
					int status=buserService.save(buser);
					if(status==1){
						map.put("status", 0);
						map.put("msg", "注册成功,请登录");
						map.put("code", 0);
						map.put("action", "login.html");
					}else{
						map.put("status", status);
						map.put("msg", "注册失败");
						map.put("code", 1);
						map.put("action", "reg.html");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				log.debug("添加用户");
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/getBuserAndEmail",method=RequestMethod.POST,produces="application/json;charset=UTF-8"
			,consumes="application/json;charset=UTF-8")
	public Map<String, Object> getBuserAndEmail(@RequestBody Buser buser){
		Map<String, Object> map=new HashMap<String, Object>();
		if(buser.getUserName()!=null || buser.getUserEmail()!=null){
			Buser buser2 = buserService.queryone(buser);
			if(buser2!=null && buser2.getUserName().equals(buser.getUserName())){
					map.put("msg", "该用户名已经存在");
					map.put("status", 0);
			}
			if(buser2!=null && buser2.getUserEmail().equals(buser.getUserEmail())){
				map.put("msg", "该邮箱已经存在");
				map.put("status", 0);
			}
		}
		return map;
	}
}
