package com.spring.jpa.services.jdbc;

import com.spring.jpa.domain.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseWorker
{
    private static Connection connection = ConnectionUtil.getConnection();
    private static final Logger logger = LoggerFactory.getLogger(DatabaseWorker.class);

    public static void writeInDatabase(Message message)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into public.message (id, content) values (?");
            //statement.setLong(1, message.getId());
            statement.setString(2, message.getContent());
            System.out.println(statement.executeUpdate());
            statement.close();
        }
        catch (SQLException e){
            System.out.println("can`t insert in database");
        }
    }

    public static List<Message> getAllMessages()
    {
        List<Message> resultList = new ArrayList<>();
        try
        {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM public.message");

            ResultSet resultSet = statement.executeQuery();
            logger.debug("************getAll");
            while (resultSet.next()){

                Long id = resultSet.getLong("ID");
                logger.debug("->next {}",id);
                String content = resultSet.getString("CONTENT");
                logger.debug("-->content {}", content);
                if (content == null){
                    content = "default";
                }
                Message message = new Message(content);
                message.setId(id);
                resultList.add(message);
            }
            statement.close();
        }
        catch (SQLException e){
            System.out.println("can`t select all");
        }

        return resultList;
    }

    public static void closeConnection(){
            ConnectionUtil.closeConnection();
    }
}
