package com.gg3083.tb.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class Md5Util {

    //MD5加密
    public static String getMd5(String key, String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return DigestUtils.md5Hex(key + str);
    }
}
