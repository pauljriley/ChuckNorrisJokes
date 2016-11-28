package uk.me.paulriley.chucknorrisjokes.services.model;

import java.io.Serializable;
import java.util.List;

public class IcndbJokes implements Serializable {
    private String type;
    private String success;
    private List<Joke> value;

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

    public List<Joke> getValue() {
        return value;
    }

    public void setValue(List<Joke> value) {
        this.value = value;
    }
}
