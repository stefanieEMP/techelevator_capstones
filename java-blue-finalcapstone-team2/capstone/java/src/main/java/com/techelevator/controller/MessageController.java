package com.techelevator.controller;

import com.techelevator.dao.BandDao;
import com.techelevator.dao.MessageDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Band;
import com.techelevator.model.Message;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class MessageController {

    private MessageDao messageDao;
    private UserDao userDao;
    private BandDao bandDao;

    public MessageController(MessageDao messageDao, UserDao userDao, BandDao bandDao) {
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.bandDao = bandDao;
    }

    @GetMapping("/inbox")
    public List<Message> getMessagesOfCurrentUser(@Valid Principal user) {
        User currentUser = userDao.findByUsername(user.getName());
        int userId = currentUser.getId();
        System.out.println("User ID: " + userId + " (" + user.getName() + ") retrieved messages");
        return messageDao.getMessagesOfCurrentUser(userId);
    }

    @PostMapping("/bands/{bandId}/newmessage")
    public boolean sendMessageToFollowers(@RequestBody Message newMessage, @Valid Principal user, @PathVariable int bandId) {
        Band band = bandDao.getBandById(bandId);
        int currentManagerId = band.getMgrId();
        User currentUser = userDao.findByUsername(user.getName());
        int currentUserId = currentUser.getId();
        if (currentUserId == currentManagerId) {
            return messageDao.sendMessageToFollowers(newMessage, currentUserId, bandId);
        } else {
            return false;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/inbox/{msgId}")
    public boolean deleteMessage(@Valid Principal user, @PathVariable int msgId){
        System.out.println(user.getName() + " deleted messages");
        User currentUser = userDao.findByUsername(user.getName());
        int currentUserId = currentUser.getId();
        System.out.println("User ID: " + currentUserId);
        return messageDao.deleteMessage(currentUserId, msgId);
    }

}
