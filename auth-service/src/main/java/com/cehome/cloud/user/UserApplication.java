package com.cehome.cloud.user;

import com.cehome.cache.CacheProvider;
import com.cehome.utils.config.DefaultConfigClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description: TODO
 * Created by hyl on 2019/07/05/ 18:12
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.cehome.cloud"})
@ComponentScan("com.cehome.cloud")
@EnableAsync
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = DefaultConfigClient.startSpringApplication(UserApplication.class);
        springApplication.run(args);
    }

    @Bean
    public CacheProvider createCacheProvider() {
        return new com.cehome.cache.redis.RedisCacheProvider();
    }
}
