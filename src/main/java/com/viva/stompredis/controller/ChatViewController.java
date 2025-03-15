package com.viva.stompredis.controller;

import com.viva.stompredis.entity.ChatRoom;
import com.viva.stompredis.repository.ChatMessageRepository;
import com.viva.stompredis.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ChatViewController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @GetMapping("/")
    public String index() {
        return "redirect:/view/chat/rooms";
    }

    @GetMapping("/view/chat/rooms")
    public String rooms(Model model) {
        model.addAttribute("rooms", chatRoomRepository.findAll());
        return "chat/rooms";
    }

    @GetMapping("/view/chat/room/{roomId}")
    public String room(@PathVariable Long roomId, Model model) {
        ChatRoom room = chatRoomRepository.findById(roomId)
            .orElseThrow(() -> new RuntimeException("채팅방을 찾을 수 없습니다."));
        model.addAttribute("room", room);
        model.addAttribute("messages", chatMessageRepository.findByRoomIdOrderByCreatedAtAsc(roomId));
        return "chat/room";
    }
}