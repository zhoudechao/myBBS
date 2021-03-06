package com.controller.register;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.IgnoreSecurity;
import com.authorization.TokenManager;
import com.model.buser.Buser;
import com.service.buser.BuserService;
import com.util.StringUtils;
    
@Controller
@RequestMapping("/tokens")
public class TokenController {
	@Autowired
	private BuserService buserService;
	@Autowired
	private TokenManager tokenManager;
	private static final Logger log = Logger.getLogger(TokenController.class);

	/*public TokenManager getTokenManager() {
		return tokenManager;
	}
	@Resource(name = "tokenManager")
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}*/

	  
	/**     
	 * @description 登录处理
	 * @author rico       
	 * @throws UnsupportedEncodingException 
	 * @created 2017年7月4日 下午4:53:58     
	 */
	@ResponseBody
	@RequestMapping(value="/login",method = RequestMethod.POST)
	@IgnoreSecurity
	public Map<String, Object> login(Buser buser, HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> map=new HashMap<String, Object>();
		Buser queryone=null;
		if(buser.getUserName()!=null){
			queryone= buserService.queryone(buser);
		}
		if (queryone!=null) {
			String token = tokenManager.createToken(queryone.getUserName());
			log.debug("**** Generate Token **** : " + token);
			Cookie cookie = new Cookie(StringUtils.DEFAULT_TOKEN_NAME, token);
			log.debug("Write Token to Cookie and return to the Client : " + cookie.toString());
			cookie.setPath("/");
			response.addCookie(cookie);
			Cookie cookie2=new Cookie("userId",String.valueOf(queryone.getUserId()));
			Cookie cookie3=new Cookie("userName",URLEncoder.encode(queryone.getUserName(), "utf-8"));
			cookie2.setPath("/");
			cookie3.setPath("/");
			response.addCookie(cookie2);
			response.addCookie(cookie3);
			map.put("status", 0);
			map.put("msg", "登录成功！");
			//int userId=queryone.getUserId();
			//map.put("userId", userId);
			//map.put("userName", queryone.getUserName());
			return map;
		}else{
			map.put("status", 1);
			map.put("msg", "登录失败！");
		}
		return map;
	}

	/**     
	 * @description 登出处理
	 * @author rico       
	 * @created 2017年7月4日 下午4:53:58     
	 */
	@ResponseBody
	@RequestMapping(value="/logout",method = RequestMethod.DELETE)
	@IgnoreSecurity
	public Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
		String token = request.getHeader(StringUtils.DEFAULT_TOKEN_NAME);
		if(token!=null){
			tokenManager.deleteToken(token);
			log.debug("Logout Success...");
			map.put("status", 1);
			map.put("msg", "注销成功！");
		}else{
			map.put("status", 0);
			map.put("msg", "注销失败！");
		}
		return map;
	}
}
