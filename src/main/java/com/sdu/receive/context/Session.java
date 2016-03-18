package com.sdu.receive.context;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理上下文
 *
 * @author xuliang
 */
public abstract class Session {
    public Session(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    /**
     * 输入流
     */
    protected InputStream inputStream;

    /**
     * 输出流
     */
    protected OutputStream outputStream;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
