package com.cehome.cloud.user;

import com.cehome.utils.config.DefaultConfigClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@MapperScan("com.cehome.cloud.user.dao")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = DefaultConfigClient.startSpringApplication(UserApplication.class);
        springApplication.run(args);
    }
}
