package com.awslabplatform.util;

import com.awslabplatform.common.message.Message;
import com.awslabplatform.entity.Result;


/**
 * 消息返回工具类
 * @author weixin
 * @version 2018-3-21
 */
public class ResultUtil {
    /**
     * 数据交互返回(1、枚举消息返回2、带返回数据)
     * @param resultFlag  结果标志(成功、true）/（失败false)
     * @param message 提示消息
     * @param object 返回数据
     * @return
     */



    public static Result getResult(Boolean resultFlag, Message message, Object object){
        if(object==null){
            object = "";
        }
        return new Result(resultFlag, message.getCode(), message.getContent(), object);
    }

    /**
     * 数据交互返回(1、枚举消息返回2、不带返回数据)
     * @param resultFlag  结果标志(成功、true）/（失败false)
     * @param message 提示消息
     * @param object 返回数据
     * @return
     */
    public static Result getResult(Boolean resultFlag, Message message){
        return new Result(resultFlag, message.getCode(), message.getContent());
    }

    /**
     * 数据交互返回(1、参数校验2、带返回数据)
     * @param resultFlag  结果标志(成功、true）/（失败false)
     * @param message 提示消息
     * @param object 返回数据
     * @return
     */
    public static Result getResult(Boolean resultFlag, String message, Object object){
        return new Result(resultFlag, message, object);
    }

    /**
     * 数据交互返回(1、参数校验2、不带返回数据)
     * @param resultFlag  结果标志(成功、true）/（失败false)
     * @param message 提示消息
     * @return
     */
    public static Result getResult(Boolean resultFlag, String message){
        return new Result(resultFlag, message);
    }
}
