package com.sdu.receive.service.handler;

import com.sdu.receive.context.Session;

/**
 * session处理接口定义
 */

public interface IHandler {
    /**
     * 是否处理校验
     *
     * @param session
     * @return
     */
    boolean validate(Session session);

    /**
     * 处理函数
     *
     * @param session
     */
    void handle(Session session);
}
