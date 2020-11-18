package com.kerr.controller;

import com.kerr.entity.Teacher;
import com.kerr.entity.Teacher;
import com.kerr.entity.Teacher;
import com.kerr.service.ClazzService;
import com.kerr.service.TeacherService;
import com.kerr.service.TeacherService;
import com.kerr.service.TeacherService;
import com.kerr.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    private static final String LIST = "teacher/list";
    private static final String ADD = "teacher/add";
    private static final String UPDATE = "teacher/update";

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/add")
    public String add(){
        return ADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(Teacher teacher){
        int result = teacherService.create(teacher);
        if (result<=0){
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = teacherService.delete(id);
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
        int result = teacherService.delete(ids);
        if (result<=0){
            // 失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(Teacher teacher){
        int result = teacherService.update(teacher);
        if (result<=0){
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id,ModelMap modelMap){
        Teacher teacher = teacherService.detail(id);
        modelMap.addAttribute("teacher",teacher);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(Teacher teacher){
        List<Teacher> list = teacherService.query(teacher);
        Integer count = teacherService.count(teacher);

        return MapControl.getInstance().success().put("data",list).put("count",count).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }
}
