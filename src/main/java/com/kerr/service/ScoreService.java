package com.kerr.service;

import com.github.pagehelper.PageHelper;
import com.kerr.dao.JobDao;
import com.kerr.dao.ScoreDao;
import com.kerr.entity.Score;
import com.kerr.utils.BeanMapUtils;
import com.kerr.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    public int create(Score pi){
        return scoreDao.create(pi);
    }

    public int delete(Integer id){
        return scoreDao.delete(MapParameter.getInstance().add("id",id).getMap());
    }

    public int update(Score score){
        return scoreDao.update(BeanMapUtils.beanToMapForUpdate(score));
    }

    public List<Score> query(Score score){
        if (score!=null&&score.getPage()!=null){
            PageHelper.startPage(score.getPage(),score.getLimit()); // 分页功能
        }
        return scoreDao.query(BeanMapUtils.beanToMapForUpdate(score));
    }

    public Score detail(Integer id){
        return scoreDao.detail(MapParameter.getInstance().add("id",id).getMap());
    }

    public int count(Score score){
        return scoreDao.count(BeanMapUtils.beanToMapForUpdate(score));
    }
}
