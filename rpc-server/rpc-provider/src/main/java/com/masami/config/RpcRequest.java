package com.masami.config;

import lombok.Data;

import java.io.Serializable;

/**
 * Date:2019/8/20
 * Author:gyc
 * Desc:
 */
@Data
public class RpcRequest implements Serializable {

    private String className;

    private String methodName;

    private Object[] args;

}
