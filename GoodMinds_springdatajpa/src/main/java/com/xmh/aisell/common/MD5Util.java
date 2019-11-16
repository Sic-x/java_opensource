package com.xmh.aisell.common;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 传入密码，md5 + 迭代10次 + salt：“itsource”
 */
public class MD5Util {

    public static final String SALT = "itsource";
    public static final Integer HASHITERATIONS = 10;
    public static String createMD5Str(String password){
        SimpleHash result = new SimpleHash("MD5", password, SALT, HASHITERATIONS);
        return result.toString();
    }
}
