package com.kerr.utils;

import java.util.HashMap;
import java.util.Map;

public class MapParameter {

    //目标对象
    private Map<String,Object> paramMap = new HashMap<>();

    //私有构造（我在什么时候创建只有我知道，所以必须为private）
    private MapParameter(){

    }

    public static MapParameter getInstance(){
        return new MapParameter();
    }

    public MapParameter add(String key,Object value){
        paramMap.put(key,value);
        return this;
    }
    public MapParameter addId(Object value){
        paramMap.put("id",value);
        return this;
    }

    public MapParameter add(Map<String,Object> map){
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            paramMap.put(entry.getKey(),entry.getValue());
        }
        return this;
    }

    // 返回Map对象
    public Map<String,Object> getMap(){
        return paramMap;
    }




}
