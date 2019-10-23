package com.cxk.cai.config;

import com.cxk.cai.shiro.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 喜闻乐见i
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建权限管理器对象
     *
     * @return 返回一个DefaultWebSecurityManager对象
     */
    @Bean
    public DefaultWebSecurityManager createDefaultWebSecurityManager(MyRealm myRealm) {
        return new DefaultWebSecurityManager(myRealm);
    }

    /**
     * 创建Shiro过滤器对象
     *
     * @param webSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean createShiroFilterFactoryBean(DefaultWebSecurityManager webSecurityManager) {
        //1、创建Shiro过滤器工厂对象
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //2、设置权限管理器对象
        factoryBean.setSecurityManager(webSecurityManager);
        //3、设置常用的3个URL页面
        //设置登录页面
        factoryBean.setLoginUrl("login.html");
        //设置登录成功之后跳转的页面
        factoryBean.setSuccessUrl("index.html");
        //设置未授权页面
        factoryBean.setUnauthorizedUrl("error.html");
        //4、设置拦截规则 anon：匿名访问 放行 谁都可以使用 authc:认证访问 必须登录才可以使用
        Map<String, String> map = new HashMap<>();
        map.put("/login.html", "anon");
        map.put("/user/login", "anon");
        //静态资源 放行
        map.put("/static/media/**", "anon");
        //全部拦截
        map.put("/*", "authc");
        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;
    }
}
