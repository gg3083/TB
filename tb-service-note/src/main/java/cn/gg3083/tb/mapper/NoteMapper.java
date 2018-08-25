package cn.gg3083.tb.mapper;

import cn.gg3083.tb.common.mapper.BaseMapper;
import cn.gg3083.tb.domain.Note;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoteMapper extends BaseMapper<Note> {

    List<Note> search(@Param("searchKey") String searchKey);

    Note get(String id);
}