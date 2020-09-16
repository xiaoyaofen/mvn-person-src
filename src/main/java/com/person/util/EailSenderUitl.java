package com.person.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

//邮件发送
public class EailSenderUitl {

    @Autowired
    private static JavaMailSender javaMailSender;

    private static EailSenderUitl eailSenderUitl;
    private EailSenderUitl(){ }

    public static EailSenderUitl getIntanse(){
        if(null == eailSenderUitl){
            synchronized (eailSenderUitl){
                if(null == eailSenderUitl){
                    eailSenderUitl = new EailSenderUitl();
                }
            }
        }
        return eailSenderUitl;
    }


    public static void sendMail(String title, String content,String form, String email) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(form); // 发送人的邮箱
            helper.setSubject(title); //标题
            helper.setTo(email); //发给谁  对方邮箱
            helper.setText(content,true); //内容,true支持html内容，false支持纯文本内容
            javaMailSender.send(message); //发送
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
