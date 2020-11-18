package com.kerr.service;

import com.github.pagehelper.PageHelper;
import com.kerr.dao.TeacherDao;
import com.kerr.entity.Teacher;
import com.kerr.utils.BeanMapUtils;
import com.kerr.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    public int create(Teacher pi){
        return teacherDao.create(pi);
    }

    public int delete(Integer id){
        return teacherDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    // 批量删除
    public int delete(String ids){
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            flag = teacherDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }

    public int update(Teacher teacher){
        return teacherDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(teacher)).addId(teacher.getId()).getMap());
    }

    public List<Teacher> query(Teacher teacher){
        if (teacher!=null&&teacher.getPage()!=null){
            PageHelper.startPage(teacher.getPage(),teacher.getLimit()); // 分页功能
        }
        return teacherDao.query(BeanMapUtils.beanToMap(teacher));
    }

    public Teacher detail(Integer id){
        return teacherDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Teacher teacher){
        return teacherDao.count(BeanMapUtils.beanToMap(teacher));
    }

    // 登录功能
    public Teacher login(String userName, String password) {

        Map<String, Object> map = MapParameter.getInstance()
                .add("teacherName", userName)
                .add("teacherPwd", password)
                .getMap();

        return teacherDao.detail(map);
    }
}
