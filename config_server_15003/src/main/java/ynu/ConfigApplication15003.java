package ynu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication15003 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication15003.class, args);
    }
}
