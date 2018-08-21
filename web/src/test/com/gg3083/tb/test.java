package com.gg3083.tb;

import com.gg3083.tb.domain.day.Day;
import com.gg3083.tb.mapper.day.DayMapper;
import com.gg3083.tb.service.day.impl.DayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {

    @Autowired
    private DayMapper dayMapper;

    @Test
    public void workDay(){
        Day day = new Day();
        day.setId( 1 );
        day.setData( 1 );
        day.setDate( new Date() );
        dayMapper.insertSelective( day );
    }

    @Autowired
    private DayService dayService;

    @Test
    public void fori(){
        dayService.add();
    }

}
