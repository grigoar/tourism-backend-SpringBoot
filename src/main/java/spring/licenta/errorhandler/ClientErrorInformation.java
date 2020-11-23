package spring.licenta.errorhandler;


import org.springframework.http.HttpStatus;

import java.util.List;

public class ClientErrorInformation {
    private String entity;
    private String requestedUri;
    private List<String> details;
    private int statusCode;
    private String statusMessage;

    public ClientErrorInformation(String entity, HttpStatus status, List<String>  details, String requestedUri) {
        this.entity = entity;
        this.statusCode = status.value();
        this.statusMessage = status.getReasonPhrase();
        this.details = details;
        this.requestedUri = requestedUri;
    }


    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String>  details) {
        this.details = details;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getRequestedUri() {
        return requestedUri;
    }

    public void setRequestedUri(String requestedUri) {
        this.requestedUri = requestedUri;
    }
}
