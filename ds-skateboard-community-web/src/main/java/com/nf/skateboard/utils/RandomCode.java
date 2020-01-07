package com.nf.skateboard.utils;

import java.util.Random;

public class RandomCode {


    // 随机生成6位数字验证码
    public static String getRandomForSix() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            //随机生成 0～9 的随机数
            int i1 = random.nextInt(10);
            result += i1;
        }
        return result;
    }

    // 随机生成6位xxx 不知道干嘛的
    public static String verifyCode() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 6; i++) {
            int key = random.nextInt(3);
            switch (key){
                case 0:
                    int code1 = random.nextInt(10);
                    result += code1;
                    break;
                case 1:
                    char code2 = (char)(random.nextInt(26)+65);
                    result += code2;
                    break;
                case 2:
                    char code3 = (char)(random.nextInt(26)+97);
                    result += code3;
                    break;
            }
        }
        return result;
    }
}
