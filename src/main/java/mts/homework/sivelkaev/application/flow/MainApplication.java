package mts.homework.sivelkaev.application.flow;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableProcessApplication("ProcessApplication")
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class MainApplication {
    public static void main(final String... args) throws Exception {
        SpringApplication.run(MainApplication.class, args);
    }
}
