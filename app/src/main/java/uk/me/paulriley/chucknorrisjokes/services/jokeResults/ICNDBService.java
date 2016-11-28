package uk.me.paulriley.chucknorrisjokes.services.jokeResults;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;
import uk.me.paulriley.chucknorrisjokes.services.model.IcndbJoke;
import uk.me.paulriley.chucknorrisjokes.services.model.IcndbJokes;

public interface ICNDBService {
    @GET("/jokes/random")
    Observable<IcndbJoke> getJoke(@QueryMap Map<String, String> allQueries);

    @GET("/jokes/random/10")
    Observable<IcndbJokes> getJokes(@QueryMap Map<String, String> allQueries);
}
