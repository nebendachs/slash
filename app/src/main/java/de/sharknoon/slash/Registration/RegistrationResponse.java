package de.sharknoon.slash.Registration;

public class RegistrationResponse {

    private String status;
    private String message;

    public RegistrationResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
