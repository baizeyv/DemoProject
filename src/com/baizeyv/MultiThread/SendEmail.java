package com.baizeyv.MultiThread;

import java.util.concurrent.Callable;

public class SendEmail implements Callable<String> {

    private String targetEmail;

    private String codeContent;

    public SendEmail(String targetEmail, String codeContent) {
        this.targetEmail = targetEmail;
        this.codeContent = codeContent;
    }

    public String process() throws Exception {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread() + " |&| " + "Send E-mail To: " + targetEmail + " E-mail Content: " + codeContent);
        return targetEmail;
    }

    @Override
    public String call() throws Exception {
        return process();
    }

    public String getTargetEmail() {
        return targetEmail;
    }

    public void setTargetEmail(String targetEmail) {
        this.targetEmail = targetEmail;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }
}
