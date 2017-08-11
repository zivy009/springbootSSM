package com.zivy009.demo.springbootshirodwz.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.zivy009.demo.springbootshirodwz.config.properties.ComProperties;
import com.zivy009.demo.springbootshirodwz.shiro.ShiroDbRealm;

/**
 * shiro权限管理的配置
 *
 * @author
 * @date 2016年11月14日 下午3:03:44
 */
@Configuration
// @Order(1)
public class ShiroConfig {
    private Integer sessionInvalidateTime = 30 * 60; // session 失效时间（默认为30分钟
                                                     // 单位：秒）
    private Integer sessionValidationInterval = 15 * 60; // session
                                                         // 验证失效时间（默认为15分钟 单位：秒）

    /**
     * 安全管理器
     */
    // @Bean
    // public DefaultWebSecurityManager securityManager(CookieRememberMeManager
    // rememberMeManager, CacheManager cacheShiroManager,
    // DefaultWebSessionManager defaultWebSessionManager) {
    // DefaultWebSecurityManager securityManager = new
    // DefaultWebSecurityManager();
    // securityManager.setRealm(this.shiroDbRealm());
    // securityManager.setCacheManager(cacheShiroManager);
    // securityManager.setRememberMeManager(rememberMeManager);
    // securityManager.setSessionManager(defaultWebSessionManager);
    // return securityManager;
    // }
    @Bean
    public DefaultWebSecurityManager securityManager(ShiroDbRealm realm, CookieRememberMeManager rememberMeManager, CacheManager cacheShiroManager,
            DefaultWebSessionManager defaultWebSessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        System.out.println("securityManager bean init ***************************");
        securityManager.setSessionManager(defaultWebSessionManager);
        securityManager.setCacheManager(cacheShiroManager);
        securityManager.setRememberMeManager(rememberMeManager);
        securityManager.setSessionManager(defaultWebSessionManager);

        return securityManager;
    }

    @Bean
    public IniRealm makeIniRealm() {

        IniRealm iniRealm = new IniRealm("classpath:shiro_users.ini");

        return iniRealm;
    }

   
    /**
     * session管理器credentialsMatcher
     */
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(CacheManager cacheShiroManager, ComProperties comProperties) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheShiroManager);
        sessionManager.setSessionValidationInterval(sessionValidationInterval * 1000);
        sessionManager.setGlobalSessionTimeout(sessionInvalidateTime * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("shiroCookie");
        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public CacheManager getCacheShiroManager(EhCacheManagerFactoryBean ehcache) {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManager(ehcache.getObject());
        return ehCacheManager;
    }

//    /**
//     * 项目自定义的Realm
//     */
//     @Bean
//     public ShiroDbRealm shiroDbRealm() {
//     return new ShiroDbRealm();
//     }

    /**
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    /**
     * 记住密码Cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);// 7天
        return simpleCookie;
    }

    /**
     * Shiro的过滤器链
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        /**
         * 默认的登陆访问url
         */
        shiroFilter.setLoginUrl("/login");

        /**
         * 登陆成功后跳转的url
         */
        shiroFilter.setSuccessUrl("/");
        /**
         * 没有权限跳转的url
         */
         
       shiroFilter.setUnauthorizedUrl("/unauthorized");// 只有在authorizationFilter 下好用。否则用异常映射view
        /**
         * 配置shiro拦截器链
         *
         * anon 不需要认证 authc 需要认证 user 验证通过或RememberMe登录的都可以
         *
         */
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("/logout", "logout");
        /******* 静态资源 *********************/
        hashMap.put("/static/**", "anon");
        hashMap.put("/dwz/**", "anon");
        /******* 登录相关 *********************/
        hashMap.put("/test", "anon");
        hashMap.put("/login**", "anon");
        hashMap.put("/login/**", "anon");
        /******* 其他 *********************/
        // hashMap.put("/global/sessionError", "anon");
       hashMap.put("/**", "user");
        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }

    /**
     * 在方法中 注入 securityManager,进行代理控制
     */
//    @Bean
//    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
//        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
//        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
//        bean.setArguments(new Object[] { securityManager });
//        
//        return bean;
//    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     */
    @Bean
    @DependsOn(value = "lifecycleBeanPostProcessor") // 依赖其他bean的初始化
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 注解拦截用
     * 
     * @author zivy
     * @date 2017年7月25日
     * @describe
     * @param securityManager
     * @return
     *
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);

        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 配置不同异常的错误页面
     * 
     *@author zivy
     *@date 2017年7月25日
     *@describe
     *@return
     *
     */
    @Bean
    public SimpleMappingExceptionResolver makeSimpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver simpleMappingExceptionResolver=new SimpleMappingExceptionResolver();
        Properties properties=new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "/unauthorized");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }
    /**
     * 限制同一账号登录同时登录人数控制
     * 
     * @return
     */
    // public KickoutSessionControlFilter kickoutSessionControlFilter() {
    // KickoutSessionControlFilter kickoutSessionControlFilter = new
    // KickoutSessionControlFilter();
    // // 使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
    // // 这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
    // // 也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
    // // kickoutSessionControlFilter.setCacheManager(cacheManager());
    // // 用于根据会话ID，获取会话进行踢出操作的；
    // // kickoutSessionControlFilter.setSessionManager(sessionManager());
    // // 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
    // kickoutSessionControlFilter.setKickoutAfter(false);
    // // 同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
    // kickoutSessionControlFilter.setMaxSession(1);
    // // 被踢出后重定向到的地址；
    // kickoutSessionControlFilter.setKickoutUrl("/kickout");
    // return kickoutSessionControlFilter;
    // }

}
