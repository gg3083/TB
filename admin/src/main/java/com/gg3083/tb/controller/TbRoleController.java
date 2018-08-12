package com.gg3083.tb.controller;

import com.gg3083.tb.common.controller.BaseController;
import com.gg3083.tb.domain.role.Role;
import com.gg3083.tb.json.JsonResult;
import com.gg3083.tb.mapper.role.RoleMapper;
import com.gg3083.tb.util.NoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

@Controller
@RequestMapping("/admin/role")
public class TbRoleController extends BaseController {

    @Autowired
    RoleMapper roleMapper;


    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(){
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo("is_delete","1");
        return buildSuccess(new JsonResult(),roleMapper.selectByExample(example));
    }

    @RequestMapping("/add")
    @ResponseBody
    public JsonResult list(Role role){
        return buildSuccess(new JsonResult(),roleMapper.insertSelective(role));
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(Role role){
        return buildSuccess(new JsonResult(),roleMapper.updateByPrimaryKeySelective(role));
    }

    @RequestMapping("/get/{pkRoleId}")
    @ResponseBody
    public JsonResult get(@PathVariable("pkRoleId")Integer pkRoleId){
        return buildSuccess(new JsonResult(),roleMapper.selectByPrimaryKey(pkRoleId));
    }

}
