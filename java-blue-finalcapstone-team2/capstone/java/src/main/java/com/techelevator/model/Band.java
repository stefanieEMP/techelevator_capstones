package com.techelevator.model;

public class Band {

    private int bandId;
    private String bandName;
    private String bandDesc;
    private String members;
    private String bandImage;
    private int mgrId;


    public Band(int bandId, String bandName, String bandDesc, String members, String bandImage, int mgrId) {
        this.bandId = bandId;
        this.bandName = bandName;
        this.bandDesc = bandDesc;
        this.members = members;
        this.bandImage = bandImage;
        this.mgrId = mgrId;
    }

    public Band() {

    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public String getBandDesc() {
        return bandDesc;
    }

    public void setBandDesc(String bandDesc) {
        this.bandDesc = bandDesc;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getBandImage() {
        return bandImage;
    }

    public void setBandImage(String bandImage) {
        this.bandImage = bandImage;
    }

    public int getMgrId() {
        return mgrId;
    }

    public void setMgrId(int mgrId) {
        this.mgrId = mgrId;
    }


}


