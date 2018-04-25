package com.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.base.AjaxResult;
import com.model.user.User;
import com.service.user.UserService;
import com.util.Tool;

/*
 * 登陆
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	private static Logger log = Logger.getLogger(LoginController.class);
	
	/**
	 * 登录跳转页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model) {
		log.info("进入到登录界面！");
		return "views/sys/login";
	}
	
	/**
	 * 登录
	 * @param request
	 * @param name
	 * @param pwd
	 * @return
	 */
	@ResponseBody
	@RequestMapping("login")
	public AjaxResult login(HttpServletRequest request,String name,String pwd) {
		log.info("用户名"+name);
		log.info("密码"+pwd);
		//若存在会话则返回该会话，否则新建一个会话（session）
		HttpSession session = request.getSession(true); 
		AjaxResult ajaxResult = new AjaxResult();
		
		User user = new User();
		user.setLoginName(name);
		List<User> users = userService.getAllList(user);
		if(users.size() > 0){
			User u = users.get(0);
			if(Tool.MD5(pwd).equals(u.getPwd())){
				if("1".equals(u.getStatus())){
					//该用户不可用
					ajaxResult.setCode("3");
					ajaxResult.setMsg("用户不可用，请联系管理员！");
				}else{
					//登录成功
					//将user放入session
					session.setAttribute("user", u);
					ajaxResult.setCode("0");
					ajaxResult.setMsg("登录成功");
				}
			}else{
				//密码不正确
				ajaxResult.setCode("1");
				ajaxResult.setMsg("密码不正确");
			}
		}else{
			//用户不存在
			ajaxResult.setCode("2");
			ajaxResult.setMsg("用户不存在");
		}
		return ajaxResult;
	}
	
	/**
	 * 退出
	 * @param request
	 * @param name
	 * @param pwd
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "views/sys/login";
	}
	
}
