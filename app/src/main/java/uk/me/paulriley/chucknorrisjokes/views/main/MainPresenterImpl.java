package uk.me.paulriley.chucknorrisjokes.views.main;

import java.util.Hashtable;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;
import uk.me.paulriley.chucknorrisjokes.ChuckNorrisJokesApplication;
import uk.me.paulriley.chucknorrisjokes.services.jokeResults.ICNDBResults;
import uk.me.paulriley.chucknorrisjokes.services.jokeResults.JokesFacade;
import uk.me.paulriley.chucknorrisjokes.services.model.IcndbJoke;

public class MainPresenterImpl implements MainPresenter {
    @Inject JokesFacade jokesFacade;

    private MainView mainView;

    @Override
    public void initialise(MainView mainView) {
        this.mainView = mainView;
        ChuckNorrisJokesApplication.get(mainView.getContext()).inject(this);
    }

    @Override
    public void destroy() {
        if (mainView != null) {
            mainView = null;
        }
    }

    @Override
    public void getRandomJoke(boolean allowExplicitJokes) {
        Map<String, String> params = new Hashtable<>();

        if (!allowExplicitJokes) {
            params.put("exclude", "explicit");

        }

        if (jokesFacade != null) {
            ICNDBResults icmdbResults = jokesFacade.getIcndbResults();

            icmdbResults.getJoke(params)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<IcndbJoke>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(IcndbJoke icndbJoke) {
                            String joke = icndbJoke.getValue().getJoke();
                            mainView.showRandomJoke(joke);
                        }
                    });
        }
    }
}
