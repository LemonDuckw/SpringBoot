package ynu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import ynu.loadBalanced.CustomLoadBalancedConfig;

@SpringBootApplication
//开启Spring Cloud Feign的支持功能
@EnableFeignClients
@LoadBalancerClient(name = "provider-server",configuration = CustomLoadBalancedConfig.class)
public class ConsumerApplication12000 {
    /*
     * @LoadBalanced , 是ribbon提供的负载均衡注解。
     * 让RestTemplate在请求时拥有客户端负载均衡的能力
     */
    //@LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication12000.class, args);
    }
}
