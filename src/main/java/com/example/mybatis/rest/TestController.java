package com.example.mybatis.rest;

import com.alibaba.fastjson.JSONObject;
import com.example.mybatis.po.Person;
import com.example.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private TestService testService;

    @RequestMapping(method = RequestMethod.GET,value = "/queryForList")
    public String queryForList() {
        return testService.queryForList();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/insert")
    public void insert() {
        testService.insertPerson();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/del")
    public void delete(@RequestBody JSONObject jsonObject) {
        testService.deletePersonById((String) jsonObject.get("id"));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/update")
    public int updatePerson(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors().get(0).getDefaultMessage());
            return -1;
        }
        System.out.println("123");
        return testService.updatePerson(person);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/batchUpdateTest")
    public void batchUpdatePerson() {
        testService.batchUpdateTest();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addKeyValue")
    public void putKeyValue(@RequestBody JSONObject jsonObject) {
        String key = (String) jsonObject.get("key");
        String value = (String) jsonObject.get("value");
        redisTemplate.boundValueOps(key).set(value);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/addKeyArr")
    public void putKeyArr(@RequestBody JSONObject jsonObject) {
        String key = (String) jsonObject.get("key");
        List<String> arr = (List<String>) jsonObject.get("value");
        redisTemplate.opsForList().leftPushAll(key,arr);
        System.out.println(redisTemplate.opsForList());
    }


    @RequestMapping(method = RequestMethod.POST,value = "/getValueByKey")
    public String getKeyValue(@RequestBody JSONObject jsonObject) {
        String key = (String) jsonObject.get("key");
        return (String) redisTemplate.boundValueOps(key).get();
    }




}
