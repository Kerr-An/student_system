package com.kerr.controller;

import com.kerr.entity.Course;
import com.kerr.entity.Course;
import com.kerr.service.ClazzService;
import com.kerr.service.CourseService;
import com.kerr.service.CourseService;
import com.kerr.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    private static final String LIST = "course/list";
    private static final String ADD = "course/add";
    private static final String UPDATE = "course/update";

    @Autowired
    private CourseService courseService;

    @RequestMapping("/add")
    public String add(){
        return ADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(Course course){
        int result = courseService.create(course);
        if (result<=0){
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = courseService.delete(id);
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
        int result = courseService.delete(ids);
        if (result<=0){
            // 失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Course course){
        int result = courseService.update(course);
        if (result<=0){
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id,ModelMap modelMap){
        Course course = courseService.detail(id);
        modelMap.addAttribute("course",course);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(Course course){
        List<Course> list = courseService.query(course);
        Integer count = courseService.count(course);

        return MapControl.getInstance().success().put("data",list).put("count",count).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }




}
