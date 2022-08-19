package com.techelevator.dao;

import com.techelevator.model.Band;
import com.techelevator.model.Venue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class JdbcVenueDao implements VenueDao{

    JdbcTemplate jdbcTemplate;

    public JdbcVenueDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    public List<Venue> getAllVenues(){
        List<Venue> venues = new ArrayList<>();
        String sql = "SELECT * FROM venue;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Venue v = mapRowToVenue(results);
            venues.add(v);
        }

        return venues;}

    public Venue getVenueById(int venueId){
        Venue venue = new Venue();
        String sql = "SELECT * FROM venue WHERE venue_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, venueId);
        if (results.next()) {
            venue = mapRowToVenue(results);
        }

        return venue;
    }

    @Override
    public List<Venue> getVenuesByName(String venueName) {
        List<Venue> venues =new ArrayList<>();
        if(!venueName.isEmpty()) {
            String sql = "select * from venue where venue_name ILIKE ?;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + venueName + "%");
            while (results.next()) {
                Venue venue = mapRowToVenue(results);
                venues.add(venue);
            }
        }
        return venues;
    }


    private Venue mapRowToVenue (SqlRowSet rs) {
        Venue venue = new Venue();

        venue.setVenueId(rs.getInt("venue_id"));
        venue.setVenueName(rs.getString("venue_name"));
        venue.setVenueAddress(rs.getString("venue_address"));
        venue.setVenueDesc(rs.getString("venue_description"));
        venue.setVenueMap(rs.getString("venue_map"));

      return venue;
    }

}
