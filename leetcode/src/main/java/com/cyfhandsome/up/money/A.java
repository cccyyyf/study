package com.cyfhandsome.up.money;

import java.util.*;

/**
 * @author cyf
 * @date 2022/3/9 22:03
 */
public class A {

    public static void main(String[] args) {
        Map<Integer,List<Integer>> randomMap =   getAllNewArr();
        StringBuilder parentStr = new StringBuilder("[\n");
        randomMap.forEach((key,valueList) -> {
            StringBuilder sb = new StringBuilder("[");
            valueList.forEach(num -> {
                sb.append(num).append(",");
            });
            sb.deleteCharAt(sb.length()-1);
            sb.append("],");
            parentStr.append(sb).append("\n");
        });
        parentStr.deleteCharAt(parentStr.length()-2);
        parentStr.append("]");
        System.out.println(parentStr);
    }

    public static Map<Integer,List<Integer> >getAllNewArr(){
        //先排序后分组
        List<Integer> randomList = createArr(18);
        randomList.sort(Comparator.comparingInt(Integer::intValue));
        Map<Integer,List<Integer>> randomMap= new HashMap();
        for(int i=0;i<randomList.size();i++){
            int randomNum = randomList.get(i);
            int num = randomNum/10;
            if(randomMap.containsKey(num)){
                randomMap.get(num).add(randomNum);
            }else{
                List<Integer> numList = new ArrayList();
                numList.add(randomNum);
                randomMap.put(num,numList);
            }
        }

        return randomMap;

    }

    public static List<Integer> createArr(int length){
        Set<Integer> set = new HashSet<>();
        while (set.size()<=length){
            set.add((int) (Math.random()*100));
        }
        return new ArrayList<>(set);
    }
}
