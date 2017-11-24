package com.spring.jpa.services;


import com.spring.jpa.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService
{
    Message save(Message message);
    void deleteMessage(Long id);
    List<Message> getAll();
    Message getById(Long id);
}
