package org.example.jta.config;
// bitronix ........

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.bitronix.PoolingDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
public class BitronixDataSourceConfig {
	
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.order")
    public DruidXADataSource orderDataSource(){
        return DataSourceBuilder.create().type(DruidXADataSource.class).build();
    }
    
    @Bean
    public PoolingDataSourceBean orderBitronixDataSource(DruidXADataSource orderDataSource){
    	PoolingDataSourceBean ds = new PoolingDataSourceBean();
    	ds.setDataSource(orderDataSource);
    	ds.setUniqueName("orderBitronixDataSource");
    	return ds;
    }
 
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.stock")
    public DruidXADataSource stockDataSource(){
    	DruidXADataSource dataSource = 
    			DataSourceBuilder.create().type(DruidXADataSource.class).build();
    	return dataSource;
    }
    
    @Bean
    public PoolingDataSourceBean stockBitronixDataSource(DruidXADataSource stockDataSource){
    	PoolingDataSourceBean ds = new PoolingDataSourceBean();
    	ds.setDataSource(stockDataSource);
    	ds.setUniqueName("stockBitronixDataSource");
    	return ds;
    }
 
 
    /**
     * 注入事物管理器
     * @return
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager () {
    	BitronixTransactionManager transactionManager = TransactionManagerServices.getTransactionManager();
        return new JtaTransactionManager(transactionManager,transactionManager);
    } 
}

