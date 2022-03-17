package com.cyfhandsome.up.money;



import java.util.*;

/**
 * @author cyf
 * @date 2022/3/5 14:41
 */
public class Main {

    public static int[] getSortNum(int[] heightArr,int[] weightArr){
        if(heightArr.length == 1){
            return new int[]{1};
        }
        List<HeightWeight> list = new ArrayList<>();
        for (int i = 0; i < heightArr.length; i++) {
            HeightWeight heightWeight = new HeightWeight();
            heightWeight.height = heightArr[i];
            heightWeight.weight = weightArr[i];
            heightWeight.sort = i;
            list.add(heightWeight);
        }
        list.sort(Comparator.comparingInt(HeightWeight::getHeight).thenComparing(HeightWeight::getWeight));
        int[] result = new int[heightArr.length];
        for (int i = 0; i < list.size(); i++) {
            result[list.get(i).sort] = i+1;
        }
        return result;
    }

    public static class HeightWeight{
        int height;
        int weight;
        int sort;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] heightArr = new int[n];
        int[] weightArr = new int[n];
        for(int i = 0; i < n; i++){
            heightArr[i] = sc.nextInt();
        }

        for(int i = 0; i < n; i++){
            weightArr[i] = sc.nextInt();
        }
        int[] sortNum = getSortNum(heightArr, weightArr);

        for (int i = 0; i < sortNum.length; i++) {
            System.out.printf(String.valueOf(sortNum[i]).concat(" "));
        }
    }

    public int getRealMoney(int curMoney){
        int beforeMoney = curMoney;
        int addMoney = beforeMoney / 10;
        curMoney = curMoney / 10;
        while (curMoney > 1) {
            if (curMoney < 10) {
                addMoney += curMoney / 5 * 9;
            }else if(curMoney<100 && curMoney/10 >=1){
                addMoney +=curMoney/10 *9;
            }
            else {
                addMoney += curMoney / 10;
            }

            curMoney = curMoney / 10;
        }

        if (beforeMoney % 10 > 3) {
            addMoney += 1;
        }

        return beforeMoney - addMoney;
    }

}






