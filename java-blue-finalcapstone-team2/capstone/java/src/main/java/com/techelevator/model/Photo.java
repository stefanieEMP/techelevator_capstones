package com.techelevator.model;

public class Photo {

    private int photoId;
    private String photoImage;

    public Photo() {};

    public Photo(int PhotoID, String photoImage) {
        this.photoId = photoId;
        this.photoImage = photoImage;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getPhotoImage() {
        return photoImage;
    }

    public void setPhotoImage(String photoImage) {
        this.photoImage = photoImage;
    }
}
