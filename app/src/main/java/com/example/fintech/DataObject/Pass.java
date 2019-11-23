package com.example.fintech.DataObject;

import java.io.Serializable;

public class Pass{
    private String pass_id;
    private String from_id; //보내준 사람 id
    private String to_id;
    private String card_num;
    private String limit_price;    //한도금액

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getPass_id() {
        return pass_id;
    }

    public void setPass_id(String pass_id) {
        this.pass_id = pass_id;
    }

    public String getLimit_price() {
        return limit_price;
    }

    public void setLimit_price(String limit_price) {
        this.limit_price = limit_price;
    }

    public Pass(String pass_id, String from_id, String to_id, String card_num, String limit_price) {
        this.pass_id = pass_id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.card_num = card_num;
        this.limit_price = limit_price;
    }

    public Pass(String pass_id, String fromId, String limit_price) {
        this.pass_id = pass_id;
        this.from_id = fromId;
        this.limit_price = limit_price;
    }

    public Pass() {}
}
