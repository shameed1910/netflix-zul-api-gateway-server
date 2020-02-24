package com.example.microservices.netflixzulapigatewayserver.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class RouteFilter extends ZuulFilter {
	private org.slf4j.Logger logger=LoggerFactory.getLogger(this.getClass());

	@Override
	public String filterType() {

		return "route";
	}
	
	@Override
	public boolean shouldFilter() {

		return true;
	}

	@Override
	public int filterOrder() {

		return 1;
	}

	

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request=context.getRequest();
		logger.info("Route filter->{} request uri::"+ request.getMethod(), request.getRequestURL().toString());
		return null;
	}

}
