package com.viva.stompredis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viva.stompredis.entity.ChatRoom;
import com.viva.stompredis.repository.ChatMessageRepository;
import com.viva.stompredis.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @GetMapping("/chat/rooms")
    public String rooms(Model model) {
        model.addAttribute("rooms", chatRoomRepository.findAll());
        return "chat/rooms";
    }

    @PostMapping("/chat/room")
    public String createRoom(@RequestParam String name) {
        ChatRoom chatRoom = ChatRoom.builder()
            .name(name)
            .creator("임시사용자")      // 임시 데이터
            .targetUser("테스트유저")   // 임시 데이터
            .build();
        chatRoomRepository.save(chatRoom);
        return "redirect:/view/chat/rooms";
    }

    @GetMapping("/chat/room/{roomId}")
    public String getRoom(@PathVariable Long roomId, Model model) {
        ChatRoom room = chatRoomRepository.findById(roomId)
            .orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));
        model.addAttribute("room", room);
        model.addAttribute("messages", chatMessageRepository.findByRoomIdOrderByCreatedAtAsc(roomId));
        return "chat/room";
    }
}