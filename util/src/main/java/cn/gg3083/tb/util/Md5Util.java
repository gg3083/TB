package cn.gg3083.tb.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.StringUtils;

public class Md5Util {

    public Md5Util() {
    }

    public static String getMd5(String key, String str) {
        return StringUtils.isEmpty(str) ? "" : DigestUtils.md5Hex(key + str);
    }
}
