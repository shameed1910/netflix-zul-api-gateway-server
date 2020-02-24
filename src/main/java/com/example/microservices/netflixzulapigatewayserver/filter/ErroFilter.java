package com.example.microservices.netflixzulapigatewayserver.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ErroFilter extends ZuulFilter {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String filterType() {
		return "error";
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
		HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
		logger.info("ErrorFilter: "+ String.format("response status is %d", response.getStatus()));
		return null;
	}

}
