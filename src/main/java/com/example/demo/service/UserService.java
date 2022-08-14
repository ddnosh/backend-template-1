package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {
    List<User> selectAll();
    User selectById(@RequestParam("id")Integer id);
    User selectByName(@RequestParam("name")String name);
    void insert(User user);
    void update(User user);
    void deleteByName(@RequestParam("name")String name);
}
