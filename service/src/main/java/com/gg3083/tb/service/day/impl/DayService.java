package com.gg3083.tb.service.day.impl;

import com.alibaba.fastjson.JSONObject;
import com.gg3083.tb.domain.day.Day;
import com.gg3083.tb.mapper.day.DayMapper;
import com.gg3083.tb.util.HttpClient431Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DayService {


    private final static String httpUrl = "https://api.goseek.cn/Tools/holiday";

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");

    @Autowired
    private DayMapper dayMapper;

    public void add(){
        searchDate( 2018 );

    }


    private void searchDate(Integer year) {//日期查询

        Calendar calendar = new GregorianCalendar();//定义一个日历，变量作为年初
        Calendar calendarEnd = new GregorianCalendar();//定义一个日历，变量作为年末

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置年初的日期为1月1日

        calendarEnd.set(Calendar.YEAR, year);
        calendarEnd.set(Calendar.MONTH, 11);
        calendarEnd.set(Calendar.DAY_OF_MONTH, 31);//设置年末的日期为12月31日


        while (calendar.getTime().getTime() <= calendarEnd.getTime().getTime()) {//用一整年的日期循环
            String date = sdf2.format(calendar.getTime());
            holiday(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);//日期+1
        }
    }

    //查询该天的数据 并且插入数据库
    private void holiday(String date) {

        Map<String, String> params = new HashMap<String, String>();

        params.put("date", date);  //设置查询到具体某一天,格式是yyyyMMdd

        Date date1 = strToDateFormat(date); //格式化成 Date 类型

        try {
            String result = HttpClient431Util.doGet(params, httpUrl);
            JSONObject jsStr = JSONObject.parseObject(result); //将返回的数据转化成json格式
            String data = jsStr.getString("data"); //取得返回的数据
            Day workDay = new Day();
            workDay.setDate(date1);
            if (data.equals("0")) {//工作日
                workDay.setData(0);
            } else { //休息日 节假日
                workDay.setData(1);
            }
            dayMapper.insertSelective(workDay);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //将yyyyMMdd 转化成yyyy-MM-dd
    private Date strToDateFormat(String date) {

        sdf2.setLenient(false);
        Date newDate = null;
        try {
            String date1 = sdf.format(sdf2.parse(date));
            newDate = sdf.parse(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
