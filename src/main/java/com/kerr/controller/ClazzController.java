package com.kerr.controller;

import com.kerr.entity.Clazz;
import com.kerr.entity.Subject;
import com.kerr.service.ClazzService;
import com.kerr.service.SubjectService;
import com.kerr.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clazz")
public class ClazzController {

    private static final String LIST = "clazz/list";
    private static final String ADD = "clazz/add";
    private static final String UPDATE = "clazz/update";

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/add")
    public String add(ModelMap modelMap) {
        // 先查专业
        List<Subject> subjects = subjectService.query(null);

        modelMap.addAttribute("subjects",subjects);

        return ADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> create(Clazz clazz) {
        int result = clazzService.create(clazz);
        if (result <= 0) {
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        int result = clazzService.delete(id);
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
        int result = clazzService.delete(ids);
        if (result <= 0) {
            // 失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(Clazz clazz) {
        int result = clazzService.update(clazz);
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
        Clazz clazz = clazzService.detail(id);
        List<Subject> subjects = subjectService.query(null);
        modelMap.addAttribute("subjects",subjects);
        modelMap.addAttribute("clazz",clazz);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String, Object> query(Clazz clazz) {
        List<Clazz> list = clazzService.query(clazz);
        List<Subject> subjects = subjectService.query(null);

        // 循环赋值
        list.forEach(entity ->{
            subjects.forEach(subject -> {
                // 如果他俩相等，就把subject赋值给entity（循环赋值）
                if (entity.getSubjectId()==subject.getId().intValue()){
                    entity.setSubject(subject);
                }
            });
        });

        Integer count = clazzService.count(clazz);
        return MapControl.getInstance().success().put("data", list).put("count", count).getMap();
    }

    @GetMapping("/list")
    public String list() {
        return LIST;
    }
}
