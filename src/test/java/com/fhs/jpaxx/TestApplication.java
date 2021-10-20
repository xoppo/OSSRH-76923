package com.fhs.jpaxx;

//import com.blinkfox.fenix.EnableFenix;
import com.fhs.jpa.anno.EnableJpaAdvance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * 请看test包下的测试用例代码
 */
@EnableJpaAdvance
@SpringBootApplication(exclude = { RedisAutoConfiguration.class })
@EnableConfigurationProperties
public class TestApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(TestApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
