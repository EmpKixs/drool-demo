package com.kixs.drool.message;

import lombok.Data;

/**
 * TODO 功能描述
 *
 * @author wangbing
 * @version v1.0.0
 * @since 2021/4/7 14:10
 */
@Data
public class Message {

    public static final Integer HELLO = 0;

    public static final Integer GOODBYE = 1;

    private String message;

    private Integer status;

    private boolean greeted;

    private boolean goodbyed;

    private int param;

    private int result;

}
