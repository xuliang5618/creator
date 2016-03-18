package com.sdu.receive.service;

import com.sdu.receive.context.Session;
import com.sdu.receive.service.handler.IHandler;

import java.util.List;

/**
 * 默认session
 *
 * @author xuliang
 */
public class WxMessageReceiveService {
    /**
     * 监听器集合
     */
    private List<IHandler> handlers;

    /**
     * 处理函数
     *
     * @param session
     */
    public void handle(Session session) {
        for (IHandler handler : handlers)
            if (handler.validate(session)) {
                handler.handle(session);
                break;
            }
    }

    public List<IHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<IHandler> handlers) {
        this.handlers = handlers;
    }
}
