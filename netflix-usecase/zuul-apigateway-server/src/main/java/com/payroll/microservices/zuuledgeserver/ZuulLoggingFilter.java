package com.payroll.microservices.zuuledgeserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author satadru.basu
 * Create a simple logging filter
 *
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter {
	
	private static final Logger log = LoggerFactory.getLogger(ZuulLoggingFilter.class);

	@Override
	public Object run() {

		RequestContext ctx= RequestContext.getCurrentContext();
		HttpServletRequest req = ctx.getRequest();
		log.info(req.getMethod().toString() +" request to " +req.getRequestURI().toString());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}



}
