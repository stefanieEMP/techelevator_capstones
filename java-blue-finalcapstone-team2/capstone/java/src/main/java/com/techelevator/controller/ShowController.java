package com.techelevator.controller;


import com.techelevator.dao.ShowDao;
import com.techelevator.model.Show;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class ShowController {

    private ShowDao showDao;

    public ShowController (ShowDao showDao) {this.showDao = showDao;}

    @GetMapping("/shows") //WORKING: Postman confirmed
    public List<Show> getAllShows() {
        return showDao.getAllShows();
    }

    @GetMapping("/shows/{showId}") //WORKING: Postman confirmed
    public Show getShowById(@PathVariable int showId) {
        return showDao.getShowByID(showId);
    }

    @GetMapping("shows/search/{showTitle}") //WORKING: Postman confirmed
    public List<Show> getShowsByTitle(@PathVariable String showTitle) {
        return showDao.getShowsByTitle(showTitle);
    }

    @GetMapping ("/shows/venue/{venueId}") //WORKING: Postman confirmed
    List<Show> getShowsByVenue(@PathVariable int venueId) {
        return showDao.getShowsByVenue(venueId);
    }

    @GetMapping ("/shows/bands/{bandId}") //WORKING: Postman confirmed
    List<Show> getShowsByBand(@PathVariable int bandId) {
        return showDao.getShowsByBand(bandId);
    }
}
