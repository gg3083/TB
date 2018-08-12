package com.gg3083.tb.service.log.impl;

import com.gg3083.tb.common.exception.GGException;
import com.gg3083.tb.domain.log.Log;
import com.gg3083.tb.mapper.log.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl {

    @Autowired
    LogMapper logMapper;

    public List<Log> list(){
        boolean b = true;
        if ( b){
            throw new GGException("hh");
        }
        return logMapper.selectAll();
    }
}
