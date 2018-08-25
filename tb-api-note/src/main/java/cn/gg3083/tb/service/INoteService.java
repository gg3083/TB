package cn.gg3083.tb.service;

import cn.gg3083.tb.domain.Note;
import com.github.pagehelper.PageInfo;

public interface INoteService {

    PageInfo<Note> search(PageInfo pageInfo, String searchKey);

    Note get(String id);

    void add( Note note);
}
