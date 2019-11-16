package com.xmh.aisell.common;

/**
 * 删除返回的信息
 */
public class JsonResult {
    //代表是否成功
    private boolean success = true;
    //代表响应的信息 一般是错误信息
    private String msg;
    //判断是添加跳转还是更新跳转
    private boolean add = false;
    public JsonResult() {
    }

    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }
}
