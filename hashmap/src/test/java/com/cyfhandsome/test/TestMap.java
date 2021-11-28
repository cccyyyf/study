package com.cyfhandsome.test;

import com.cyfhandsome.domain.ThirdHashMap;
import org.junit.Test;

/**
 * @author cyf
 * @date 2021/11/28 20:22
 */
public class TestMap {
    @Test
    public void testMap() {
        ThirdHashMap<String, String> thirdHashMap = new ThirdHashMap<>();
        thirdHashMap.put("test1", "test2");
        System.out.println(thirdHashMap.get("test1"));
        System.out.println(thirdHashMap.get("asdasd"));
    }
}
