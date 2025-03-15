package com.viva.stompredis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageDto {

    private Long roomId;
    private String sender;
    private String content;
    private MessageType type;

    public enum MessageType {
        ENTER, TALK
    }

    @Builder
    public ChatMessageDto(Long roomId, String sender, String content, MessageType type) {
        this.roomId = roomId;
        this.sender = sender;
        this.content = content;
        this.type = type;
    }
}