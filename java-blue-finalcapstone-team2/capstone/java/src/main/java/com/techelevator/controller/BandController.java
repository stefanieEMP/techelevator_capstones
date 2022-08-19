package com.techelevator.controller;


import com.techelevator.dao.BandDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Band;

import java.security.Principal;
import java.util.List;

import net.bytebuddy.build.Plugin;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class BandController {

    private BandDao bandDao;
    private UserDao userDao;

    public BandController(BandDao bandDao, UserDao userDao) {
        this.bandDao = bandDao;
        this.userDao = userDao;
    }

    @GetMapping("/bands") //WORKING: Postman confirmed
    public List<Band> getAllBands() {
        return bandDao.getAllBands();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/bands/{bandId}") //WORKING: Postman confirmed
    public Band getBandsById(@PathVariable int bandId) {
        return bandDao.getBandById(bandId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("bands/search/{bandName}") //WORKING: Postman confirmed
    public List<Band> getBandsByName(@PathVariable String bandName) {
        return bandDao.getBandsByName(bandName);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/bands/genres/{genreName}") //WORKING: Postman confirmed
    public List<Band> getBandsByGenre(@PathVariable String genreName) {
        return bandDao.getBandsByGenre(genreName);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/bands/shows/{showTitle}") //WORKING: Postman confirmed
    public List<Band> getBandsByShow(@PathVariable String showTitle) {
        return bandDao.getBandsByShow(showTitle);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/bands/newband")
    //WORKING: Postman confirmed, current user_id is set as the new band's manager_id no matter what they put in
    public Band createBand(@Valid @RequestBody Band newBand, Principal user) {
        int currentUserId = userDao.findIdByUsername(user.getName());
        newBand.setMgrId(currentUserId);
        return bandDao.createBand(newBand);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/bands/{bandId}")
    //WORKING: Postman confirmed, can only change band details if current user_id matches band's manager_id
    public boolean updateBand(@Valid @RequestBody Band bandToUpdate, Principal user, @PathVariable int bandId) {
        Band band = bandDao.getBandById(bandId);
        int currentManagerId = band.getMgrId();
        int currentUserId = userDao.findIdByUsername(user.getName());
        if (currentUserId == currentManagerId) {
            return bandDao.updateBand(bandToUpdate, bandId);
        } else {
            return false;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/bands/{bandId}")
    //WORKING: Postman confirmed, can only delete band if current user_id matches band's manager_id
    public boolean deleteBand(@Valid Band bandToDelete, Principal user, @PathVariable int bandId) {
        Band band = bandDao.getBandById(bandId);
        int currentManagerId = band.getMgrId();
        int currentUserId = userDao.findIdByUsername(user.getName());
        if (currentUserId == currentManagerId) {
            return bandDao.deleteBand(bandToDelete, bandId);
        } else {
            return false;
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/following")
    public List<Band> getBandsFollowed(@Valid Principal user) {
        int currentUserId = userDao.findIdByUsername(user.getName());
        return bandDao.getBandsFollowed(currentUserId);
    }
}



