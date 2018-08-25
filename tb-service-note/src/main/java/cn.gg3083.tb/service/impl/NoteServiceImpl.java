package cn.gg3083.tb.service.impl;

import cn.gg3083.tb.domain.Note;
import cn.gg3083.tb.mapper.NoteMapper;
import cn.gg3083.tb.service.INoteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements INoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Override
    public PageInfo<Note> search(PageInfo pageInfo, String searchKey) {
        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        PageInfo pageInfo1 = new PageInfo<>(noteMapper.search(searchKey));
        return pageInfo1;
    }

    @Override
    public Note get(String id) {
        return noteMapper.get(id);
    }

    @Override
    public void add(Note note) {
        noteMapper.insertSelective(note);
    }

}
