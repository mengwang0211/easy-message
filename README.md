### Easy-message 

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.mengwang0211/easy-message/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.mengwang0211/easy-message/)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

#### 消息推送组件

##### 完成功能

    1. 阿里云短信、语音
    2. email

#### 用法

##### pom.xml引入[已上传到中央仓库]

    <dependency>
        <groupId>io.github.mengwang0211</groupId>
        <artifactId>easy-message</artifactId>
        <version>1.0.1</version>
    </dependency>    
    
    
##### application.yml

```yaml
aliyun:
  # sms profile
  sms:
    accessKeyId:
    accessKeySecret:
    signName:
    templateCode:
  # call phone profile
  phone:
    # accessKeyId
    accessKeyId:
    # accessKeySecret
    accessKeySecret:
    # ttsCode 模板编号
    ttsCode:
    # 主叫号码
    calledShowNumber:
# email profile
email:
   username:
   password:
   host:  


```     
                
###### 发送消息

####### 发送短信

    
    @Autowired
    SMSMessagePusher sMSMessagePusher;
    
    sMSMessagePusher.sendMsg(messageEntity);
    

####### 发送email

    @Autowired
    EmailMessagePusher emailMessagePusher;
    
    emailMessagePusher.sendMsg(messageEntity);
    

####### 打电话

    @Autowired
    AliyunPhoneMessagePusher aliyunPhoneMessagePusher;
    
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setPhoneTtsCode("XXXXX");
    messageEntity.setPhoneNumber("21231324");
    messageEntity.setTemplatePhoneParam(new HashMap());
    aliyunPhoneMessagePusher.sendMsg(messageEntity); 

    
                

        