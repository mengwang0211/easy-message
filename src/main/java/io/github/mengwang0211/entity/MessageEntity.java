package io.github.mengwang0211.entity;

import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Message entity
 */
@Data
public class MessageEntity {

    /****----      sms param start                 --------*/

    /**
     * Template code
     */
    String templateCode;

    /**
     * Template param
     */
    Map<String, String> templateParam;

    /**
     * Contacts
     */
    List<String> contacts;

    /****----      sms param end                 --------*/

    /****----      email param start                 --------*/

    /**
     * Email subject
     */
    String emailSubject;

    /**
     * Email text
     */
    String emailText;

    /**
     * Address
     */
    String address;

    /****----      email param end                 --------*/

    /****----      call phone param start                 --------*/

    /**
     * Phone tts code
     */
    String phoneTtsCode;

    /**
     * Phone number 被叫号码 [主叫号码配置中配置]
     */
    String phoneNumber;

    /**
     * Template phone param
     */
    Map<String, String> templatePhoneParam;

    /****----      call phone param end                 --------*/
}
