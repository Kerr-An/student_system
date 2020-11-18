package com.kerr.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kerr.dao.JobDao;
import com.kerr.dao.SubjectDao;
import com.kerr.entity.Subject;
import com.kerr.utils.BeanMapUtils;
import com.kerr.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    public int create(Subject pi){
        return subjectDao.create(pi);
    }

    public int delete(Integer id){
        return subjectDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    // 批量删除
    public int delete(String ids){
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            flag = subjectDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }

    public int update(Subject subject){
        Map<String,Object> map = MapParameter.getInstance()
                .add(BeanMapUtils.beanToMapForUpdate(subject))
                .addId(subject.getId()).getMap();
        return subjectDao.update(map);
    }

    public List<Subject> query(Subject subject){
        // 首先判断subject和subject.getPage()是否为空，若为空的情况下执行startPage，那么就会报空
        if (subject!=null&&subject.getPage()!=null){
            PageHelper.startPage(subject.getPage(),subject.getLimit()); // 分页功能
        }

        return subjectDao.query(BeanMapUtils.beanToMap(subject));
    }

    public Subject detail(Integer id){
        return subjectDao.detail(MapParameter.getInstance().add("id",id).getMap());
    }

    public int count(Subject subject){
        return subjectDao.count(BeanMapUtils.beanToMap(subject));
    }
}
