package com.progressoft.induction.errorsex;

public class NotFoundExcepion extends Exception {


    private String message;

    public NotFoundExcepion(String message) {
        super(message);
        this.message = message;

    }

    @Override
    public String getMessage() {
        return message;
    }

}
