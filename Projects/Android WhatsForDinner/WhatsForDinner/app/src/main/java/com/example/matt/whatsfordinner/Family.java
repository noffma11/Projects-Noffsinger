package com.example.matt.whatsfordinner;

import java.util.ArrayList;

public class Family {


    private int _id;
    private String name;
    private ArrayList<FamilyMember> members;

    public Family(){

    }
    public Family(String thisName){
        this.name = thisName;
    }

    public Family(String thisName, ArrayList<FamilyMember> theseMembers){
        this.name = thisName;
        this.members = theseMembers;
    }

    public String getName() {
        return name;
    }

    public ArrayList<FamilyMember> getMembers() {
        return members;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {

        this._id = _id;
    }

    //public void setMembers(ArrayList<FamilyMember> members) {
    //    this.members = members;
    //}
    public void addMember(FamilyMember thisMember){
        members.add(thisMember);
    }



    public void setName(String name) {
        this.name = name;
    }
}
