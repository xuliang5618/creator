package com.sdu.receive;

import com.sdu.common.utils.Utils;
import org.w3c.dom.Document;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * receive包下的公共函数
 *
 * @author xuliang
 */
public class ReceiveUtils {
    /**
     * 自动回复，给微信服务器发消息
     *
     * @param document
     * @param outputStream
     */
    public static void sendMessage(Document document , OutputStream outputStream) {
        try {
            Transformer transformer = Utils.getTransformer();
            transformer.transform(new DOMSource(document), new StreamResult(new OutputStreamWriter(outputStream, "utf-8")));
        } catch (Exception e) {
            e.printStackTrace();// 保存dom至目输出流
        }
    }
}
