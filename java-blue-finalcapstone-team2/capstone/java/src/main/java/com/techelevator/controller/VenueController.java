package com.techelevator.controller;

import com.techelevator.dao.VenueDao;
import com.techelevator.model.Band;
import com.techelevator.model.Venue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class VenueController {

    VenueDao venueDao;

    public VenueController (VenueDao venueDao) {this.venueDao = venueDao;}

    @GetMapping ("/venues") //WORKING: Postman confirmed
    List<Venue> getAllVenues() {
        return venueDao.getAllVenues();
    }

    @GetMapping("/venues/{venueId}") //WORKING: Postman confirmed
    Venue getVenueById(@PathVariable int venueId) {
        return venueDao.getVenueById(venueId);
    }

    @GetMapping("venues/search/{venueName}") //WORKING: Postman confirmed
    public List<Venue> getVenuesByName(@PathVariable String venueName) {return venueDao.getVenuesByName(venueName) ;}

}
