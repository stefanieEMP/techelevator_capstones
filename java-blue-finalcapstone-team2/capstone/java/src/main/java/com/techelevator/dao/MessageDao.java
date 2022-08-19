package com.techelevator.dao;

import com.techelevator.model.Message;

import java.util.List;

public interface MessageDao {

    List<Message> getMessagesOfCurrentUser(int userId);

    Message getMessageById(int messageId);

    List<Message> sortMessagesByDate();

    List <Message> sortMessagesByBandName();

    boolean sendMessageToFollowers(Message newMessage, int mgrId, int bandId);

    boolean deleteMessage(int userId, int msgId);

   // void deleteMessage(int messageId);

}
