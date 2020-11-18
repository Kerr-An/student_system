package com.kerr.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

// 声明这个类为全局异常处理类
@ControllerAdvice
public class GlobalControllerAdvice {

    private final String ERROR = "error";

    // 没有权限访问就跳转到无权访问的页面
    @ExceptionHandler(value=PermissionException.class) // 异常控制器
    public ModelAndView noPermission(PermissionException e){
        ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(ERROR,e.getMessage());
        return modelAndView;
    }

    // 普通异常返回json
    @ExceptionHandler(value=RuntimeException.class)
    @ResponseBody
    public Map<String,Object> runtimeException(RuntimeException e){
        e.printStackTrace();
        return MapControl.getInstance().error().getMap();
    }


}
