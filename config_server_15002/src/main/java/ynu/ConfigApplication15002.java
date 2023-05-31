package ynu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication15002 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication15002.class, args);
    }
}
