package io.github.mengwang0211.core;

import cn.javaer.aliyun.sms.SmsTemplate;
import io.github.biezhi.ome.OhMyEmail;
import io.github.biezhi.ome.SendMailException;
import io.github.mengwang0211.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Properties;

/**
 * Email message pusher
 */
@Slf4j
public class EmailMessagePusher implements IMessage{

    @Value("${email.host}")
    private String emailHost;

    @Value("${email.username}")
    private String emailUsername;

    @Value("${email.password}")
    private String emailPassword;

    /**
     * Init email
     */
    public void initEmail(){
        Properties props = OhMyEmail.defaultConfig(false);
        props.put("mail.smtp.host", emailHost);
        OhMyEmail.config(props, emailUsername, emailPassword);
    }


    @Override
    public boolean sendMsg(MessageEntity messageEntity) {
        this.initEmail();
        try {
            OhMyEmail.subject(messageEntity.getEmailSubject())
                    .from(emailUsername)
                    .to(messageEntity.getAddress())
                    .text(messageEntity.getEmailText())
                    .send();
        } catch (SendMailException e) {
            log.info(e.getMessage());
            return false;
        }
        return true;
    }
}
