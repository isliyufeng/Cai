package com.cxk.cai.config;

import com.alibaba.druid.support.http.StatViewFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 喜闻乐见i
 */
@Configuration
public class DruidConfig {

    /**
     * 注册Druid的过滤器 实现SQL的监控 FilterRegistrationBean过滤器注册对象 实现过滤器的注册
     * @return 返回一个过滤器注册对象
     */
    @Bean
    public FilterRegistrationBean createFilterRegistrationBean(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        // 设置过滤器
        registrationBean.setFilter(new StatViewFilter());
        // 设置过滤路径
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    /**
     * 注册Servlet 实现可视化页面的访问
     * @return 返回一个Servlet注册对象
     */
    @Bean
    public ServletRegistrationBean createServletRegistrationBean(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        // 设置Servlet对象
        registrationBean.setServlet(new StatViewServlet());
        // 设置触发路径
        registrationBean.addUrlMappings("/druid/*");
        return registrationBean;
    }
}
