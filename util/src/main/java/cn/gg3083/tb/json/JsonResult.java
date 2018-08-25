package cn.gg3083.tb.json;


import cn.gg3083.tb.util.ResourceAPI;

import java.io.Serializable;

/**
 * json 响应返回的数据
 */

/**
 * @author GG
 *
 * @param <T>
 */
@SuppressWarnings("all")
public class JsonResult implements Serializable {

	private int code;

	private String msg;

	private Object obj;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public JsonResult() {
	}

	public JsonResult(Integer code){
		this.code=code;
	}

	public JsonResult(String msg) {
		this.msg = msg;
	}

	public JsonResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


	public JsonResult(int code, String msg,Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}

	public JsonResult operSuccess(){
		JsonResult json=new JsonResult();
		json.setCode(ResourceAPI.SUCCESS_CODE);
		return json;
	}

	public JsonResult operError(){
		JsonResult json=new JsonResult();
		json.setCode(ResourceAPI.ERROR_CODE);
		return json;
	}

	public JsonResult operError(String msg){
		JsonResult json=new JsonResult();
		json.setCode(ResourceAPI.ERROR_CODE);
		json.setMsg(msg);
		return json;
	}

	@Override
	public String toString() {
		return "JsonResult [code=" + code + ", msg=" + msg + ", obj=" + obj + "]";
	}

}
