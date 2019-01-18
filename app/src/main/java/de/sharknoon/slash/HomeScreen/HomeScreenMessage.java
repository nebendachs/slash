package de.sharknoon.slash.HomeScreen;

@SuppressWarnings({"FieldCanBeLocal", "UnusedParameters"})
class HomeScreenMessage {
    private final String sessionid;
    private String status = "GET_HOME";

    HomeScreenMessage(String sessionid){
        this.sessionid = sessionid;
    }
}
