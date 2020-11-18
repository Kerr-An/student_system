package com.kerr.controller;

import com.kerr.entity.Clazz;
import com.kerr.entity.Student;
import com.kerr.entity.Subject;
import com.kerr.service.ClazzService;
import com.kerr.service.StudentService;
import com.kerr.service.SubjectService;
import com.kerr.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    private static final String LIST = "student/list";
    private static final String ADD = "student/add";
    private static final String UPDATE = "student/update";

    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClazzService clazzService;

    @RequestMapping("/add")
    public String add(ModelMap modelMap) {
        // 先查专业
        List<Subject> subjects = subjectService.query(null);

        modelMap.addAttribute("subjects",subjects);

        return ADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(Student student) {
        int result = studentService.create(student);
        if (result <= 0) {
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = studentService.delete(id);
        if (result <= 0) {
            // 失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    // 批量删除
    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(String ids) {
        int result = studentService.delete(ids);
        if (result <= 0) {
            // 失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(Student student) {
        int result = studentService.update(student);
        if (result <= 0) {
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    /**
     * 这个里面不只要拿到具体的班级，还要拿到专业
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Student student = studentService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects",subjects);
        modelMap.addAttribute("student",student);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(Student student) {
        // 学生列表
        List<Student> students = studentService.query(student);
        List<Subject> subjects = subjectService.query(null);
        List<Clazz> clazzes = clazzService.query(null);
        students.forEach(entity ->{
            subjects.forEach(subject -> {
                if (entity.getSubjectId() == subject.getId().intValue()){
                    entity.setSubject(subject);
                }
            });
            clazzes.forEach(clazz -> {
                if (entity.getClazzId() == clazz.getId().intValue()){
                    entity.setClazz(clazz);
                }
            });
        });
        Integer count = studentService.count(student);
        return MapControl.getInstance().success().put("data", students).put("count", count).getMap();
    }

    @GetMapping("/list")
    public String list() {
        return LIST;
    }
}
