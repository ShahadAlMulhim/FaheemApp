package com.example.faheemapplication.CheckProduct.AllergyType;


import com.example.faheemapplication.CheckProduct.AllergyType.AllergyType;

public class ChildInfo {
    String UserId;
    AllergyType typeOfAllergy;


    public ChildInfo(){
    }

    public ChildInfo(String userId, AllergyType typeOfAllergy) {
        UserId = userId;
        this.typeOfAllergy = typeOfAllergy;
    }

    public String getUserId() {

        return UserId;
    }

    public void setUserId(String userId) {

        UserId = userId;
    }

    public AllergyType gettypeOfAllergy() {
        return typeOfAllergy;
    }

    public void settypeOfAllergy(AllergyType typeOfAllergy) {
        this.typeOfAllergy = typeOfAllergy;
    }
}
