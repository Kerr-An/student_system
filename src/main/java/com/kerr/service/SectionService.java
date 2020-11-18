package com.kerr.service;

import com.github.pagehelper.PageHelper;
import com.kerr.dao.JobDao;
import com.kerr.dao.SectionDao;
import com.kerr.entity.Section;
import com.kerr.utils.BeanMapUtils;
import com.kerr.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SectionService {

    @Autowired
    private SectionDao sectionDao;

    public int create(Section pi){
        return sectionDao.create(pi);
    }

    public int delete(Integer id){
        return sectionDao.delete(MapParameter.getInstance().add("id",id).getMap());
    }

    public int update(Section section){
        return sectionDao.update(BeanMapUtils.beanToMapForUpdate(section));
    }

    public List<Section> query(Section section){
        if (section!=null&&section.getPage()!=null){
            PageHelper.startPage(section.getPage(),section.getLimit()); // 分页功能
        }
        return sectionDao.query(BeanMapUtils.beanToMapForUpdate(section));
    }

    public Section detail(Integer id){
        return sectionDao.detail(MapParameter.getInstance().add("id",id).getMap());
    }

    public int count(Section section){
        return sectionDao.count(BeanMapUtils.beanToMapForUpdate(section));
    }
}
