package uk.me.paulriley.chucknorrisjokes.services.model;

import java.io.Serializable;

public class IcndbJoke implements Serializable {
    private String type;
    private String success;
    private Joke value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String getSuccess() {
        return success;
    }

    public  void setSuccess(String success) {
        this.success = success;
    }

    public Joke getValue() {
        return value;
    }

    public void setValue(Joke value) {
        this.value = value;
    }
}
