package com.viva.stompredis.service;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final RedisTemplate<String, String> redisTemplate;
    private static final String CHAT_USERS = "CHAT_ROOM_USERS:";

    // 채팅방 입장
    public void enterChatRoom(Long roomId, String userId) {
        String key = CHAT_USERS + roomId;
        redisTemplate.opsForSet().add(key, userId);
    }

    // 채팅방 퇴장
    public void leaveChatRoom(Long roomId, String userId) {
        String key = CHAT_USERS + roomId;
        redisTemplate.opsForSet().remove(key, userId);
    }

    // 채팅방 사용자 목록 조회
    public Set<String> getChatRoomUsers(Long roomId) {
        String key = CHAT_USERS + roomId;
        return redisTemplate.opsForSet().members(key);
    }
}