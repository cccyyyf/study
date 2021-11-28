package com.cyfhandsome.factory.demo.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cyf
 * @date 2021/11/25 15:15
 * 顶级模板接口限制节点内容 节点传递箭头模板 数据判定节点模板 异常解决方案
 */
public abstract class BaseNodeHandler<K> {


    /**
     * 获取节点的配置类
     *
     * @return List
     */
    protected final List<K> getConfig(String nodeId) {
        //return xxx.queryById(configId)
        return new ArrayList<>();
    }

    /**
     * 获取下一个节点
     * @param currentNodeId input
     */
    public abstract void doHandle(String currentNodeId);




}
