package de.sharknoon.slash.Login;

import de.sharknoon.slash.Server.Connector;

public class UserLogin {

    public UserLogin(String email, String password, Connector connector) {
        connector.sendMessage();
    }
}
