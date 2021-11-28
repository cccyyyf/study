package com.cyfhandsome.factory.demo.factory;

import com.cyfhandsome.factory.demo.handler.BaseNodeType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cyf
 * @date 2021/11/26 11:01
 */
@Component
public class GetNodeTypeFactory implements ApplicationContextAware {

    private static final Map<String, BaseNodeType> GET_CLASS_MAP = new HashMap<>();

    public static BaseNodeType getClassType(String strategyKey) {
        return GET_CLASS_MAP.getOrDefault(strategyKey, GET_CLASS_MAP.get("TestNodeA"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, BaseNodeType> getTableNameMap = applicationContext.getBeansOfType(BaseNodeType.class);
        getTableNameMap.values().forEach(bean -> GET_CLASS_MAP.put(bean.nodeType(), bean));
    }
}
