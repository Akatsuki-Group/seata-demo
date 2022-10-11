package org.example.jta.controller;

import org.example.jta.service.JtaTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yuanct
 * @since 1.2
 */
@RestController
@RequestMapping("/jta")
public class JtaTestController {
    @Autowired
    private JtaTestService jtaTestService;


    @ResponseBody
    @RequestMapping("/test01")
    public Map<String,Object> test01(){
        LinkedHashMap<String,Object> resultMap=new LinkedHashMap<String,Object>();
        try {
            return jtaTestService.test01();
        }catch (Exception e){
            resultMap.put("state","fail");
            resultMap.put("message","分布式事务同步失败");
            return resultMap;
        }
    }

    @ResponseBody
    @RequestMapping("/test02")
    public Map<String,Object> test02(){
        LinkedHashMap<String,Object> resultMap=new LinkedHashMap<String,Object>();
        try {
            return jtaTestService.test02();
        }catch (Exception e){
            resultMap.put("state","fail");
            resultMap.put("message","分布式事务同步失败");
            return resultMap;
        }
    }

    @ResponseBody
    @RequestMapping("/test")
    public Map<String,Object> test(){
        LinkedHashMap<String,Object> resultMap=new LinkedHashMap<String,Object>();
        try {
            return jtaTestService.test();
        }catch (Exception e){
            resultMap.put("state","fail");
            resultMap.put("message","分布式事务同步失败");
            return resultMap;
        }
    }
}
