package com.techelevator.dao;

import com.techelevator.model.Band;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcBandDao implements BandDao {

    JdbcTemplate jdbcTemplate;

    public JdbcBandDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Band> getAllBands() {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM band;";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Band band = mapRowToBand(results);
            bands.add(band);
        }
        return bands;
    }

    public Band getBandById(int bandId) {
        Band band = new Band();
        String sql = "SELECT * FROM band WHERE band_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        if (results.next()) {
            band = mapRowToBand(results);
        }
        return band;
    }

    @Override
    public List<Band> getBandsByName(String bandName) {
        List<Band> bands =new ArrayList<>();
        if(!bandName.isEmpty()) {
            String sql = "select * from band where band_name ILIKE ?;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + bandName + "%");
            while (results.next()) {
                Band band = mapRowToBand(results);
                bands.add(band);
            }
        }
        return bands;
    }


    public List<Band> getBandsByGenre(String genreName) {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM band JOIN band_genre USING (band_id) JOIN genre USING (genre_id) WHERE genre_name ILIKE ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + genreName + "%");
        while (results.next()) {
            Band band = mapRowToBand(results);
            bands.add(band);
        }

        return bands;
    }

    public List<Band> getBandsByShow(String showTitle) {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM band JOIN show_band USING (band_id) JOIN show USING (show_id) WHERE show_title ILIKE ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + showTitle + "%");
        while (results.next()) {
            Band band = mapRowToBand(results);
            bands.add(band);
        }
        return bands;
    }

    public Band createBand(Band newBand) {
        String sql = "INSERT INTO band (band_name, band_description, band_member, band_image, manager_id) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING band_id;";
        try{
            Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newBand.getBandName(), newBand.getBandDesc(), newBand.getMembers(), newBand.getBandImage(), newBand.getMgrId());
            return getBandById(newId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBand(Band bandToUpdate, int bandId) {
        String sql = "UPDATE band SET band_name = ?, band_description = ?, band_member = ?, band_image = ?, manager_id = ? WHERE band_id = ?;";
        try {
            jdbcTemplate.update(sql, bandToUpdate.getBandName(), bandToUpdate.getBandDesc(), bandToUpdate.getMembers(), bandToUpdate.getBandImage(), bandToUpdate.getMgrId(), bandId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteBand(Band bandToDelete, int bandId) {
        String sql = "DELETE FROM user_messages WHERE message_id = ANY(SELECT message_id FROM messages WHERE band_id = " + bandId + ");" +
                "DELETE FROM messages WHERE band_id = " + bandId + "; " +
                "DELETE FROM band_genre WHERE band_id = " + bandId + "; " +
                "DELETE FROM show_band WHERE band_id = " + bandId + "; " +
                "DELETE FROM user_band WHERE band_id = " + bandId + "; " +
                "DELETE FROM band_photo WHERE band_id = " + bandId + "; " +
                "DELETE FROM band WHERE band_id = " + bandId + ";";
        try {
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Band> getBandsFollowed(int userId) {
        List<Band> bands = new ArrayList<>();
        String sql = "SELECT * FROM band JOIN user_band using (band_id) where user_id = " + userId + ";";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Band band = mapRowToBand(results);
            bands.add(band);
        }
        return bands;
    }

    public int findIdByBandName(String bandName) {
        if (bandName == null) throw new IllegalArgumentException("Band Name cannot be null");

        int bandId;
        try {
            bandId = jdbcTemplate.queryForObject("select band_id from band where band_name ILIKE ?", int.class, "%" + bandName + "%");
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("Band " + bandName + " was not found.");
        }

        return bandId;
    }

    private Band mapRowToBand(SqlRowSet rs) {
        Band band = new Band();

        band.setBandId(rs.getInt("band_id"));
        band.setBandName(rs.getString("band_name"));
        band.setBandDesc(rs.getString("band_description"));
        band.setMembers(rs.getString("band_member"));
        band.setBandImage(rs.getString("band_image"));
        band.setMgrId(rs.getInt("manager_id"));


        return band;
    }

}