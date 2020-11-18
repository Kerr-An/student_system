package com.kerr.service;

import com.github.pagehelper.PageHelper;
import com.kerr.dao.ClazzDao;
import com.kerr.entity.Clazz;
import com.kerr.entity.User;
import com.kerr.utils.BeanMapUtils;
import com.kerr.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClazzService {

    @Autowired
    private ClazzDao clazzDao;

    public int create(Clazz pi){
        return clazzDao.create(pi);
    }

    public int delete(Integer id){
        return clazzDao.delete(MapParameter.getInstance().add("id",id).getMap());
    }

    // 批量删除
    public int delete(String ids){
        String[] arr = ids.split(",");
        int flag = 0;
        for (String s : arr) {
            flag = clazzDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
        }
        return flag;
    }

    public int update(Clazz clazz){
        return clazzDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(clazz)).addId(clazz.getId()).getMap());
    }

    public List<Clazz> query(Clazz clazz){
        if (clazz!=null&&clazz.getPage()!=null){
            PageHelper.startPage(clazz.getPage(),clazz.getLimit()); // 分页功能
        }
        return clazzDao.query(BeanMapUtils.beanToMap(clazz));
    }

    public Clazz detail(Integer id){
        return clazzDao.detail(MapParameter.getInstance().add("id",id).getMap());
    }

    public int count(Clazz clazz){
        return clazzDao.count(BeanMapUtils.beanToMap(clazz));
    }
}
