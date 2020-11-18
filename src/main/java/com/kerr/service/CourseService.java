package com.kerr.service;

import com.github.pagehelper.PageHelper;
import com.kerr.dao.CourseDao;
import com.kerr.entity.Course;
import com.kerr.entity.Course;
import com.kerr.utils.BeanMapUtils;
import com.kerr.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    public int create(Course pi){
        return courseDao.create(pi);
    }

    public int delete(Integer id){
        return courseDao.delete(MapParameter.getInstance().add("id",id).getMap());
    }

    // 批量删除
    public int delete(String ids){
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            flag = courseDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }

    public int update(Course course){
        return courseDao.update(MapParameter.getInstance()
        .add(BeanMapUtils.beanToMapForUpdate(course))
        .addId(course.getId()).getMap());
    }

    public List<Course> query(Course course){
        if (course!=null&&course.getPage()!=null){
            PageHelper.startPage(course.getPage(),course.getLimit()); // 分页功能
        }
        return courseDao.query(BeanMapUtils.beanToMap(course));
    }

    public Course detail(Integer id){
        return courseDao.detail(MapParameter.getInstance().add("id",id).getMap());
    }

    public int count(Course course){
        return courseDao.count(BeanMapUtils.beanToMap(course));
    }
}
