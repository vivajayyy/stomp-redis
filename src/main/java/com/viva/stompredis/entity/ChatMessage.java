package com.viva.stompredis.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Document(collection = "chat_messages")
@NoArgsConstructor
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK
    }

    @Id
    private String id;
    private MessageType type;
    private Long roomId;
    private String sender;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public ChatMessage(MessageType type, Long roomId, String sender, String content) {
        this.type = type;
        this.roomId = roomId;
        this.sender = sender;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}