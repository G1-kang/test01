package com.test.dashboard.common.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.test.dashboard.common.interceptor.GradeInterceptor;
import com.test.dashboard.model.biz.DashMemberBiz;
import com.test.dashboard.model.biz.DashMemberBizImpl;
import com.test.dashboard.model.biz.WRuleBiz;
import com.test.dashboard.model.biz.WRuleBizImpl;



// 해당 파일이 설정 파일임을 가르키는 어노테이션
@Configuration

//해당 DispatcherServlet에서는 트랜잭션 설정을 한다.
//<tx:annotation-driven/> 대체
@EnableTransactionManagement

// properties 파일을 읽어주는 어노테이션
// classpath : main/resources/
// <context:property-placeholder location="classpath:./properties/*.properties"/> 대체
@PropertySources({ 
	@PropertySource("classpath:properties/db.properties")
})

// Mapper가 되는 클래스가 모여 있는 패키지 경로 ( mybatis_mapper.xml 대체)
// <mybatis-spring:scan base-package="com.test.mvc.model.dao" factory-ref="sqlSessionFactoryBean" template-ref="sqlSessionTemplate"/> 대체
@MapperScan("com.test.dashboard.model.dao")

// ApplicationContext.xml 설정 파일
public class ApplicationContent {
	
	private Logger logger = LoggerFactory.getLogger(ApplicationContent.class);
	
	
	@Autowired
	// 읽어들인 properties 파일들을 제어하기 위한 객체
	private Environment env;
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Bean 어노테이션은 외부 클래스를 개발자가 Component를 어노테이션을 사용해서 스프링 IOC 컨테이너에 등록하기 어려울 경우 사용한다.
	// Bean은 메소드에서 걸어서 해당 리턴 값으로 외부 클래스 타입의 객체 되돌려주는 식으로 만들고
	// Component는 클래스 위에 선언한다.
	@Bean
	/*
	여기선 common-dbcp2을 사용하여 BasicDataSource 빈 객체를 만들었다.
	<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource">
		<property name="driverClassName" value="${jdbc.driver}"/>
		<property name="url" value="${jdbc.url}"/>
		<property name="username" value="${jdbc.username}"/>
		<property name="password" value="${jdbc.password}"/>
	</bean>
	 */
	public BasicDataSource dataSource() {
		BasicDataSource bean = new BasicDataSource();
		bean.setDriverClassName(env.getProperty("jdbc.driver"));
		bean.setUrl(env.getProperty("jdbc.url"));
		bean.setUsername(env.getProperty("jdbc.username"));
		bean.setPassword(env.getProperty("jdbc.password"));
		return bean;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	<bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean" autowire="byType">
		<property name="typeAliasesPackage" value="com.test.mvc.model.dto"/>
	</bean>
	https://mybatis.org/spring/ko/factorybean.html 참조
	 */
	@Bean
	public SqlSessionFactoryBean sqlSession() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource());
		bean.setTypeAliasesPackage("com.test.dashboard.model.dto");
		
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		config.setJdbcTypeForNull(JdbcType.NULL);
		config.setCacheEnabled(false);
		bean.setConfiguration(config);
		
		return bean;
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	<bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg ref="sqlSessionFactoryBean"/>
	</bean> 
	*/
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() {
		SqlSessionTemplate bean = null;
		try {
			bean = new SqlSessionTemplate(sqlSession().getObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("[ERROR] : ApplicationContext > SqlSessionTemplate Error");
			e.printStackTrace();
		}
		return bean;
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
  		<constructor-arg ref="dataSource" />
	</bean>
	https://mybatis.org/spring/ko/transactions.html 참조
	 */
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	
	
	
}
