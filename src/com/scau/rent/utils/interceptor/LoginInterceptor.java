package com.scau.rent.utils.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * ��¼������
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
		//�����¼��ע��,����
		if(requestURI.endsWith("login.action")||requestURI.endsWith("regist.action"))
			return true;
		//�û��Ѿ���¼
		if(request.getSession().getAttribute("user")!=null)
			return true;
		//�����ض��򵽵�¼ҳ��
		response.sendRedirect(request.getContextPath()+"/user/toError.action");
		return false;
	}

}
