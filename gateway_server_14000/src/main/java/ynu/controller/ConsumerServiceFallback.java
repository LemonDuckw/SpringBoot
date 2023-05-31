package ynu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ynu.entity.CommentResult;
import ynu.entity.User;

@RestController
public class ConsumerServiceFallback {
    @RequestMapping(value = "/fallback",method = RequestMethod.GET)
    public CommentResult GetCommentResult(){
        System.out.println("由于ConsumerService异常，进行服务降级响应");
        return new CommentResult<>(
                403,"由于ConsumerService异常，进行服务降级响应",new User());
    }
}
