package com.example.matt.whatsfordinner;

import java.util.ArrayList;

public class FamilyMember {


    private String memberName;
    private ArrayList<String> memberAllergies;
    private ArrayList<String> memberFavorites;


    public FamilyMember(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    public ArrayList<String> getMemberAlgeries() {
        return memberAllergies;
    }

    public ArrayList<String> getMemberFavorites() {
        return memberFavorites;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setMemberAlgeries(ArrayList<String> memberAlgeries) {
        this.memberAllergies = memberAlgeries;
    }

    public void setMemberFavorites(ArrayList<String> memberFavorites) {
        this.memberFavorites = memberFavorites;
    }
}
