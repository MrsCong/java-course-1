package com.example.mybatis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.mybatis.dao.PersonDao;
import com.example.mybatis.exception.MyException;
import com.example.mybatis.po.Person;
import com.example.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    @Qualifier("personDao")
    private PersonDao personDao;

    @Override
    public String queryForList() {
        List<Person> persons = personDao.queryForList();
        return JSONObject.toJSONString(persons);
    }

    @Override
    public void insertPerson() {
        String id = UUID.randomUUID().toString();
        String name = "lisi" + new Random().nextInt(100)+1;
        personDao.insertPerson(new Person(id,name));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,
            timeout = 10,rollbackFor = RuntimeException.class,noRollbackFor = MyException.class)
    public void deletePersonById(String id) {
        personDao.deletePersonById(id);
        boolean isMyException = true;
        if (isMyException) {
            throw new MyException("我的异常");
        }else {
            throw new RuntimeException("运行时异常");
        }
    }

    @Override
    public int updatePerson(Person person) {
       return personDao.updatePerson(person);
    }

    @Override
    public void batchUpdateTest() {
        List<String> ids = Arrays.asList("1","2");
        personDao.batchUpdateTest(ids);
    }

    @Override
    public List<String> selectByTime() {
        return personDao.selectByTime(new Date());
    }

    @Override
    public int updateById(Date date,String id) {
        return personDao.updateById(date,id);
    }
}
