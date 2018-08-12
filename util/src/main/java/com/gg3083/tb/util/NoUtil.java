package com.gg3083.tb.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author : GG
 * @Date : 2018/7/25
 */
public class NoUtil {

    /**
     * 生成时间格式的编号
     * @return
     */
    public static String getDateNo(){
        String no = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        return no.substring(2,no.length());
    }

    /**
     * 生成UUID格式的编号 去掉-
     * @return
     */
    public static String getUuidNo(){
       return UUID.randomUUID().toString().replace("-","");
    }
}
