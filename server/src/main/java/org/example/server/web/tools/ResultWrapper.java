package org.example.server.web.tools;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultWrapper<T> {
    private Integer code;
    private String message;
    private T data;

    public static final int DEFAULT_SUCCESS = 0;
    public static final int DEFAULT_ERROR = 1;

    private ResultWrapper(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 返回一个成功的包装器
     * @return wrapper
     */
    public static <T> ResultWrapper<T> success() {
        return new ResultWrapper<>(DEFAULT_SUCCESS, "success", null);
    }

    /**
     * 返回一个标识成功的包装器
     * @param message 响应信息
     * @param data 响应数据
     * @return wrapper
     */
    public static <T> ResultWrapper<T> success(String message, T data) {
        return new ResultWrapper<>(DEFAULT_SUCCESS, message, data);
    }

    /**
     * 返回一个标识成功的包装器
     * @param data 响应数据
     * @return wrapper
     */
    public static <T> ResultWrapper<T> success(T data) {
        return new ResultWrapper<>(DEFAULT_SUCCESS, "success", data);
    }

    /**
     * 返回一个标识失败的包装器
     * @return wrapper
     */
    public static <T> ResultWrapper<T> error() {
        return new ResultWrapper<>(DEFAULT_ERROR, "success", null);
    }

    /**
     * 返回一个标识失败的包装器
     * @param message 响应信息
     * @param data 响应数据
     * @return wrapper
     */
    public static <T> ResultWrapper<T> error(String message, T data) {
        return new ResultWrapper<>(DEFAULT_ERROR, message, data);
    }
}
