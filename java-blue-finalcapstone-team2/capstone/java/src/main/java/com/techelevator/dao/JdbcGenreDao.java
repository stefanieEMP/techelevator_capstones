package com.techelevator.dao;

import com.techelevator.model.Genre;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGenreDao implements GenreDao {

    JdbcTemplate jdbcTemplate;

    public JdbcGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genre;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Genre genre = mapRowToGenre(results);
            genres.add(genre);
        }
        return genres;
    }

    @Override
    public Genre getGenreByID(int genreId) {
        Genre genre = new Genre();
        String sql = "SELECT * FROM genre WHERE genre_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genreId);
        if (results.next()) {
            genre = mapRowToGenre(results);
        }
        return genre;
    }

    public List<Genre> getGenresByBandId(int bandId) {

        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT * FROM genre JOIN band_genre USING (genre_id) JOIN band USING (band_id) WHERE band_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,bandId);
        while (results.next()){
        Genre genre = mapRowToGenre(results);
        genres.add(genre);
        }
        return genres;
    }

    @Override
    public Genre createGenre(Genre newGenre) {
        String sql = "INSERT INTO genre (genre_name) " +
                "VALUES (?) RETURNING genre_id;";
        try{
            Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, newGenre.getGenreName());
            return getGenreByID(newId);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteGenre( int genreID) {
        String sql = "DELETE FROM band_genre WHERE genre_id = ANY(SELECT genre_id FROM genre WHERE genre_id = " + genreID + ");" +
                "DELETE FROM genre WHERE genre_id = " + genreID + ";";
        try {
            jdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void addGenreToBand(int bandId, int genreId) {
        String sql = "INSERT INTO band_genre (band_id, genre_id) VALUES (?, ?)";
        try{
            jdbcTemplate.update(sql, bandId, genreId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeGenreFromBand(int bandId, int genreId) {
        String sql = "DELETE FROM band_genre WHERE band_id = ? AND genre_ID = ?";
        try{
            jdbcTemplate.update(sql, bandId, genreId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Genre mapRowToGenre(SqlRowSet rs) {
        Genre genre = new Genre();

        genre.setGenreId(rs.getInt("genre_id"));
        genre.setGenreName(rs.getString("genre_name"));

        return genre;
    }
}
