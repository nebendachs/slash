package de.sharknoon.slash.HomeScreen;


public class Contact {

    private String contactname;
    private String contactimageurl;

    public Contact(String contactname, String contactimageurl){
        this.contactname = contactname;
        this.contactimageurl = contactimageurl;
    }

    public String getContactName() {
        return contactname;
    }

    public String getContactImageUrl() {
        return contactimageurl;
    }
}
