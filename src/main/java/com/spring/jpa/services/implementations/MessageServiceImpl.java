package com.spring.jpa.services.implementations;


import com.spring.jpa.domain.Message;
import com.spring.jpa.repository.MessageRepository;
import com.spring.jpa.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MessageServiceImpl implements MessageService
{
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(Message message)
    {
        Message savedMessage = messageRepository.saveAndFlush(message);
        return savedMessage;
    }

    @Override
    public void deleteMessage(Long id)
    {
        messageRepository.delete(id);
    }

    @Override
    public List<Message> getAll()
    {
        return messageRepository.findAll();
    }

    @Override
    public Message getById(Long id)
    {
        return messageRepository.findOne(id);
    }
}
