package org.example.jta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yuanct
 * @since 1.2
 */
@EnableTransactionManagement(proxyTargetClass = true)  // 启注解事务管理，等同于xml配置方式的
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootJtaBitronixApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootJtaBitronixApplication.class, args);
    }
}
