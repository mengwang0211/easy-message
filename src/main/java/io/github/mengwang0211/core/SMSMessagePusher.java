package io.github.mengwang0211.core;

import cn.javaer.aliyun.sms.SmsClient;
import cn.javaer.aliyun.sms.SmsTemplate;
import io.github.mengwang0211.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Sms message pusher
 */
public class SMSMessagePusher implements IMessage {

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.signName}")
    private String signName;


    @Override
    public String sendMsg(MessageEntity messageEntity) {
        SmsClient smsClient = new SmsClient(accessKeyId, accessKeySecret);
        SmsTemplate smsTemplate = SmsTemplate.builder()
                .signName(signName)
                .templateCode(messageEntity.getTemplateCode())
                .phoneNumbers(messageEntity.getContacts())
                .build();
        smsTemplate.setTemplateParam(messageEntity.getTemplateParam());
        smsClient.send(smsTemplate);
        return "ok";
    }

    @Override
    public String sendMsgBatch(List<MessageEntity> messageEntities) {
        SmsClient smsClient = new SmsClient(accessKeyId, accessKeySecret);
        messageEntities.stream().forEach(messageEntity -> {
            SmsTemplate smsTemplate = SmsTemplate.builder()
                    .signName(signName)
                    .templateCode(messageEntity.getTemplateCode())
                    .phoneNumbers(messageEntity.getContacts())
                    .build();
            smsTemplate.setTemplateParam(messageEntity.getTemplateParam());
            smsClient.send(smsTemplate);
        });
        return "ok";
    }
}
