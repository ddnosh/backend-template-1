package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserDynamicSqlSupport;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> selectAll() {
        SelectStatementProvider selectStatement = SqlBuilder.select(UserMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return userMapper.selectMany(selectStatement);
    }

    @Override
    public User selectById(Integer id) {
        SelectStatementProvider selectStatement = SqlBuilder.select(UserDynamicSqlSupport.id, UserDynamicSqlSupport.name)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.id, isEqualTo(id))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return userMapper.selectMany(selectStatement).get(0);
    }

    @Override
    public User selectByName(String name) {
        SelectStatementProvider selectStatement = SqlBuilder.select(UserDynamicSqlSupport.id, UserDynamicSqlSupport.name)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.name, isEqualTo(name))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return userMapper.selectMany(selectStatement).get(0);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteByName(String name) {
        DeleteStatementProvider deleteStatement = SqlBuilder.deleteFrom(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.name, isEqualTo(name))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        userMapper.delete(deleteStatement);
    }
}
