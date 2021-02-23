package com.hemily.hsaasotanormal.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 字符串相关通用方法
 */
public class StrUtil {

    /**
     * 验证字符串是否为空
     *
     * @param text 要验证的字符串
     * @return
     */
    public static boolean isNullOrEmpty(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        if (text.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 验证字符串是否是有效到时间戳
     *
     * @param text 字符串
     * @return
     */
    public static boolean isTimeStamp(String text) {
        try {
            return Long.valueOf(text) > 0;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 获取当前时间戳(秒级)
     *
     * @return
     */
    public static long getTimeStamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 时间戳转时间(秒级)
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime convertToDate(long timestamp) {
        // ofEpochSecond 以秒为单位， ofEpochMilli 以毫秒为单位
        Instant instant = Instant.ofEpochSecond(timestamp);
        //Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 字符串md5加密
     *
     * @param text 明文
     * @return 密文
     */
    public static String getMd5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }


}
