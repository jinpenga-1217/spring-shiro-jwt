package ssj.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回的对象
 */
public class Result {

    private int code;
    // 提示信息
    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setCode(400);
        result.setMessage("fail");
        return result;
    }

    public static Result noPermission() {
        Result result = new Result();
        result.setCode(401);
        result.setMessage("no permission");
        return result;
    }

    public static Result code(int code){
        Result result = new Result();
        result.setCode(code);
        result.setMessage("exception");
        return result;
    }

    public Result add(String key, Object value) {
        this.data.put(key, value);
        return this;
    }



}