package com.geekerstar.basic.exception;

/**
 * @author geekerstar
 * @date 2021/8/14 19:09
 * @description
 */
public class CommonException {

    public static final BusinessException SYS_ERROR = new BusinessException("A0005", "系统内部异常");
    public static final BusinessException ERROR_REQUEST = new BusinessException("A0001", "无效的请求");
    public static final BusinessException ADDRESS_ALREADY_IN_USE = new BusinessException("A0005", "端口占用");


    public static final BusinessException HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION = new BusinessException("G10001", "请求方式不支持");
    public static final BusinessException METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION = new BusinessException("G10001", "参数格式错误");
    public static final BusinessException HTTP_MESSAGE_NOT_READABLE_EXCEPTION = new BusinessException("G10001", "参数格式错误");

    public static final BusinessException RATE_LIMIT_EXCEPTION = new BusinessException("G10001", "手速太快了，慢点儿吧~");


}
