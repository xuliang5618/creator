package com.sdu.receive.service.handler.impl;

import com.sdu.common.utils.Utils;
import com.sdu.receive.ReceiveUtils;
import com.sdu.receive.context.Session;
import com.sdu.receive.context.impl.DefaultSession;
import com.sdu.receive.domain.msg.*;
import com.sdu.receive.service.handler.IHandler;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

/**
 * 声音消息处理
 *
 * @author xuliang
 */
@Service("voiceHandlerImpl")
public class VoiceHandlerImpl implements IHandler {

    @Override
    public boolean validate(Session session) {
        if (session instanceof DefaultSession) {
            DefaultSession defaultSession = (DefaultSession) session;
            if (Msg.MSG_TYPE_VOICE.equals(defaultSession.getHead().getMsgType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handle(Session session) {
        DefaultSession defaultSession = (DefaultSession) session;
        Msg4Voice msg = new Msg4Voice(defaultSession.getHead());
        String fromUserName = msg.getFromUserName();
        String toUserName = msg.getToUserName();
        Msg4Text msg4Text = new Msg4Text();
        msg4Text.setFromUserName(toUserName);
        msg4Text.setToUserName(fromUserName);
        msg4Text.getHead().setMsgType(Msg.MSG_TYPE_TEXT);
        msg4Text.setContent("我听不见");
        Document document = Utils.getDocumentBuilder().newDocument();
        msg4Text.write(document);
        ReceiveUtils.sendMessage(document, session.getOutputStream());
    }
}
