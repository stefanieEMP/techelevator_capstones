package com.techelevator.dao;

import com.techelevator.model.Show;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class JdbcShowDao implements ShowDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcShowDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Show> getAllShows() {
        List<Show> shows = new ArrayList<>();
        String sql = "SELECT * FROM show;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Show show = mapRowToShow(results);
            shows.add(show);
        }
        return shows;
    }

    @Override
    public Show getShowByID(int showId) {
        Show show = new Show();
        String sql = "SELECT * FROM show WHERE show_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, showId);
        if (results.next()) {
            show = mapRowToShow(results);
        }
        return show;
    }

    @Override
    public List<Show> getShowsByTitle(String showTitle) {
        List<Show> shows = new ArrayList<>();
        if (!showTitle.isEmpty()) {
            String sql = "select * from show where show_title ILIKE ?;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + showTitle + "%");
            while (results.next()) {
                Show show = mapRowToShow(results);
                shows.add(show);
            }
        }
        return shows;
    }

    public List<Show> getShowsByVenue(int venueId) {
        List<Show> shows = new LinkedList<>();
        String sql = "SELECT * FROM show JOIN venue USING (venue_id) WHERE venue_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, venueId);

        while (results.next()) {
            Show show = mapRowToShow(results);
            shows.add(show);
        }
        return shows;
    }

    public List<Show> getShowsByBand(int bandId) {
        List<Show> shows = new LinkedList<>();
        String sql = "SELECT * FROM show JOIN show_band USING (show_id) JOIN band USING (band_id) WHERE band_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);

        while (results.next()) {
            Show show = mapRowToShow(results);
            shows.add(show);
        }
        return shows;
    }

    //OPTIONAL - getShowsByDate();

    public Show addShow() {
        return null;
    }

    public Show updateShow(int showId) {
        return null;
    }

    public void deleteShow(int showId) {

    }

    private Show mapRowToShow(SqlRowSet rs) {
        Show show = new Show();


        show.setShowId(rs.getInt("show_id"));
        show.setShowTime(rs.getTimestamp("show_time").toLocalDateTime());
        show.setShowTitle(rs.getString("show_title"));
        show.setShowDesc(rs.getString("show_description"));
        show.setVenueId(rs.getInt("venue_id"));


        return show;
    }

}
