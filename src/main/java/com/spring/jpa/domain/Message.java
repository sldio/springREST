package com.spring.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Message implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String content;

    public Message()
    {
    }

    public Message(String content)
    {
        this.content = content;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '}';
    }
}
