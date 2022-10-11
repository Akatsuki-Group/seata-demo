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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

//========================StockMyBatisConfiguration======================
@Configuration
@MapperScan(basePackages = "org.example.jta.stock.mapper", sqlSessionFactoryRef = "stockSqlSessionFactory")
public class StockMyBatisConfiguration {


	@Bean("stockSqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory(
			@Qualifier("stockBitronixDataSource") DataSource dataSource,
			@Qualifier("stockGlobalConfig") GlobalConfig globalConfig
			) throws Exception {

		MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources("classpath:mapper/stock/*.xml"));
		
		// 分页
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
	
		factoryBean.setPlugins(new Interceptor[] { interceptor });
		factoryBean.setDataSource(dataSource);

		MybatisConfiguration configuration = new MybatisConfiguration();
		configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		
		configuration.setMapUnderscoreToCamelCase(true);
		// #开启返回map结果集的下划线转驼峰
		configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
		
		configuration.setLogImpl(StdOutImpl.class);
		factoryBean.setConfiguration(configuration);
		factoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
		factoryBean.setGlobalConfig(globalConfig);
		return factoryBean.getObject();

	}

	@Bean(name = "stockGlobalConfig")
	public GlobalConfig globalConfig() {
		GlobalConfig globalConfig = new GlobalConfig();
		return globalConfig;
	}
	
	public SqlSessionTemplate stockSqlSessionTemplate(
			@Qualifier("stockSqlSessionFactory")SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
		return template;
	}
}