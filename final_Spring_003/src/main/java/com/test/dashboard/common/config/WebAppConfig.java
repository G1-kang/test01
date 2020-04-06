package com.test.dashboard.common.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.test.dashboard.common.filter.HttpRequestWrapperFilter;
import com.test.dashboard.common.filter.LogFilter;

// Web.xml 파일 Class화
// WebApplicationInitializer implements 필요
public class WebAppConfig implements WebApplicationInitializer {
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// TODO WebAppConfig init
		
		// ServletConext : web application 설정 객체 ( web.xml )
		
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		/*
	 	spring IOC 전역 컨테이너 객체 생성
		<context-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/ApplicationContext.xml</param-value>
		</context-param>
		 */
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		// 전역 컨테이너 설정을 저장해놓은 클래스를 컨테이너 객체에 등록
		applicationContext.register(ApplicationContent.class);		
		// 객체 생성 끝

		// 스프링 리스너에 컨테이너 등록
		servletContext.addListener(new ContextLoaderListener(applicationContext));

		////////////////////////////////////////////////////////////////////////////////////////////////
		
		/*
		DispatcherServlet 컨테이너 객체 생성
		<servlet>
		<servlet-name>appServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
		</servlet>
		 */
		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		
		// dispatcherServlet 설정 클래스가 모여 있는 있는 패키지 설정
		dispatcherServlet.setConfigLocations("com.test.dashboard.common.config.dispatcherservlet");
		
		// DispatcherServlet 설정, Spring에 등록 및 Servlet 제어 객체 생성
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("appServlet", new DispatcherServlet(dispatcherServlet));
		
		/*
		Servlet 제어 객체를 통해 매핑 설정
		<servlet-mapping>
			<servlet-name>appServlet</servlet-name>
			<url-pattern>/</url-pattern>
		</servlet-mapping>
		 */
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Filter 설정
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter("UTF-8",true));
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
		
		
		FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
		hiddenHttpMethodFilter.addMappingForUrlPatterns(null, true,"/*");
		
		
		FilterRegistration.Dynamic logFilter = servletContext.addFilter("logFilter", new LogFilter());
		logFilter.addMappingForUrlPatterns(null, true, "/*");
		
		FilterRegistration.Dynamic httpRequestWrapperFilter = servletContext.addFilter("httpRequestWrapperFilter", new HttpRequestWrapperFilter());
		httpRequestWrapperFilter.addMappingForUrlPatterns(null, true, "/dashboard/board/*");

	}
	
	
	
}
