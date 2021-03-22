package com.example.faheemapplication.CheckProduct.AllergyType;


public class ChildInfo {
    String UserId;
    String typeOfAllergy;


    public ChildInfo(String userId, String typeOfAllergy) {
        UserId = userId;
        this.typeOfAllergy = typeOfAllergy;
    }

    public String getUserId() {

        return UserId;
    }

    public void setUserId(String userId) {

        UserId = userId;
    }

    public String gettypeOfAllergy() {
        return typeOfAllergy;
    }

    public void settypeOfAllergy(String typeOfAllergy) {
        this.typeOfAllergy = typeOfAllergy;
    }
}
