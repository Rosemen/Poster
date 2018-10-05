package com.scau.rent.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 登录拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		//请求登录或注册,放行
		if(requestURI.endsWith("login.action")||requestURI.endsWith("regist.action"))
			return true;
		//用户已经登录
		if(request.getSession().getAttribute("user")!=null)
			return true;
		//否则重定向到登录页面
		response.sendRedirect(request.getContextPath()+"/user/toError.action");
		return false;
	}

}
