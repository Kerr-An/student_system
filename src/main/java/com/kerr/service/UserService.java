package com.kerr.service;

import com.github.pagehelper.PageHelper;
import com.kerr.dao.UserDao;
import com.kerr.entity.User;
import com.kerr.entity.User;
import com.kerr.utils.BeanMapUtils;
import com.kerr.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public int create(User pi) {
        return userDao.create(pi);
    }

    public int delete(Integer id) {
        return userDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    // 批量删除
    public int delete(String ids){
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            flag = userDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }

    public int update(User user) {
        return userDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(user)).addId(user.getId()).getMap());
    }

    public List<User> query(User user) {
        if (user!=null&&user.getPage()!=null){
            PageHelper.startPage(user.getPage(),user.getLimit()); // 分页功能
        }
        return userDao.query(BeanMapUtils.beanToMap(user));
    }

    public User detail(Integer id) {
        return userDao.detail(MapParameter.getInstance().add("id", id).getMap());
    }

    // 登录功能
    public User login(String userName, String password) {

        Map<String, Object> map = MapParameter.getInstance()
                .add("userName", userName)
                .add("userPwd", password)
                .getMap();

        return userDao.detail(map);
    }

    public int count(User user) {
        return userDao.count(BeanMapUtils.beanToMap(user));
    }
}
