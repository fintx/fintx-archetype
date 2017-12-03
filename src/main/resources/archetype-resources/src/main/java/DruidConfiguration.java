package com.beebank.payment;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

import javax.sql.DataSource;


@Configuration
@EnableAutoConfiguration
// @ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class DruidConfiguration {

    // private String url;
    // private String username;
    // private String password;
    // private String driverClassName;
    // private int initialSize;
    // private int minIdle;
    // private int maxActive;
    // private int maxWait;
    // private int timeBetweenEvictionRunsMillis;
    // private int minEvictableIdleTimeMillis;
    // private String validationQuery;
    // private boolean testWhileIdle;
    // private boolean testOnBorrow;
    // private boolean testOnReturn;
    // private boolean poolPreparedStatements;
    // private int maxPoolPreparedStatementPerConnectionSize;
    // private String filters;
    // private String connectionProperties;
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @ConditionalOnClass(DruidDataSource.class)
    @ConditionalOnProperty(prefix = "spring.datasource", name = "url")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        // datasource.setUrl(url);
        // datasource.setUsername(username);
        // datasource.setPassword(password);
        // datasource.setDriverClassName(driverClassName);
        //
        // //configuration
        // datasource.setInitialSize(initialSize);
        // datasource.setMinIdle(minIdle);
        // datasource.setMaxActive(maxActive);
        // datasource.setMaxWait(maxWait);
        // datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // datasource.setValidationQuery(validationQuery);
        // datasource.setTestWhileIdle(testWhileIdle);
        // datasource.setTestOnBorrow(testOnBorrow);
        // datasource.setTestOnReturn(testOnReturn);
        // datasource.setPoolPreparedStatements(poolPreparedStatements);
        // datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        // try {
        // datasource.setFilters(filters);
        // } catch (SQLException e) {
        // System.err.println("druid configuration initialization filter: " + e);
        // }
        // datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }

    /**
     * 注册一个StatViewServlet
     * 
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet() {
        // org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        // 添加初始化参数：initParams

        // 白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        // IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        // 登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        // 是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     * 
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());

        // 添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        // 添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
