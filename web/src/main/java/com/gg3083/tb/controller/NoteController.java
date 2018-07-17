package com.gg3083.tb.controller;

import com.gg3083.tb.service.note.impl.NoteServiceImpl;
import com.gg3083.tb.common.controller.BaseController;
import com.gg3083.tb.domain.note.Note;
import com.gg3083.tb.json.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@RequestMapping("/tb/note")
@Controller
public class NoteController extends BaseController {

    @Autowired
    private NoteServiceImpl noteServiceImpl;

    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(PageInfo pageInfo, String searchKey){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setObj(noteServiceImpl.search(pageInfo,searchKey));
        return jsonResult;
    }

    @RequestMapping("/add")
    @ResponseBody
    public JsonResult add(Note note){
        JsonResult jsonResult=new JsonResult();
        note.setPkNoteId(UUID.randomUUID().toString().replace("-",""));
        //noteServiceImpl.saveNoNull(note);
        jsonResult.setMsg("添加成功");
        return buildSuccess(jsonResult);
    }
}

