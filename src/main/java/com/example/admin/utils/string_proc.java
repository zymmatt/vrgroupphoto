package com.example.admin.utils;

import java.util.Random;

public class string_proc {
    public static String extractFileName(String url) {
        // 找到最后一个斜杠的位置
        int lastSlashIndex = url.lastIndexOf("/");

        // 如果找到了斜杠，则从斜杠位置+1开始截取字符串，即为文件名
        if (lastSlashIndex != -1) {
            return url.substring(lastSlashIndex + 1);
        } else {
            // 如果没有斜杠，则返回原始字符串
            return url;
        }
    }

    public static boolean containsAtLeastTwoLetters(String input) {
        int letterCount = 0;
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount++;
                if (letterCount >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String generateRandomString(int length) {
        // 定义字符源
        String source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // 将字符源转换为字符数组
        char[] charArray = source.toCharArray();

        // 创建随机数生成器
        Random random = new Random();

        // 用于存储生成的随机字符的字符串生成器
        StringBuilder sb = new StringBuilder();

        // 生成指定长度的随机字符串
        for (int i = 0; i < length; i++) {
            // 从字符数组中随机选择一个字符并追加到字符串生成器
            sb.append(charArray[random.nextInt(charArray.length)]);
        }

        // 返回最终生成的随机字符串
        return sb.toString();
    }


}
