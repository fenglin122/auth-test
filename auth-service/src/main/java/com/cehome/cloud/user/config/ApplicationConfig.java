package com.cehome.cloud.user.config;

import com.cehome.cache.redis.RedisCacheProvider;
import com.cehomex.spring.mybatis.ReadWriteInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Created by hyl on 2019/07/05/ 18:12
 */
@Configuration
public class ApplicationConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    private DataSource defaultDataSource;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    private static final String MAPPER_PACKAGE = "classpath*:com/cehome/cloud/user/mapper/*.xml";

    /**
     * @return SqlSessionTemplate
     * @throws
     * @Title: readSqlSessionTemplate
     * @Description: 读库的sqlSession模板
     * @author sun_jason
     */
    @Bean(name = "readSqlSessionTemplate")
    @ConfigurationProperties(prefix = "datasource.read")
    public SqlSessionTemplate readSqlSessionTemplate() {
        PoolProperties properties = getPoolProperties();
        org.apache.tomcat.jdbc.pool.DataSource readDataSource = new org.apache.tomcat.jdbc.pool.DataSource(properties);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        SqlSessionFactory sessionFactory = null;
        try {
            readDataSource.createPool();
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(readDataSource);
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            factoryBean.setMapperLocations(resolver.getResources(MAPPER_PACKAGE));
            factoryBean.setPlugins(new Interceptor[]{new ReadWriteInterceptor()});
            sessionFactory = factoryBean.getObject();
        } catch (Exception e) {
            log.error("--------------------------mybatis init error");
            e.printStackTrace();
        } finally {
            context.close();
        }
        return new SqlSessionTemplate(sessionFactory);
    }

    private PoolProperties getPoolProperties() {
        PoolProperties properties = new PoolProperties();
        properties.setTestOnBorrow(true);
        properties.setValidationQuery("select 1");
        properties.setTestWhileIdle(true);
        properties.setDriverClassName(dataSourceConfig.getDriverClassName());
        properties.setUrl(dataSourceConfig.getUrl());
        properties.setUsername(dataSourceConfig.getUsername());
        properties.setPassword(dataSourceConfig.getPassword());
        properties.setInitialSize(dataSourceConfig.getInitialSize());
        properties.setMaxActive(dataSourceConfig.getMaxActive());
        properties.setMaxIdle(dataSourceConfig.getMaxIdle());
        properties.setMaxWait(dataSourceConfig.getMaxWait());
        properties.setMinIdle(dataSourceConfig.getMinIdle());
        return properties;
    }

    /**
     * @return SqlSessionTemplate
     * @throws
     * @Title: writeSqlSessionTemplate
     * @Description: 默认写库的sqlSession模板
     * @author sun_jason
     */
    @Bean(name = "writeSqlSessionTemplate")
    public SqlSessionTemplate writeSqlSessionTemplate() {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(defaultDataSource);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        SqlSessionFactory sessionFactory = null;
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            factoryBean.setMapperLocations(resolver.getResources(MAPPER_PACKAGE));
            factoryBean.setPlugins(new Interceptor[]{new ReadWriteInterceptor()});
            sessionFactory = factoryBean.getObject();
        } catch (Exception e) {
            log.error("--------------------------mybatis init error");
            e.printStackTrace();
        } finally {
            context.close();
        }

        return new SqlSessionTemplate(sessionFactory);
    }

    @Bean(name = "redisCacheProviderStr")
    public RedisCacheProvider createCacheProviderStr(){
        return new RedisCacheProvider("str");
    }


//    /**
//     * @return ServletListenerRegistrationBean<CMQInitialListener>
//     * @throws
//     * @Title: servletListenerRegistrationBean
//     * @Description: 添加mq监听
//     * @author sun_jason
//     */
//    @Bean
//    public ServletListenerRegistrationBean<CMQInitialListener> servletListenerRegistrationBean() {
//        ServletListenerRegistrationBean<CMQInitialListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<CMQInitialListener>();
//        servletListenerRegistrationBean.setListener(new CMQInitialListener());
//        return servletListenerRegistrationBean;
//    }
}
