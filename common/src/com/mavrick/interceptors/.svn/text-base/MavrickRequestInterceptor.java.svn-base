package com.mavrick.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class MavrickRequestInterceptor extends HandlerInterceptorAdapter {
	Logger logger = Logger.getLogger(MavrickRequestInterceptor.class);
	
@Override
public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
	logger.info("Incoming request for logout...");
return super.preHandle(request, response, handler); 

}

@Override
public void postHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
	super.postHandle(request, response, handler, modelAndView);
	logger.info("Post logout in validation session..");
	request.getSession().invalidate();
}




}
