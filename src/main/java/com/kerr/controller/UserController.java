package com.kerr.controller;

import com.kerr.entity.User;
import com.kerr.service.UserService;
import com.kerr.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final String LIST = "user/list";
    private static final String ADD = "user/add";
    private static final String UPDATE = "user/update";

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String add(){
        return ADD;
    }

    @PostMapping("/create")
    @ResponseBody
    public Map<String,Object> create(User user){
        int result = userService.create(user);
        if (result<=0){
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Map<String,Object> delete(@PathVariable("id") Integer id){
        int result = userService.delete(id);
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
        int result = userService.delete(ids);
        if (result<=0){
            // 失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @PostMapping("/update")
    @ResponseBody
    public Map<String,Object> update(User user){
        int result = userService.update(user);
        if (result<=0){
            // 新增失败
            return MapControl.getInstance().error().getMap();
        }
        return MapControl.getInstance().success().getMap();
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap){
        User user = userService.detail(id);
        modelMap.addAttribute("user",user);
        return UPDATE;
    }

    @PostMapping("/query")
    @ResponseBody
    public Map<String,Object> query(User user){
        List<User> list = userService.query(user);
        Integer count = userService.count(user);

        return MapControl.getInstance().success().put("data",list).put("count",count).getMap();
    }

    @GetMapping("/list")
    public String list(){
        return LIST;
    }



}
