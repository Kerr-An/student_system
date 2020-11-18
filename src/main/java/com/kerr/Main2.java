package com.kerr;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        int minStr = getMinStr(str1, str2);
        System.out.println(minStr);
    }

    private static int getMinStr(String str1, String str2) {
        // 首先判断参数str1或者str2是否为null，还有一种情况：str1的长度小于str2
        if (str1 == null || str2 ==null || str1.length()<str2.length()){
            return 0;
        }

        // 正常情况的逻辑
        // 1. 转为数组方便比较
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        // 2. 遍
        int[] map = new int[256];
        for (int i = 0; i < chars2.length; i++) {
            map[chars2[i]]++;
        }
        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        int match = chars2.length;
        while (right != chars1.length){
            map[chars1[right]]--;
            if (map[chars1[right]]>=0){
                match--;
            }
            if (match==0){
                while (map[chars1[left]]<0){
                    map[chars1[left++]]++;
                }
                minLen = Math.min(minLen,right-left+1);
                match++;
                map[chars1[left++]]++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
