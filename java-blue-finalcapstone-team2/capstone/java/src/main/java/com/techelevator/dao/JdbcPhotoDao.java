package com.techelevator.dao;

import com.techelevator.model.Photo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPhotoDao implements PhotoDao{

    JdbcTemplate jdbcTemplate;

    public JdbcPhotoDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}


    @Override
    public List<Photo> getPhotosByBand(int bandId) {
        List<Photo> photos = new ArrayList<>();
        String sql = "SELECT * FROM photo JOIN band_photo USING (photo_id) WHERE band_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, bandId);
        while (results.next()) {
            Photo photo = mapRowToPhoto(results);
            photos.add(photo);
        }
        return photos;
    }

    private Photo mapRowToPhoto(SqlRowSet rs) {
        Photo photo = new Photo();

        photo.setPhotoId(rs.getInt("photo_id"));
        photo.setPhotoImage(rs.getString("photo_url"));

        return photo;
    }
}
