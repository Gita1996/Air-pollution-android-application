package com.example.hp.myapplication;

/**
 * Created by HP on 2/14/2019.
 */

public class DataModel2 {
    String gas;
    String date;
    String time;
    public DataModel2( String date,String time,String gas) {
        this.date=date;
        this.time=time;
        this.gas=gas;

    }
    public DataModel2(){

    }

    public String getGas() {
        return gas;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }



    public void setGas(String gas) {
        this.gas = gas;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
