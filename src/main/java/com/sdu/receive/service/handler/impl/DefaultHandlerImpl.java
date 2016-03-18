package com.sdu.receive.service.handler.impl;

import com.sdu.common.utils.Utils;
import com.sdu.receive.ReceiveUtils;
import com.sdu.receive.context.Session;
import com.sdu.receive.context.impl.DefaultSession;
import com.sdu.receive.domain.msg.Msg;
import com.sdu.receive.domain.msg.Msg4Text;
import com.sdu.receive.service.handler.IHandler;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

/**
 * 默认处理
 */
@Service("defaultHandlerImpl")
public class DefaultHandlerImpl implements IHandler {
    @Override
    public boolean validate(Session session) {
        return true;
    }

    @Override
    public void handle(Session session) {
        DefaultSession defaultSession = (DefaultSession) session;
        Msg4Text msg = new Msg4Text(defaultSession.getHead());
        String fromUserName = msg.getFromUserName();
        String toUserName = msg.getToUserName();
        msg.setFromUserName(toUserName);
        msg.setToUserName(fromUserName);
        msg.read(defaultSession.getDocument());
        msg.getHead().setMsgType(Msg.MSG_TYPE_TEXT);
        msg.setContent("hello word");
        Document document = Utils.getDocumentBuilder().newDocument();
        msg.write(document);
        ReceiveUtils.sendMessage(document, session.getOutputStream());
    }
}
