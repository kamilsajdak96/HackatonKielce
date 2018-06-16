package com.example.kamil.hackatonkielce.giveDirectory;

public class PaidOffers {

    String idOfPaidOffer;
    String title;
    String sallary;
    String description;
    String addPhoneNumber;

    public String getIdOfPaidOffer() {
        return idOfPaidOffer;
    }

    public void setIdOfPaidOffer(String idOfPaidOffer) {
        this.idOfPaidOffer = idOfPaidOffer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSallary() {
        return sallary;
    }

    public void setSallary(String sallary) {
        this.sallary = sallary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddPhoneNumber() {
        return addPhoneNumber;
    }

    public void setAddPhoneNumber(String addPhoneNumber) {
        this.addPhoneNumber = addPhoneNumber;
    }

    public PaidOffers(){

    }

    public PaidOffers(String idOfPaidOffer, String title, String sallary, String description, String addPhoneNumber) {
        this.sallary = sallary;
        this.idOfPaidOffer = idOfPaidOffer;
        this.title = title;
        this.description = description; //czyli tekst
        this.addPhoneNumber = addPhoneNumber;
    }


    public PaidOffers(String idOfPaidOffer, String title, String description, String addPhoneNumber) {
        this.idOfPaidOffer = idOfPaidOffer;
        this.title = title;
        this.description = description; //czyli tekst
        this.addPhoneNumber = addPhoneNumber;
    }

}
