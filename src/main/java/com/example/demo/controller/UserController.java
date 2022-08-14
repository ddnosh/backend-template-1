package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")     // 下面所有映射都在users路径下
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @ApiOperation(value = "获取所有用户")
    public List<User> getUsers() {
        return userService.selectAll();
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    @ApiOperation(value = "获取指定用户", notes = "获取指定用户: by user id")
    private User getUserById(@PathVariable Integer id) {
        return userService.selectById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseBody
    @ApiOperation(value = "获取指定用户", notes = "获取指定用户: by user name")
    private User getUserByName(@PathVariable String name) {
        return userService.selectByName(name);
    }

    @PostMapping("/")
    @ApiOperation(value = "创建新用户", notes = "创建新用户: by user entity")
    public String postUser(@RequestBody User user) {
        userService.insert(user);
        return "success";
    }

    @PutMapping("/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "String", name = "id", value = "用户Id", required = true, example = "1")
    @ApiOperation(value = "更新用户", notes = "更新用户: by user id")
    public String updateUser(@PathVariable Integer id, @RequestBody User user) {
        User u = getUserById(id);
        u.setName(user.getName());
        userService.update(u);
        return "success";
    }

    @DeleteMapping("/{name}")
    @ApiOperation(value = "删除用户", notes = "删除用户: by user name")
    public String deleteUser(@PathVariable String name) {
        userService.deleteByName(name);
        return "success";
    }
}
