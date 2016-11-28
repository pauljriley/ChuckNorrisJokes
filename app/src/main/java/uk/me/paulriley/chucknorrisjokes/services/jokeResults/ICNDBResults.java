package uk.me.paulriley.chucknorrisjokes.services.jokeResults;

import java.util.Map;

import rx.Observable;
import uk.me.paulriley.chucknorrisjokes.services.model.IcndbJoke;
import uk.me.paulriley.chucknorrisjokes.services.model.IcndbJokes;

public class ICNDBResults {
    private final ICNDBService icndb;

    public ICNDBResults(ICNDBService icndb) {
        this.icndb = icndb;
    }

    public Observable<IcndbJoke> getJoke(Map<String, String> allQueries) {
        return icndb.getJoke(allQueries);
    }

    public Observable<IcndbJokes> getJokes(Map<String, String> allQueries) {
        return icndb.getJokes(allQueries);
    }
}
