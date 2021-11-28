package com.cyfhandsome.factory.demo.handler;

import com.cyfhandsome.factory.demo.factory.GetNodeTypeFactory;

import java.util.List;

/**
 * @author cyf
 * @date 2021/11/25 17:56
 * 先init整体流程 之后自动处理数据 责任链模式 A->B ->C ->D
 */
public class TestNodeA<K> extends BaseNodeHandler<K> implements BaseNodeType<TestNodeA<K>> {


    @Override
    public void doHandle(String currentNodeId) {
        System.out.println(currentNodeId);
        //获取node详情 -> config
        List<K> config = getConfig(currentNodeId);

        //根据类型获取下一个节点对应的类
        //工厂类获取对应类
        BaseNodeHandler nextDoClass = GetNodeTypeFactory.getClassType("TestNodeA").getCorrespondingClass();
        //处理
        nextDoClass.doHandle("xxx");
    }

    @Override
    public String nodeType() {
        return "TestNodeA";
    }

    @Override
    public BaseNodeHandler getCorrespondingClass() {
        return new TestNodeA<>();
    }


}
