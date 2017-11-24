package com.spring.jpa.services.implementations;


import com.spring.jpa.domain.Message;
import com.spring.jpa.repository.MessageRepository;
import com.spring.jpa.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class MessageServiceImpl implements MessageService
{
    @Autowired
    private MessageRepository messageRepository;

    public void setMessageRepository(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public Message save(Message message)
    {
        Message savedMessage = messageRepository.saveAndFlush(message);
        return savedMessage;
    }

    @Override
    @Transactional
    public void deleteMessage(Long id)
    {
        messageRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAll()
    {
        return messageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Message getById(Long id)
    {
        return messageRepository.findOne(id);
    }
}
