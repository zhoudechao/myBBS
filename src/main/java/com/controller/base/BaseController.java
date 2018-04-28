package com.controller.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.user.User;
import com.service.base.BaseService;
import com.service.user.UserService;

public class BaseController<T> {
	@Autowired
	private UserService userService;
	private Class<T> clazz;
	String name=this.getPojoName();
	@Autowired
	protected BaseService<T> baseService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model){
		//获取当前用户
		User user=(User) request.getSession().getAttribute("user");
		user=userService.get(user);
		model.addAttribute("user",user);
		//String name=this.getPojoName();
		return "views/"+name+"/"+name+"List";
	}
	/**
	 * @Description: 通过泛型类得到类的名称
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月27日
	 */
	public String getPojoName(){
		Type genericSuperclass = getClass().getGenericSuperclass();
		if(genericSuperclass instanceof ParameterizedType){
			ParameterizedType parameterizedType=(ParameterizedType) genericSuperclass;
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			this.clazz=(Class<T>) actualTypeArguments[0];
		}else{
			this.clazz=(Class<T>) genericSuperclass;
		}
		String str=clazz.getCanonicalName();
		String[] str2=str.split("\\.");
		String str3=null;
		if(str2.length>0){
			str3=str2[str2.length-1].toLowerCase();
		}
		return str3;
	}
	/**
	 * @Description: 公共的跳转到添加页面
	 * @param @param entity
	 * @param @param request
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月27日
	 */
	@RequestMapping("/add")
	public String form(HttpServletRequest request,Model model){
		//String name=this.getPojoName();
		return "views/"+name+"/"+name+"Add";
	}
	/**
	 * @Description: 更新修改
	 * @param @param entity
	 * @param @param model
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author zhoudechao
	 * @date 2018年4月27日
	 */
	@RequestMapping("/edit")
	public String edit(T entity,Model model){
		entity=baseService.queryone(entity);
		model.addAttribute(name, entity);
		return "views/"+name+"/"+name+"Add";
	}
	
	@ResponseBody
	@RequestMapping("/deleteBatch")
	public String deleteBatch(Model model,String ids){
		String result="0";
		try {
			String[] idarr=ids.split(",");
			for (String id : idarr) {
				if(baseService.delete(Integer.parseInt(id))!=1){
					break;
				}
			}
			result="1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String getNowTime(){
		//得到long类型当前时间
		long l = System.currentTimeMillis();
		//new日期对象
		Date date = new Date(l);
		//转换提日期输出格式
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = dateFormat.format(date);
		return format;
	}
}
