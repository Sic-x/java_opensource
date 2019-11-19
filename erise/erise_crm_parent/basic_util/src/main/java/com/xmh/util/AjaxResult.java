package com.xmh.util;

public class AjaxResult {
    private boolean success = true;
    private String message = "成功";
    private Object resultObj ;

    public boolean isSuccess() {
        return success;
    }

    public AjaxResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResultObj() {
        return resultObj;
    }

    //默认成功
    public AjaxResult() {
    }
    //失败调用
    public AjaxResult(String message) {
        this.success = false;
        this.message = message;
    }


    public static AjaxResult me(){
        return new AjaxResult();
    }

    //设置返回的对象
    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

}
