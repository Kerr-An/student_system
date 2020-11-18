package com.kerr;

public class Main {
    public static void main(String[] args) {
        String test = test();
        System.out.printf("返回：" + test);
    }

    public static String test() {
        try {
//            System.out.println("try");
            return "try";
        } catch (Exception e) {
//            System.out.println("catch");
            e.printStackTrace();
        } finally {
//            System.out.println("finally");
            return "finally";
        }
    }
}
