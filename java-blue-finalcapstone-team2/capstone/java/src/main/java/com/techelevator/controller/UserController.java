package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class UserController {

    private UserDao userDao;

    public UserController (UserDao userDao){
        this.userDao = userDao;
    }

    @GetMapping("/bands/{bandId}/followers") //WORKING: Postman confirmed
    public List<User> getFollowersByBand (@PathVariable int bandId) {
        return userDao.getFollowersByBand(bandId);
    }

    @PostMapping("/bands/{bandId}/follow") //WORKING: Postman confirmed
    public void followBand (@PathVariable int bandId, Principal user) {
        User currentUser = userDao.findByUsername(user.getName());
        int userId = currentUser.getId();
        userDao.followBand(userId, bandId);}

    @DeleteMapping("/bands/{bandId}/unfollow") //WORKING: Postman confirmed
    public void unfollowBand(@PathVariable int bandId, Principal user) {
        User currentUser = userDao.findByUsername(user.getName());
        int userId = currentUser.getId();
        userDao.unfollowBand(userId, bandId);}

}
