package com.kerr.controller;

import com.kerr.entity.Student;
import com.kerr.entity.Teacher;
import com.kerr.entity.User;
import com.kerr.service.StudentService;
import com.kerr.service.TeacherService;
import com.kerr.service.UserService;
import com.kerr.utils.MD5Utils;
import com.kerr.utils.MapControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String userName, String password, String type, String captcha, HttpSession session) {

        // 判断基本登录信息是否为空
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(type)) {
            return MapControl.getInstance().error("用户名或密码不能为空！").getMap();
        }

        // 判断验证码
        String _captcha = (String) session.getAttribute("captcha");
        // 验证码不能为空
        if (StringUtils.isEmpty(captcha)){
            return MapControl.getInstance().error("验证码不能为空").getMap();
        }
        // 验证码不匹配返回错误信息
        if (!captcha.equals(_captcha)) {
            return MapControl.getInstance().error("验证码错误").getMap();
        }

        // 判断登录类型：
        // 1管理员（123456 >>> 93a9ded8a9ab7cb69dba0c0575665204）
        if ("1".equals(type)) {
            User user = userService.login(userName, MD5Utils.getMD5(password));
            if (user != null) {
                session.setAttribute("user", user);
                session.setAttribute("type", "1");
                return MapControl.getInstance().success().add("data",user).getMap();
            } else {
                return MapControl.getInstance().error("用户名或密码错误").getMap();
            }
        }
        // 2老师
        if ("2".equals(type)) {
            Teacher teacher = teacherService.login(userName, MD5Utils.getMD5(password));
            if (teacher != null) {
                session.setAttribute("user", teacher);
                session.setAttribute("type", "2");
                return MapControl.getInstance().success().add("data",teacher).getMap();
            } else {
                return MapControl.getInstance().error("用户名或密码错误").getMap();
            }
        }
        // 3学生
        if ("3".equals(type)) {
            Student student = studentService.login(userName, MD5Utils.getMD5(password));
            if (student != null) {
                session.setAttribute("user", student);
                session.setAttribute("type", "3");
                return MapControl.getInstance().success().add("data",student).getMap();

            } else {
                return MapControl.getInstance().error("用户名或密码错误").getMap();
            }
        }
        return MapControl.getInstance().getMap();
    }

}
