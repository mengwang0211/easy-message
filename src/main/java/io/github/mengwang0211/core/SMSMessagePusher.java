package io.github.mengwang0211.core;

import cn.javaer.aliyun.sms.SmsClient;
import cn.javaer.aliyun.sms.SmsTemplate;
import io.github.mengwang0211.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Sms message pusher
 */
@Slf4j
public class SMSMessagePusher implements IMessage {

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.signName}")
    private String signName;


    @Override
    public boolean sendMsg(MessageEntity messageEntity) {
        try {
            SmsClient smsClient = new SmsClient(accessKeyId, accessKeySecret);
            SmsTemplate smsTemplate = SmsTemplate.builder()
                    .signName(signName)
                    .templateCode(messageEntity.getTemplateCode())
                    .phoneNumbers(messageEntity.getContacts())
                    .build();
            smsTemplate.setTemplateParam(messageEntity.getTemplateParam());
            smsClient.send(smsTemplate);
        }catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
        return true;
    }
}
