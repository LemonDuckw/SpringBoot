package ynu.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ynu.entity.CommentResult;
import ynu.entity.User;
import ynu.feign.UserFeignClient;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private DiscoveryClient discoveryClient;

    //注入Fegin接口（@EnableFeignClients自动扫描@FeignClient注解）
    @Autowired
    private UserFeignClient userFeignClient;
    @GetMapping("/getUserById/{userId}")
//    //@RateLimiter(name = "rate-limiterA",fallbackMethod = "fallback")
//    @Bulkhead(name = "bulkheadA",fallbackMethod = "fallback",type = Bulkhead.Type.THREADPOOL)
//    public CompletableFuture<User> getUserById(@PathVariable Integer userId)throws InterruptedException{
//        System.out.println("进入方法");
//        Thread.sleep(10000L);
//        CompletableFuture<User> result=CompletableFuture.supplyAsync(()->{
//            return userFeignClient.getUserById(userId).getResult();
//        });
//
//        System.out.println("离开方法");
//        return  result;
//    }
//
//    public CompletableFuture<User> fallback(Integer userId,Throwable e){
//        e.printStackTrace();
//        System.out.println("fallback已经调用！");
//
//        CompletableFuture<User> result=CompletableFuture.supplyAsync(()->{
//            return new CommentResult<>(400,"当前用户服务不正常，请稍后再试！",new User()).getResult();
//        });
//        return result;
//    }

    @CircuitBreaker(name = "backendA",fallbackMethod = "fallback")
    public CommentResult getUserById(@PathVariable("userId") Integer userId) throws InterruptedException{
        System.out.println("进入方法");

        CommentResult list=userFeignClient.getUserById(userId);
        System.out.println("离开方法");
        return list;
        //使用微服务名替换IP地址和端口
//        CommentResult result = restTemplate.getForObject(
//                "http://provider-server/user/getUserById/"+userId, CommentResult.class);
//        return result;
//        //使用Fegin接口进行服务调用
//        return userFeignClient.getUserById(userId);
    }

    public CommentResult<User> fallback(Integer userId,Throwable e){
        e.printStackTrace();
        System.out.println("fallback已经调用！");

        CommentResult<User> result=new CommentResult<>(

                400,"当前用户服务不正常，请稍后再试",new User()
        );
        return result;
    }
    @GetMapping("/hello")
    public String hello(){

//        List<ServiceInstance> instanceList = discoveryClient.getInstances("provider-server");
//
//        for(ServiceInstance si:instanceList){
//            System.out.println(si.getHost()+"\t"+si.getPort());
//        }
//
//        ServiceInstance instance = instanceList.get(0);

        return restTemplate.getForObject(
//                "http://"+instance.getHost()+":"+instance.getPort()+"/user/hello",
                "http://provider-server/user/hello",
                String.class
        );
    }

    @GetMapping("/addCart/{userId}")
    public CommentResult<User> addCart(@PathVariable Integer userId){

//        List<ServiceInstance> instanceList = discoveryClient.getInstances("provider-server");
//
//        for(ServiceInstance si:instanceList){
//            System.out.println(si.getHost()+"\t"+si.getPort());
//        }
//
//        ServiceInstance instance = instanceList.get(0);


        CommentResult<User> result = restTemplate.getForObject(
                //"http://"+instance.getHost()+":"+instance.getPort()+"/user/getUserById/"+userId,
                "http://localhost:11000/user/getUserById/"+userId,
                CommentResult.class
        );
        return result;
    }

    @RequestMapping("/updateUser")
    public String methodPut(Integer userId,String name,String newName){
        restTemplate.put(
                "http://provider-server/user/updateUser?userId="+userId+"&"+"name="+"&"+"newName="+newName,
                userId,name,newName,CommentResult.class
        );
        return name+"用户改名为"+newName;
    }

    @PostMapping("/createUser")
    public CommentResult<User> methodPost(Integer userId,String name){
        CommentResult<User> result=restTemplate.postForObject(
                "http://provider-server/user/createUser?userId="+userId+"&"+"name="+name, userId,
                CommentResult.class
        );
        return result;
    }

    @DeleteMapping("/deleteUser/{userId}")
    public String methodDelete(@PathVariable Integer userId){
        restTemplate.delete(
                "http://provider-server/user/deleteUser/"+userId
        );
        return "用户"+userId+"删除成功";
    }

}
