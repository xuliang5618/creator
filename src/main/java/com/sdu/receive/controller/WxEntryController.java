package com.sdu.receive.controller;

import com.sdu.receive.context.impl.DefaultSession;
import com.sdu.receive.service.WxMessageReceiveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * @author xuliang
 */
@Controller
@RequestMapping("entry")
public class WxEntryController {

    public static Logger logger = LogManager.getLogger(WxEntryController.class);

    @Autowired
    private WxMessageReceiveService wxMessageReceiveService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void test(HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
        logger.debug("==================enter test==================");
        String echostr = request.getParameter("echostr");// 随机字符串
        Writer out = response.getWriter();
        out.write(echostr);
        out.flush();
        out.close();
        logger.debug("==================exist test==================");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void receive(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        logger.debug("==================enter receive==================");
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        validate(signature, timestamp, nonce);
        OutputStream os = response.getOutputStream();
        wxMessageReceiveService.handle(new DefaultSession(request.getInputStream(),os));
        logger.debug("==================exist receive==================");
    }

    private boolean validate(String signature, String timestamp, String nonce) {
        //todo 微信来源的校验
        return true;
    }
}
