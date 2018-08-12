package com.gg3083.tb.service.note.impl;

import com.gg3083.tb.domain.note.Note;
import com.gg3083.tb.mapper.note.NoteMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl  {

    @Autowired
    private NoteMapper noteMapper;

    public PageInfo<Note> search(PageInfo pageInfo, String searchKey) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        PageInfo pageInfo1 = new PageInfo<>(noteMapper.search(searchKey));
        return pageInfo1;
    }

    public Note get(String id){
        return noteMapper.get( id );
    }


    public void add( Note note){
        noteMapper.insertSelective( note);
    }

}
