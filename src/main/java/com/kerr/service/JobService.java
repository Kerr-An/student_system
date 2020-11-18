package com.kerr.service;

import com.github.pagehelper.PageHelper;
import com.kerr.dao.CourseDao;
import com.kerr.dao.JobDao;
import com.kerr.entity.Job;
import com.kerr.utils.BeanMapUtils;
import com.kerr.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class JobService {

    @Autowired
    private JobDao jobDao;

    public int create(Job pi){
        return jobDao.create(pi);
    }

    public int delete(Integer id){
        return jobDao.delete(MapParameter.getInstance().add("id",id).getMap());
    }

    public int update(Job job){
        return jobDao.update(BeanMapUtils.beanToMapForUpdate(job));
    }

    public List<Job> query(Job job){
        if (job!=null&&job.getPage()!=null){
            PageHelper.startPage(job.getPage(),job.getLimit()); // 分页功能
        }
        return jobDao.query(BeanMapUtils.beanToMapForUpdate(job));
    }

    public Job detail(Integer id){
        return jobDao.detail(MapParameter.getInstance().add("id",id).getMap());
    }

    public int count(Job job){
        return jobDao.count(BeanMapUtils.beanToMapForUpdate(job));
    }
}
