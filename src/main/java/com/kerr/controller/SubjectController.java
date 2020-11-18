package com.kerr.controller;

import com.kerr.entity.Subject;
import com.kerr.service.SubjectService;
import com.kerr.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    private static final String LIST = "subject/list";
    private static final String ADD = "subject/add";
    private static final String UPDATE = "subject/update";

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/add")
    public String add(){
        return ADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(Subject subject){
        int result = subjectService.create(subject);
        if (result<=0){
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = subjectService.delete(id);
        if (result<=0){
            // 失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    // 批量删除
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String ids){
        int result = subjectService.delete(ids);
        if (result<=0){
            // 失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Subject subject){
        int result = subjectService.update(subject);
        if (result<=0){
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id,ModelMap modelMap){
        Subject subject = subjectService.detail(id);
        modelMap.addAttribute("subject",subject);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(Subject subject){
        List<Subject> list = subjectService.query(subject);
        Integer count = subjectService.count(subject);

        return MapControl.getInstance().success().put("data",list).put("count",count).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }



}

