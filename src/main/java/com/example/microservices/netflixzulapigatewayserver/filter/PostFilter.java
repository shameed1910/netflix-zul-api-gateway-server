package com.example.microservices.netflixzulapigatewayserver.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostFilter extends ZuulFilter {
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String filterType() {
		return "post";
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
		String responseData = null;
		RequestContext ctx = RequestContext.getCurrentContext();
		try (final InputStream responseDataStream = ctx.getResponseDataStream()) 
		{			   
			responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
			ctx.setResponseBody(responseData);
		}
		catch (IOException e) 
		{
			logger.warn("Error reading body",e);
		}
		logger.info("PostFileter: " + responseData);
		return null;
	}

}
