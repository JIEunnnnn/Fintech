package com.example.fintech.DataObject;

public class Card {
    private String cardName;
    private String cardNo;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Card(String cardName, String cardNo) {
        this.cardName = cardName;
        this.cardNo = cardNo;
    }
}
