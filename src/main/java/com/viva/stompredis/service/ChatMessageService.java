package com.viva.stompredis.service;

import com.viva.stompredis.entity.ChatMessage;
import com.viva.stompredis.repository.ChatMessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository messageRepository;

    // 채팅 메시지 저장
    public ChatMessage saveMessage(ChatMessage message) {
        return messageRepository.save(message);
    }

    // 채팅방의 이전 메시지 조회 (최근 50개)
    public List<ChatMessage> getRecentMessages(Long roomId) {
        return messageRepository.findByRoomIdOrderByCreatedAtDesc(roomId, PageRequest.of(0, 50));
    }
}