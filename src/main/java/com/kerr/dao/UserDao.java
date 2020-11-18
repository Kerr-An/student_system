package com.kerr.dao;

import com.kerr.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public int create(User user);

    public int delete(Map<String,Object> map); // 此处传map使得传参更加灵活，可以传id，也可以传各种组合参数

    public int update(Map<String,Object> map);

    public List<User> query(Map<String,Object> map);

    public int count(Map<String,Object> map);

    public User detail(Map<String,Object> map);

}
