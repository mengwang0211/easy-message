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

    /**
     * Phone tts code
     */
    String phoneTtsCode;

    /**
     * Phone number
     */
    String phoneNumber;

    /**
     * Template phone param
     */
    Map<String, String> templatePhoneParam;
}
