package com.pb.gaetest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SpringTestController extends ApplicationObjectSupport implements Controller {
	private SimpleServiceInterface service;
	
	public void setSimpleService(final SimpleServiceInterface ss) {
		service = ss;
	}
	
	public ModelAndView handleRequest(HttpServletRequest req, final HttpServletResponse resp) throws Exception {
		logger.info("Hello from the AOS logger object");
		resp.setContentType("text/plain");
		final SimpleEntity simpleEntity = new SimpleEntity();
		service.addItem(simpleEntity);
		List results = service.allItems();
		for ( Object entity : results ) {
			resp.getWriter().println("item = " + ((SimpleEntity)entity).getId());
		}
		logger.info(simpleEntity);
		return null;
	}
}
