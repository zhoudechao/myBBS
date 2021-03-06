package com.controller.test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.authorization.IgnoreSecurityType;
import com.model.test.Test;
import com.model.user.User;
import com.service.test.TestService;
import com.service.user.UserService;

/**
 * @功能说明：测试
 * @作者： zhangshihui
 * @创建日期：2018-04-17
 */
@Controller
@RequestMapping("/test")
@IgnoreSecurityType
public class TestController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TestService testService;

	/**
	 * 测试列表跳转页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request, Model model) {
		//获取当前用户
		User user = (User) request.getSession().getAttribute("user");
		user = (User) userService.get(user);
		model.addAttribute("user", user);
		return "views/test/testList";
	}
	
	
	/**
	 * 分页获取测试
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/testData")
	public Map<String, Object> testData(HttpServletRequest request, Model model, Test test) throws UnsupportedEncodingException {
		 Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Test> list = testService.getListByPage(test);
	        Long count = testService.getCount(test);
	        map.put("code", 0);
	        map.put("msg", "");
	        map.put("count", count);
	        map.put("data", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

        return map;
		
	}
	
	/**
	 * 删除测试
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request, Model model, Test test) {
		String result = "1";
		try {
			testService.delete(test.getId());
			result = "0";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 批量删除测试
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBatch")
	public String deleteBatch(HttpServletRequest request, Model model, String ids) {
		String result = "1";
		try {
			String[] idarr = ids.split(",");
			for(String id : idarr){
				testService.delete(Integer.parseInt(id));
			}
			result = "0";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 新增测试跳转页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/form")
	public String form(Test test, HttpServletRequest request, Model model) {
		return "views/test/testForm";
	}
	
	/**
	 * 新增测试跳转页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String edit(Test test, HttpServletRequest request, Model model) {
		test = testService.get(test);
		model.addAttribute("test", test);
		return "views/test/testForm";
	}
	
	/**
	 * 保存测试
	 * @param request
	 * @param model
	 * @param user
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, Model model, Test test) throws UnsupportedEncodingException {
		String result = "1";//结果标识 1：失败 0：成功
		try
		{
			//设置默认值
			test.setStatus("0");//初始状态为可用
			testService.save(test);
			result = "0";
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
}
