package com.geekerstar.basic.domain.enums;

import lombok.Getter;

/**
 * @author geekerstar
 * @date 2021/8/14 20:18
 * @description
 */
@Getter
public enum ResultEnum {

    UNKNOWN_EXCEPTION(100, "未知异常"),
    FORMAT_ERROR(101, "参数格式错误"),
    TIME_OUT(102, "超时"),
    ADD_ERROR(103, "添加失败"),
    UPDATE_ERROR(104, "更新失败"),
    DELETE_ERROR(105, "删除失败"),
    GET_ERROR(106, "查找失败"),
    ARGUMENT_TYPE_MISMATCH(107, "参数类型不匹配"),
    REQ_METHOD_NOT_SUPPORT(110, "请求方式不支持"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum getByCode(int code) {
        for (ResultEnum resultEnum : ResultEnum.values()) {
            if (code == resultEnum.getCode()) {
                return resultEnum;
            }
        }
        return null;
    }
}
