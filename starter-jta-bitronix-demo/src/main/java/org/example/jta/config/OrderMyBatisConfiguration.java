package org.example.jta.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

//============================OrderMyBatisConfiguration===================================
@Configuration
@MapperScan(basePackages = "org.example.jta.order.mapper", sqlSessionFactoryRef = "orderSqlSessionFactory")
public class OrderMyBatisConfiguration {

	@Autowired
	@Qualifier("orderBitronixDataSource")
	private  DataSource dataSource;
	
	@Bean("orderSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(
			@Qualifier("orderGlobalConfig") GlobalConfig globalConfig) throws Exception {

		MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();

		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/order/*.xml"));
		
		// ??????
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
	
		factoryBean.setPlugins(new Interceptor[] { interceptor });
		factoryBean.setDataSource(dataSource);

		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		
		configuration.setMapUnderscoreToCamelCase(true);
		// #????????????map??????????????????????????????
		configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
		
		configuration.setLogImpl(StdOutImpl.class);
		
		factoryBean.setConfiguration(configuration);
		factoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
		factoryBean.setGlobalConfig(globalConfig);
		return factoryBean.getObject();

	}

	@Bean(name = "orderGlobalConfig")
	public GlobalConfig globalConfig() {
		return new GlobalConfig();
	}
	
	@Bean
	public SqlSessionTemplate orderSqlSessionTemplate(
			@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
