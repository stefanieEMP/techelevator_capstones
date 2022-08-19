package com.techelevator.dao;

import com.techelevator.model.Venue;
import java.util.List;

public interface VenueDao {

    List<Venue> getAllVenues();

    Venue getVenueById(int venueId);

    List<Venue> getVenuesByName(String venueName);



}
