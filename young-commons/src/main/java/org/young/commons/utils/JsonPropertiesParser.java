package org.young.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonPropertiesParser {
    
    private JsonPropertiesParser() {}
    
    /**
     * JSON字符串转换为Properties, 暂时不支持json数组
     * @param json json字符串
     * @return Properties
     */
    public Properties parseToProperties(String json) {
        Properties prop = new Properties();
        if(StringUtils.isBlank(json)) {
            return prop;
        }
        
        try {
            JSONObject jsonObj = JSON.parseObject(json);
            for(String key : jsonObj.keySet()) {
                prop.setProperty(key, jsonObj.getString(key));
            }
            
            
        } catch (JSONException e) {
            throw e;
        }
        
        return prop;
    }
    
    /** 
     * properties 转json字符串， 支持数组
     * @param properties
     * @return json字符串
     */
    public static String parseToJsonString(Properties properties) {
        JSONObject root = new JSONObject();
        if(properties==null) {
            return root.toJSONString();
        }
        
        Set<Object> keyset = properties.keySet();
        Properties copy =  (Properties) properties.clone();
        
        for(Object obj :keyset) {
            String key = obj.toString();
            String val = copy.getProperty(key);
            
            String[] levels = key.split("\\.");
            nextChildren(root, copy, key, val, levels, 0);
        }
        
        
        return root.toJSONString();
    }
    
    
    /**
     * 递归处理
     * @param parent 父节点
     * @param prop 属性对象
     * @param pkey 属性key
     * @param value 属性值
     * @param levels 属性key等级切分
     * @param index 属性key等级索引
     * @return 递归调用
     */
    private static JSONObject nextChildren(JSONObject parent, Properties prop, String pkey, String value,  String[] levels, int index) {
        
        String regex = "\\[[0-9]+\\]"; // [xxx]的正则
        String key   = levels[index].trim().replaceAll(regex, ""); // 去掉 [xxx]
        
        String nodeIdx = levels[index].trim().replaceAll(key, "");
        nodeIdx = nodeIdx.replaceAll("\\[|\\]", ""); // 截取节点元素索引
        
        boolean isArr = levels[index].matches(key+regex);
        boolean last  = index==levels.length-1;
        
        // 是否最后一级
        if(last) {
            if(isArr) {
                Object vals = parent.get(key);
                
                String[] arr = vals==null?new String[0]:vals.toString().split(",");
                List<String> children = new ArrayList<>();
                for(String e:arr) {
                    children.add(e);
                }
                
                int childIndex = Integer.valueOf(nodeIdx);
                int size = children.size();
                
                while(size < childIndex+1) {
                    children.add("");
                    size = children.size();
                }
                
                arr = children.toArray(new String[size]);
                arr[childIndex] = value;
                
                value = StringUtils.join(arr, ",");
            }
            
            parent.put(key, value);
            prop.remove(pkey);
            return parent;
        }
        
        index++;
        if(isArr) {
            
            int childIndex = Integer.valueOf(nodeIdx);
            JSONArray children = parent.getJSONArray(key);
            children = children==null?new JSONArray():children;
            
            int size = children.size();
            while(size < childIndex+1) {
                children.add(new JSONObject());
                size = children.size();
            }
            
            JSONObject child = children.getJSONObject(childIndex);
            parent.put(key, children);
            
            return nextChildren(child, prop, pkey, value, levels, index);
        }
        
        JSONObject child = parent.getJSONObject(key);
        child = child==null?new JSONObject():child;
        
        parent.put(key, child);
        return nextChildren(child, prop, pkey, value, levels, index);
    }
    
    
    public static void main(String[] args) {
        
        Properties prop = new Properties();
        
        prop.put("param.user.name", "liusanjie");
        prop.put("param.user.age", "12");
        prop.put("param.data.length", "1024");
        prop.put("param.roles[0]", "admin");
        prop.put("param.roles[4]", "user");
        prop.put("param.roles[3]", "pm");
        
        prop.put("params[1].users[0].name", "wangwu");
        prop.put("params[1].users[0].age", "30");

        prop.put("params[1].users[1].name", "zhaoliu");
        prop.put("params[1].users[1].age", "33");
        
        prop.put("params[0].users[0].name", "zhangsan");
        prop.put("params[0].users[0].age", "10");

        
        prop.put("params[0].users[1].name", "lisi");
        prop.put("params[0].users[1].age", "20");
        
        System.out.println(parseToJsonString(prop));
        
    }
    

}
