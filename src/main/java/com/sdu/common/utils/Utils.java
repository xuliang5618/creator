package com.sdu.common.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

/**
 * @author xuliang
 */
public class Utils {
    public static Logger logger = LogManager.getLogger(Utils.class);
    /**
     * Document构建类
     */
    private static DocumentBuilder builder;
    private static TransformerFactory tffactory;

    static {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        //格式化工厂对象
        tffactory = TransformerFactory.newInstance();
    }

    /**
     * 获取document
     *
     * @return
     */
    public static DocumentBuilder getDocumentBuilder() {
        return builder;
    }

    /**
     * 获取Transformer
     *
     * @return
     */
    public static Transformer getTransformer() throws TransformerConfigurationException {
        Transformer transformer = null;
        try {
            transformer=tffactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            logger.error("啥情况", e);
            throw e;
        }
        return transformer;
    }
}
