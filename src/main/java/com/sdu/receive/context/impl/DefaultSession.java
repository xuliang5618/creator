package com.sdu.receive.context.impl;

import com.sdu.common.utils.Utils;
import com.sdu.receive.context.Session;

import java.io.*;

import com.sdu.receive.domain.common.WXXmlElementName;
import com.sdu.receive.domain.msg.Msg4Head;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * 默认Session，扩展要保存的上下文信息
 *
 * @author xuliang
 */
public class DefaultSession extends Session {
    public DefaultSession(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
        try {
            message = generateMessage(inputStream);
            byte[] bytes = message.getBytes("utf-8");
            inputStream = new ByteArrayInputStream(bytes);
            document = Utils.getDocumentBuilder().parse(inputStream);
            head = new Msg4Head();
            head.read(document);
        } catch (UnsupportedEncodingException e) {

        } catch (IOException e) {

        } catch (SAXException e) {

        }
    }

    /**
     * 消息头
     */
    private Msg4Head head;

    /**
     * 消息文档
     */
    private Document document;

    /**
     * 消息
     */
    private String message;

    public Msg4Head getHead() {
        return head;
    }

    public void setHead(Msg4Head head) {
        this.head = head;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String generateMessage(InputStream inputStream) {

        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String tmp = br.readLine();
            while (null != tmp) {
                sb.append(tmp);
                tmp = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {

                    br.close();
                }
            } catch (IOException e) {
                br = null;
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

            } catch (IOException e) {
                inputStream = null;
            }
        }
        return sb.toString();
    }
}
