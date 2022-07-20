package com.example.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.example.mybatis.pojo.Student;
import com.example.mybatis.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import java.util.*;


@SpringBootTest
@Slf4j
class MybatisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TestService testService;

    @Test
    void test1() {
        System.out.println(testService.selectByTime());
    }

    @Test
    void add() {
        redisTemplate.opsForList().leftPush("list","2");
        redisTemplate.opsForList().leftPush("list","3");
        redisTemplate.opsForList().leftPush("list","4");
    }

    @Test
    void addObj() {
        Student s1 = new Student("张三",18,1);
        Student s2 = new Student("李四",20,2);
        Student s3 = new Student("王五",18,3);

        redisTemplate.opsForList().leftPush("objList", JSONObject.toJSONString(s1));
        redisTemplate.opsForList().leftPush("objList", JSONObject.toJSONString(s2));
        redisTemplate.opsForList().leftPush("objList", JSONObject.toJSONString(s3));
    }

    @Test
    void remove() {
        redisTemplate.opsForList().remove("list",1,"1");
    }

    @Test
    void myTest() {
        JSONObject apdu = new JSONObject();
        apdu.put("power-on", new JSONObject().put("duration", new JSONObject().put("seconds", 60)));
        System.out.println(apdu);
    }

    @Test
    void myTest01() {
        JSONObject apdu = new JSONObject();
        JSONObject duration = new JSONObject();
        duration.put("seconds",60);
        apdu.put("power-on",duration);
        System.out.println(apdu);
    }

    @Test
    void testScan() {
        Set<String> key = scanMatch("key1");
        key.forEach(v-> System.out.println(v));
    }

    public  Set<String> scanMatch(String matchKey) {
        Set<String> keys = new HashSet();
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection redisConnection = connectionFactory.getConnection();
        Cursor<byte[]> scan = null;
        if(redisConnection instanceof JedisClusterConnection){
            RedisClusterConnection clusterConnection = connectionFactory.getClusterConnection();
            Iterable<RedisClusterNode> redisClusterNodes = clusterConnection.clusterGetNodes();
            Iterator<RedisClusterNode> iterator = redisClusterNodes.iterator();
            while (iterator.hasNext()) {
                RedisClusterNode next = iterator.next();
                scan = clusterConnection.scan(next, ScanOptions.scanOptions().match(matchKey).count(Integer.MAX_VALUE).build());
                while (scan.hasNext()) {
                    keys.add(new String(scan.next()));
                }
                if(scan !=null){
                    scan.close();
                }
            }
            return keys;
        }
        if(redisConnection instanceof JedisConnection){
            scan = redisConnection.scan(ScanOptions.scanOptions().match(matchKey).count(Integer.MAX_VALUE).build());
            while (scan.hasNext()){
                //找到一次就添加一次
                keys.add(new String(scan.next()));
            }
            if(scan !=null){
                scan.close();
            }
            return keys;
        }

        return keys;

    }

}
