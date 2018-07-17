package com.gg3083.tb.common.controller;


import com.gg3083.tb.json.JsonResult;
import com.gg3083.tb.util.ResourceAPI;

public class BaseController {


	/*
	 * @Resource protected ReadPropertyPlaceholder readPropertyPlaceholder;
	 */

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String ERRORCODE = "500";

	/**
	 * @功能: 所有的子类方法执行之前都要先执行此方法，子类方法不需要在model此方法中的参数 aop
	 * @作者: 柯栋 @代号:隐无为
	 * @param model
	 */
	/*
	 * @ModelAttribute public void init(Model model) { model.addAttribute("webPath",
	 * readPropertyPlaceholder.getProperty("sys.webPath"));
	 * model.addAttribute("imgPath",
	 * readPropertyPlaceholder.getProperty("sys.imgPath"));
	 * model.addAttribute("webRoot",
	 * readPropertyPlaceholder.getProperty("sys.webRoot")); }
	 */

	/**
	 * @功能: 异常统一捕获
	 * @作者: 柯栋 @代号:隐无为
	 * @时间: 2017年2月28日
	 */
	/*
	 * @ExceptionHandler
	 * 
	 * @ResponseBody public String exceptionProcess(HttpServletRequest request,
	 * HttpServletResponse response, RuntimeException ex) {
	 * 
	 * JsonResult<String> jsonReturnData = new JsonResult<String>(500, "系统出错");
	 * return responseAPI.getJsonString(jsonReturnData); }
	 */


	public JsonResult buildSuccess(JsonResult jsonResult){
		jsonResult.setCode(ResourceAPI.SUCCESS_CODE);
		return jsonResult;
	}
}
