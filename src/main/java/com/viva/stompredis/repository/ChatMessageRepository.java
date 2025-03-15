package com.viva.stompredis.repository;

import com.viva.stompredis.entity.ChatMessage;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByRoomIdOrderByCreatedAtDesc(Long roomId, Pageable pageable);
    List<ChatMessage> findByRoomIdOrderByCreatedAtAsc(Long roomId);
}
