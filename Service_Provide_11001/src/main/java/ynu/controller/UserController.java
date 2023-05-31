package ynu.controller;

import org.springframework.web.bind.annotation.*;
import ynu.entity.CommentResult;
import ynu.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/hello")
    public String Hello(){
        return "Hello";
    }

    @GetMapping("/getUserById/{userId}")
    public ynu.entity.CommentResult<ynu.entity.User> getUserById(@PathVariable Integer userId){
        ynu.entity.User u = new User(userId,"小明","123456");
        return new CommentResult<>(200,"success(11001)",u);
    }

    @PutMapping("/updateUser")
    public CommentResult<User> updateUser(Integer userId,String name,String newName){
        // update user info by userId
        ynu.entity.User u = new User(userId,name,"123456");
        u.setUserName(newName);
        return new CommentResult<>(200,"success(11001)",u);
    }

    @PostMapping("/createUser")
    public CommentResult<User> createUser(Integer userId,String name){
        // create user
        ynu.entity.User u = new User(userId,name,"123456");
        return new CommentResult<>(200,"success(11001)",u);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public CommentResult<User> deleteUser(@PathVariable Integer userId){
        ynu.entity.User u = new User(userId,"小明","123456");
        return new CommentResult<>(200,"success(11001)",u);
    }
}
