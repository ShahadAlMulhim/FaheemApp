package com.example.faheemapplication;


import java.util.ArrayList;

public class ChildInfo {
    private String UserId;
    private ArrayList<String> Checkboxes;


    public ChildInfo(String userId, ArrayList<String> checkboxes) {
        UserId = userId;
        Checkboxes = checkboxes;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public ArrayList<String> getCheckboxes() {
        return Checkboxes;
    }

    public void setCheckboxes(ArrayList<String> checkboxes) {
        Checkboxes = checkboxes;
    }
}
