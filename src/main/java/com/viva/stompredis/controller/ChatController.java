package com.viva.stompredis.controller;

import com.viva.stompredis.dto.ChatMessageDto;
import com.viva.stompredis.entity.ChatMessage;
import com.viva.stompredis.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations sendingOperations;
    private final ChatMessageRepository chatMessageRepository;

    @GetMapping("/chat/room/{roomId}")
    public String chatRoom(@PathVariable Long roomId, Model model) {
        model.addAttribute("roomId", roomId);
        model.addAttribute("messages", chatMessageRepository.findByRoomIdOrderByCreatedAtAsc(roomId));
        return "chat/room";
    }

    @MessageMapping("/chat/message")
    public void sendMessage(ChatMessageDto message) {
        ChatMessage chatMessage = ChatMessage.builder()
            .type(ChatMessage.MessageType.valueOf(message.getType().name()))
            .roomId(message.getRoomId())
            .sender(message.getSender())
            .content(message.getType() == ChatMessageDto.MessageType.ENTER ?
                message.getSender() + "님이 입장하셨습니다." :
                message.getContent())
            .build();

        // MongoDB에 저장
        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        // 클라이언트에게 발송
        sendingOperations.convertAndSend("/sub/chat/room/" + message.getRoomId(), savedMessage);
    }
}