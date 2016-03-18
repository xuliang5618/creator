package com.sdu.receive.service.handler.impl;

import com.sdu.common.utils.Utils;
import com.sdu.receive.ReceiveUtils;
import com.sdu.receive.context.Session;
import com.sdu.receive.context.impl.DefaultSession;
import com.sdu.receive.domain.msg.Msg;
import com.sdu.receive.domain.msg.Msg4Image;
import com.sdu.receive.domain.msg.Msg4Text;
import com.sdu.receive.service.handler.IHandler;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

/**
 * 图片消息处理
 *
 * @author xuliang
 */
@Service("imageHandlerImpl")
public class ImageHandlerImpl implements IHandler {

    @Override
    public boolean validate(Session session) {
        if (session instanceof DefaultSession) {
            DefaultSession defaultSession = (DefaultSession) session;
            if (Msg.MSG_TYPE_IMAGE.equals(defaultSession.getHead().getMsgType())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void handle(Session session) {
        DefaultSession defaultSession = (DefaultSession) session;
        Msg4Image msg = new Msg4Image(defaultSession.getHead());
        String fromUserName = msg.getFromUserName();
        String toUserName = msg.getToUserName();
        Msg4Text msg4Text = new Msg4Text();
        msg4Text.setFromUserName(toUserName);
        msg4Text.setToUserName(fromUserName);
        msg4Text.getHead().setMsgType(Msg.MSG_TYPE_TEXT);
        msg4Text.setContent("请不要发图片");
        Document document = Utils.getDocumentBuilder().newDocument();
        msg4Text.write(document);
        ReceiveUtils.sendMessage(document, session.getOutputStream());
    }
}
