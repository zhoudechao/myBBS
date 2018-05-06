package com.controller.register;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.authorization.IgnoreSecurity;
import com.authorization.TokenManager;
import com.model.buser.Buser;
import com.service.buser.BuserService;
import com.util.StringUtils;
    
@RestController
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
	 * @created 2017年7月4日 下午4:53:58     
	 */
	@RequestMapping(value="/login",method = RequestMethod.POST)
	@IgnoreSecurity
	public Map<String, Object> login(@RequestBody Buser buser, HttpServletResponse response) {
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
			response.addCookie(cookie);
			Cookie cookie2=new Cookie("userName", queryone.getUserName());
			response.addCookie(cookie2);
			map.put("status", 0);
			map.put("msg", "登录成功！");
			int userId=queryone.getUserId();
			map.put("userId", userId);
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
	@RequestMapping(method = RequestMethod.DELETE)
	@IgnoreSecurity
	public Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
		String token = request.getHeader(StringUtils.DEFAULT_TOKEN_NAME);
		tokenManager.deleteToken(token);
		log.debug("Logout Success...");
		map.put("status", 1);
		map.put("msg", "注销成功！");
		return map;
	}
}
