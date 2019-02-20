package com.sajib.graph.types;

/**
 * Created by sajib on 2/20/19.
 */
public class SearchError {

    private String errorMessage;

    public SearchError() {
    }

    public SearchError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
