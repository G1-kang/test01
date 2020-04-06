package com.test.dashboard.common.config.dispatcherservlet;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.test.dashboard.common.interceptor.GradeInterceptor;
import com.test.dashboard.common.interceptor.LoginInterceptor;

@Configuration
// 해당 클래스는 Spring MVC 설정 클래스임을 가르킨다. implements로 WebMvcConfigurer 해주어야 한다.
// <annotation-driven /> 대체
@EnableWebMvc
// Component, Bean, Autowired 어노테이션 설정되어 있는 메소드 및 클래스를 읽어 IOC 컨테이너에 Bean 객체 등록
// <context:component-scan base-package="com.test.mvc" /> 대체
@ComponentScan("com.test.dashboard")
// DispatcherServlet 설정 파일
public class DashBoardDispatcherServlet implements WebMvcConfigurer {

	
	
	/*
	<resources mapping="/resources/**" location="/resources/" />
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO DispatcherServlet ResourceHandler
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
	}
	
	
	/*
	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
		bean.setViewClass(JstlView.class);
		return bean;
	}
	
	@Bean
	public GradeInterceptor beanGradeInterceptor() {
		return new GradeInterceptor();
	}
	
	/*
	<interceptors>
		<interceptor>
			<mapping path="/*"/>
			<beans:bean class="com.test.mvc.common.interceptor.LoginInterceptor"></beans:bean>
		</interceptor>
	</interceptors>
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/resources/**");
		registry.addInterceptor(beanGradeInterceptor()).addPathPatterns("/widget/**");
		
	}

	
}
