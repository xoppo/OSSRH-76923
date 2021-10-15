package com.fhs.jpa;

//import com.blinkfox.fenix.EnableFenix;
import com.fhs.jpa.anno.EnableJpaExtNativeQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * 请看test包下的测试用例代码
 */
@SpringBootApplication(exclude = { RedisAutoConfiguration.class })
@EnableConfigurationProperties
@ComponentScan({"com.fhs"})
@EnableJpaExtNativeQuery(basePackages = "com.fhs.jpa.test.dao")
public class TestApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(TestApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
