package com.example.firstproject;

public class users {

    String fullName,storeName,space_occupied,Adate,ddate;
        int comp_no,compartment_sizes;


    public users(String fullName, String space_occupied, String storName, String Adate, String Ddate,int comp_no,int compartment_sizes) {
        this.fullName = fullName;
        this.storeName = storName;
        this.space_occupied = space_occupied;
        this.Adate = Adate;
        this.ddate = Ddate;
        this.comp_no=comp_no;
        this.compartment_sizes=compartment_sizes;
    }

    public int getCompartment_sizes() {
        return compartment_sizes;
    }

    public void setCompartment_sizes(int compartment_sizes) {
        this.compartment_sizes = compartment_sizes;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getAdate() {
        return Adate;
    }

    public void setAdate(String adate) {
        Adate = adate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSpace_occupied() {
        return space_occupied;
    }

    public void setSpace_occupied(String space_occupied) {
        this.space_occupied = space_occupied;
    }

    public int getComp_no() {
        return comp_no;
    }

    public void setComp_no(int comp_no) {
        this.comp_no = comp_no;
    }
}
