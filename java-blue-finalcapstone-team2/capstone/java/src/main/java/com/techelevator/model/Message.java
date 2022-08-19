package com.techelevator.model;

import java.util.Date;

public class Message {

    private int messageId;
    private String messageBody;
    private Date messageTimestamp;  //maybe revisit this??
    private int bandId;
    private  String bandName;

    public Message(int messageId, String messageBody, Date messageTimestamp, int bandId) {
        this.messageId = messageId;
        this.messageBody = messageBody;
        this.messageTimestamp = messageTimestamp;
        this.bandId = bandId;
    }

    public Message() {

    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(Date messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
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
}
