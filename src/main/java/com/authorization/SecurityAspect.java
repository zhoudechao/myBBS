package com.authorization;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.exception.TokenException;
import com.util.StringUtils;

import com.util.WebContextUtil;

/**
 * Title:安全检查切面(是否登录检查) 
 * Description: 通过验证Token维持登录状态
 * 
 * @author rico
 * @created 2017年7月4日 下午4:32:34
 */
@Component
@Aspect
public class SecurityAspect {

	/** Log4j日志处理(@author: rico) */
	private static final Logger log = Logger.getLogger(SecurityAspect.class);

	private TokenManager tokenManager;

	@Resource(name = "tokenManager")
	public void setTokenManager(TokenManager tokenManager) {
		this.tokenManager = tokenManager;
	}

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
		// 从切点上获取目标方法
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		//从切点上获取目标方法的类
		Object target = pjp.getTarget();
		String leiName=target.toString();
		String url=leiName.substring(0, leiName.indexOf("@"));
		Class<?> forName = Class.forName(url);
		//判断目标方法的类上面是否有IgnoreSecurityType注解
		if(forName.isAnnotationPresent(IgnoreSecurityType.class)){
			return pjp.proceed();
		}
		
		log.debug("methodSignature : " + methodSignature);
		Method method = methodSignature.getMethod();
		
		log.debug("Method : " + method.getName() + " : "
				+ method.isAnnotationPresent(IgnoreSecurity.class));
		// 若目标方法忽略了安全性检查,则直接调用目标方法
		if (method.isAnnotationPresent(IgnoreSecurity.class)) {
			return pjp.proceed();
		}
		
		// 从 request header 中获取当前 token
		String token = WebContextUtil.getRequest().getHeader(
				StringUtils.DEFAULT_TOKEN_NAME);
		// 检查 token 有效性
		if (!tokenManager.checkToken(token)) {
			String message = String.format("token [%s] is invalid", token);
			log.debug("message : " + message);
			throw new TokenException(message);
		}
		// 调用目标方法
		return pjp.proceed();
	}
}
