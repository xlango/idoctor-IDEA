package com.idoctor.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service("redisTemplateUtil")
public class RedisTemplateUtil {  
    @Resource  
    private RedisTemplate<String, Object> redisTemplate;  

    public void set(String key, Object value) {  
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();  
        valueOperations.set(key, value);  
    }  

    public Object get(String key) {  
        return redisTemplate.opsForValue().get(key);  
    }  

    public void setList(String key, List<?> value) {  
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();  
        listOperations.leftPush(key, value);  
    }  

    public Object getList(String key) {  
        return redisTemplate.opsForList().leftPop(key);  
    }  

    public void setSet(String key, Set<?> value) {  
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();  
        setOperations.add(key, value);  
    }  

    public Object getSet(String key) {  
        return redisTemplate.opsForSet().members(key);  
    }  

    public void setHash(String key, Map<String, ?> value) {  
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();  
        hashOperations.putAll(key, value);  
    }  

    public Object getHash(String key) {  
        return redisTemplate.opsForHash().entries(key);  
    }  

    public void delete(String key) {  
        redisTemplate.delete(key);  
    }   
}