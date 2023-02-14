package org.pms.vo;

/**
 * 服务器响应客户端消息的封装  -- 前端与后端交互的统一规范
 *
 * @Date 2023-02-14
 * @Author zqx
 */
public class ResponseData {
    /**
     * 消息代码，默认200
     */
    private int code = 200 ;

    /**
     * 客户端消息
     */
    private String msg ;

    /**
     * 返回客户端具体的数据结果
     */
    private Object data ;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
