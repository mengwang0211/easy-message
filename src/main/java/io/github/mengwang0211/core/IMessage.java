package io.github.mengwang0211.core;

import io.github.mengwang0211.entity.MessageEntity;

import java.util.Collection;
import java.util.List;

/**
 * 发送
 */
public interface IMessage {

    /**
     * Send msg string
     *
     * @param messageEntity message entity
     * @return the string
     */
    boolean sendMsg(MessageEntity messageEntity);
}
