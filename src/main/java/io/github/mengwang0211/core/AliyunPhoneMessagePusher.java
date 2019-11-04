package io.github.mengwang0211.core;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsRequest;
import com.aliyuncs.dyvmsapi.model.v20170525.SingleCallByTtsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import io.github.mengwang0211.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Aliyun phone message pusher
 */
@Slf4j
public class AliyunPhoneMessagePusher implements IMessage{

    @Value("${aliyun.phone.accessKeyId}")
    private String accessKeyIdPro;

    @Value("${aliyun.phone.accessKeySecret}")
    private String accessKeySecretPro;

    @Value("${aliyun.phone.ttsCode}")
    private String ttsCode;

    @Value("${aliyun.phone.calledShowNumber}")
    private String calledShowNumber;

    @Override
    public String sendMsg(MessageEntity messageEntity){
        try {
            this.phone(messageEntity);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return "ok";
    }

    @Override
    public String sendMsgBatch(List<MessageEntity> messageEntities) {
        messageEntities.stream().forEach(messageEntity -> {
            try {
                this.phone(messageEntity);
            }catch (Exception e){
                log.error(e.getMessage());
            }
        });
        return "ok";
    }

    private void phone(MessageEntity messageEntity) throws Exception{
        //设置访问超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //云通信产品-语音API服务产品名称（产品名固定，无需修改）
        final String product = "Dyvmsapi";
        //产品域名（接口地址固定，无需修改）
        final String domain = "dyvmsapi.aliyuncs.com";
        //AK信息
        final String accessKeyId = accessKeyIdPro;
        final String accessKeySecret = accessKeySecretPro;
        //初始化acsClient 暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SingleCallByTtsRequest request = new SingleCallByTtsRequest();
        //必填-被叫显号,可在语音控制台中找到所购买的显号
        request.setCalledShowNumber(calledShowNumber);
        //必填-被叫号码
        request.setCalledNumber(messageEntity.getPhoneNumber());
        //必填-Tts模板ID
        request.setTtsCode(messageEntity.getPhoneTtsCode());
        //可选-当模板中存在变量时需要设置此值
        request.setTtsParam(JSON.toJSONString(messageEntity.getTemplatePhoneParam()));
        //可选-音量 取值范围 0--200
        request.setVolume(100);
        //可选-播放次数
        request.setPlayTimes(3);
        //可选-外部扩展字段,此ID将在回执消息中带回给调用方
        //request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SingleCallByTtsResponse singleCallByTtsResponse = acsClient.getAcsResponse(request);
        if(singleCallByTtsResponse.getCode() != null && singleCallByTtsResponse.getCode().equals("OK")) {
            //请求成功
            log.info("语音文本外呼---------------");
            log.info("RequestId=" + singleCallByTtsResponse.getRequestId());
            log.info("Code=" + singleCallByTtsResponse.getCode());
            log.info("Message=" + singleCallByTtsResponse.getMessage());
            log.info("CallId=" + singleCallByTtsResponse.getCallId());
        }
    }
}
