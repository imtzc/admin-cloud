package com.github.zuihou;

import com.alibaba.cloud.seata.feign.SeataFeignClientAutoConfiguration;
import com.github.zuihou.security.annotation.EnableLoginArgResolver;
import com.github.zuihou.validator.annotation.EnableFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author zuihou
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SeataFeignClientAutoConfiguration.class})
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(value = {
        "com.github.zuihou",
})
@Slf4j
@EnableLoginArgResolver
@EnableFormValidator
public class DemoServerApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(DemoServerApplication.class, args);
        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用 '{}' 运行成功! 访问连接:\n\t" +
                        "Swagger文档: \t\thttp://{}:{}/doc.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));

    }
}