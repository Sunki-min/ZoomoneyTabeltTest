package com.hardcopy.btchat;

import java.io.Serializable;

/**
 * Created by simstealer on 2017-12-01.
 */

public class Data implements Serializable {

    public String phone_first;
    public String phone_second;

    public Data(String phone){
        this.phone_first = phone;
        this.phone_second = phone;
    }


}