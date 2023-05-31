package ynu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import ynu.entity.CommentResult;
import ynu.entity.User;

@FeignClient(name="provider-server")
public interface UserFeignClient {
    //配置需要调用的挂号服务接口。与UserController中的方法定义一致
    @GetMapping("/user/getUserById/{userId}")
    public CommentResult<User> getUserById(@PathVariable("userId") Integer userId);

    @RequestMapping(value = "/user/hello", method = RequestMethod.GET)
    public String Hello();

    @RequestMapping(value = "/user/restful.post", method = RequestMethod.POST)
    public CommentResult<User> methodPost(@RequestParam("userId") Integer userId, @RequestParam("name") String name);

    @RequestMapping(value = "/user/restful.put", method = RequestMethod.PUT)
    public CommentResult<User> methodPut(@RequestParam("userId") Integer userId,
                                         @RequestParam("name") String name, @RequestParam("changeName") String changeName);

    @RequestMapping(value = "/user/restful.delete/{userId}", method = RequestMethod.DELETE)
    public CommentResult<User> methodDelete(@PathVariable("userId") Integer userId);

}
