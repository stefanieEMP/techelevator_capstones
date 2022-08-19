package com.techelevator.model;

public class Venue {

    private int venueId;
    private String venueName;
    private String venueAddress;
    private String venueDesc;
    private String venueMap;

    public Venue() {
        this.venueId = venueId;
        this.venueName = venueName;
        this.venueAddress = venueAddress;
        this.venueDesc = venueDesc;
        this.venueMap = venueMap;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        this.venueAddress = venueAddress;
    }

    public String getVenueDesc() {
        return venueDesc;
    }

    public void setVenueDesc(String venueDesc) {
        this.venueDesc = venueDesc;
    }

    public String getVenueMap() {
        return venueMap;
    }

    public void setVenueMap(String venueMap) {
        this.venueMap = venueMap;
    }
}
