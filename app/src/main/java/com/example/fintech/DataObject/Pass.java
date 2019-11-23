package com.example.fintech.DataObject;

public class Pass {
    private String fromId; //보내준 사람 id
    private String sentDate;    //보낸 날짜

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public Pass(String fromId, String sentDate) {
        this.fromId = fromId;
        this.sentDate = sentDate;
    }
}
