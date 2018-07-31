package com.hugo.wanandroid.base;

/**
 * @author 作者：hugo
 * @date 时间：2018/7/19.
 * 版本：v1.0
 * 描述：基础数据类
 */
public class BaseResponse<T> {

    public static final int SUCCESS = 0;

    /**
     * 0：成功，errorCode如果为负数则认为错误
     */
    private int errorCode = -1;

    private String errorMsg;

    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
