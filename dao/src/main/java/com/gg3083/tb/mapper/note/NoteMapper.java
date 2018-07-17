package com.gg3083.tb.mapper.note;

import com.gg3083.tb.common.mapper.BaseMapper;
import com.gg3083.tb.domain.note.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

    List<Note> search(@Param("searchKey") String searchKey);
}