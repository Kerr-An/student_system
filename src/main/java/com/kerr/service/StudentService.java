package com.kerr.service;

import com.github.pagehelper.PageHelper;
import com.kerr.dao.StudentDao;
import com.kerr.entity.Student;
import com.kerr.utils.BeanMapUtils;
import com.kerr.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public int create(Student pi){
        return studentDao.create(pi);
    }

    public int delete(Integer id){
        return studentDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    // 批量删除
    public int delete(String ids){
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            flag = studentDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }

    public int update(Student student){
        return studentDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(student)).addId(student.getId()).getMap());
    }

    public List<Student> query(Student student){
        if (student!=null&&student.getPage()!=null){
            PageHelper.startPage(student.getPage(),student.getLimit()); // 分页功能
        }
        return studentDao.query(BeanMapUtils.beanToMap(student));
    }

    public Student detail(Integer id){
        return studentDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Student student){
        return studentDao.count(BeanMapUtils.beanToMap(student));
    }


    public Student login(String userName, String password) {
// 学生登录使用学号
        Map<String, Object> map = MapParameter.getInstance()
                .add("stuNo", userName)
                .add("stuPwd", password)
                .getMap();

        return studentDao.detail(map);
    }
}
