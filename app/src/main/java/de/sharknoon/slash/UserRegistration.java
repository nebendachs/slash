package de.sharknoon.slash;

public class UserRegistration {

    private static final int MIN_PASSWORD_LENGTH = 8;

    public UserRegistration(String username, String email, String password){


    }

    public static boolean checkForPasswordGuidelines(String password){

        if(password.length() < UserRegistration.MIN_PASSWORD_LENGTH){
            return false;
        }

        return true;
    }
}
