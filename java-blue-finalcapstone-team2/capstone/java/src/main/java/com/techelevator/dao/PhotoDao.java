package com.techelevator.dao;

import com.techelevator.model.Photo;

import java.util.List;

public interface PhotoDao {

    List<Photo> getPhotosByBand(int bandId);
}
