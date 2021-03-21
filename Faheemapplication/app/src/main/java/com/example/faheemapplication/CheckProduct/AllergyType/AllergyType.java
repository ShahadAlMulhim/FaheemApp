package com.example.faheemapplication.CheckProduct.AllergyType;

public class AllergyType {
    private String Allergy1;
    private String Allergy2;
    private String Allergy3;
    private String Allergy4;
    private String Allergy5;
    private String Allergy6;

    public AllergyType(){
    }

    public AllergyType(String allergy1, String allergy2, String allergy3, String allergy4, String allergy5, String allergy6) {
        Allergy1 = allergy1;
        Allergy2 = allergy2;
        Allergy3 = allergy3;
        Allergy4 = allergy4;
    }

    public String getAllergy1() {
        return Allergy1;
    }

    public void setAllergy1(String allergy1) {
        Allergy1 = allergy1;
    }

    public String getAllergy2() {
        return Allergy2;
    }

    public void setAllergy2(String allergy2) {
        Allergy2 = allergy2;
    }

    public String getAllergy3() {
        return Allergy3;
    }

    public void setAllergy3(String allergy3) {
        Allergy3 = allergy3;
    }

    public String getAllergy4() {
        return Allergy4;
    }

    public void setAllergy4(String allergy4) {
        Allergy4 = allergy4;
    }

}
