package com.gg3083.tb.common.controller;


import com.gg3083.tb.common.exception.GGException;
import com.gg3083.tb.json.JsonResult;
import com.gg3083.tb.util.ResourceAPI;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {


    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String ERRORCODE = "500";

    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e) {
        return buildError(new JsonResult(), e.getMessage());
    }

    @ExceptionHandler(GGException.class)
    public JsonResult handleggException(GGException e) {
        return buildError(new JsonResult(), e.getMessage());
    }

    public JsonResult buildSuccess(JsonResult jsonResult) {
        jsonResult.setCode(ResourceAPI.SUCCESS_CODE);
        return jsonResult;
    }

    public JsonResult buildSuccess(JsonResult jsonResult, Object obj) {
        jsonResult.setCode(ResourceAPI.SUCCESS_CODE);
        jsonResult.setObj(obj);
        return jsonResult;
    }

    public JsonResult buildError(JsonResult jsonResult) {
        jsonResult.setCode(ResourceAPI.ERROR_CODE);
        return jsonResult;
    }

    public JsonResult buildError(JsonResult jsonResult, String msg) {
        jsonResult.setCode(ResourceAPI.ERROR_CODE);
        jsonResult.setMsg(msg);
        return jsonResult;
    }
}
