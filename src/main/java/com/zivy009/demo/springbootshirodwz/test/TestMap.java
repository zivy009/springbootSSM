package com.zivy009.demo.springbootshirodwz.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
* @author zivy
* @date 2017年7月7日
* @describe
*/
public class TestMap   implements Map<String, Object>  {
private Map<String,Object> map=new HashMap<String,Object>();
    @Override
    public int size() {
        // TODO Auto-generated method stub
        return map.size();
        
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return map.isEmpty();
        
    }

    @Override
    public boolean containsKey(Object key) {
        // TODO Auto-generated method stub
        return map.containsKey(key);
        
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        return map.containsValue(value);
        
    }

    @Override
    public Object get(Object key) {
        // TODO Auto-generated method stub
        return map.get(key);
        
    }

    @Override
    public Object put(String key, Object value) {
        // TODO Auto-generated method stub
        return map.put(key, value);
        
    }

    @Override
    public Object remove(Object key) {
        // TODO Auto-generated method stub
        return map.remove(key);
        
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        // TODO Auto-generated method stub
        map.putAll(m);
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        // TODO Auto-generated method stub
        return map.keySet();
        
    }

    @Override
    public Collection<Object> values() {
        // TODO Auto-generated method stub
        return map.values();
        
    }

    @Override
    public Set<java.util.Map.Entry<String, Object>> entrySet() {
        // TODO Auto-generated method stub
        return map.entrySet();
        
    } 
    
}
