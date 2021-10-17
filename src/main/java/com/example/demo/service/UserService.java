package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {
    User selectByName(@RequestParam("name")String name);
}
