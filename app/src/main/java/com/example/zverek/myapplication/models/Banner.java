package com.example.zverek.myapplication.models;

public class Banner {

    private String name;
    private String state;
    private String text;
    private String telNumber;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }


    @Override
    public String toString() {
        return "Banner{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", text='" + text + '\'' +
                ", telNumber='" + telNumber + '\'' +
                '}';
    }
}
