package com.itheima.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class Interceptor1 implements HandlerInterceptor{


	//注入userService
//    @Autowired
//    private UserService userService;
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("方法前 1");
		//判断用户是否登陆  如果没有登陆  重定向到登陆页面   不放行   如果登陆了  就放行了
		// URL  http://localhost:8080/springmvc-mybatis/login.action
		//URI /login.action
//		String requestURI = request.getRequestURI();
//		String username = (String) request.getSession().getAttribute("USER_SESSION");
//		String password=(String) request.getSession().getAttribute("User_Password");
//		if(!requestURI.contains("/login")){
//			
//			if(userService.login(username, password)==null) {
//				response.sendRedirect(request.getContextPath() + "/login.action");
//				return false;
//			}
//		}
		return true;
			
	}
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("方法后 1");
		
	}
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("页面渲染后 1");
		
	}



}
