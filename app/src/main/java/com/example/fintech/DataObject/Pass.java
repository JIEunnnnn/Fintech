package com.example.fintech.DataObject;

import java.io.Serializable;

public class Pass implements Serializable {
    private String pass_id;


    private String fromId; //보내준 사람 id
    private String limit_price;    //한도금액

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

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public Pass(String pass_id, String fromId, String limit_price) {
        this.pass_id = pass_id;
        this.fromId = fromId;
        this.limit_price = limit_price;
    }

}
