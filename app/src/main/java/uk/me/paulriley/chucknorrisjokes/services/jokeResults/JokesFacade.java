package uk.me.paulriley.chucknorrisjokes.services.jokeResults;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

public class JokesFacade {

    private final ICNDBResults icndbResults;

    public JokesFacade() {
        OkHttpClient httpClient = initialiseHttpClient();

        ICNDBService icndb = new Retrofit.Builder()
                .baseUrl("https://api.icndb.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build()
                .create(ICNDBService.class);

        icndbResults = new ICNDBResults(icndb);
    }

    private OkHttpClient initialiseHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    public ICNDBResults getIcndbResults() {
        return icndbResults;
    }
}
