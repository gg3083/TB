package com.gg3083.tb.controller;

import com.gg3083.tb.common.controller.BaseController;
import com.gg3083.tb.domain.menu.Menu;
import com.gg3083.tb.json.JsonResult;
import com.gg3083.tb.mapper.menu.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/menu")
public class TbMenuController extends BaseController {

    @Autowired
    private MenuMapper menuMapper;

    @RequestMapping("list")
    @ResponseBody
    public JsonResult list(){
        return buildSuccess(new JsonResult(),menuMapper.selectAll());
    }

    @RequestMapping("add")
    @ResponseBody
    public JsonResult add(Menu menu){
        return buildSuccess(new JsonResult(),menuMapper.insertSelective(menu));
    }
}
