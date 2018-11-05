package de.sharknoon.slash.HomeScreen;

public class HomeScreenMessage {

    private String sessionid;
    private String status;

    HomeScreenMessage(String sessionid, String status){
        this.status = status;
        this.sessionid = sessionid;
    }
}
