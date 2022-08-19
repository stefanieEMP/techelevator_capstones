package com.techelevator.controller;

import com.techelevator.dao.PhotoDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Photo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class PhotoController {

    private PhotoDao photoDao;
    private UserDao userDao;

    public PhotoController(PhotoDao photoDao, UserDao userDao) {
        this.photoDao = photoDao;
        this.userDao = userDao;
    }

    @GetMapping("/bands/{bandId}/photos") //WORKING: Postman confirmed
    public List<Photo> getPhotoById(@PathVariable int bandId) {
        return photoDao.getPhotosByBand(bandId);
    }
}
