package com.spring.jpa.controller;

import com.spring.jpa.domain.Message;
import com.spring.jpa.services.MessageService;
import com.spring.jpa.services.jdbc.DatabaseWorker;
import com.spring.jpa.services.implementations.MessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MessageController
{

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @GetMapping("/test")
    @Transactional
    public Message showHello(@RequestParam(defaultValue = "default text") String message)
    {
        logger.info("---------------test ok");
        return new Message(String.format("Hello %s!", message));
    }

    @GetMapping("/show-all")
    public List<Message> showAllMessagesJDBC() {
        logger.error("----------------show all");
        List<Message> list = DatabaseWorker.getAllMessages();
        DatabaseWorker.closeConnection();

        return list;
    }

    @GetMapping("/show")
    @ReadOnlyProperty
    public List<Message> showAll(){
        logger.error("----------------show all with messageRepository");
        List<Message> list = messageService.getAll();
        System.out.println(list.size());

        return list;
    }

    @GetMapping("/select")
    @ReadOnlyProperty
    public List<Message> select(){
        logger.error("----------------show all with messageRepository");
        List<Message> list = messageService.getAll();
        System.out.println(list.size());

        return list;
    }

    @GetMapping("/save")
    public Message saveMessage(@RequestParam(defaultValue = "default message") String message){
        logger.error("----------------show all");
        if (message == null){
            message = "default message from if";
        }
        Message mes = messageService.save(new Message(message));

        return mes;
    }
}
