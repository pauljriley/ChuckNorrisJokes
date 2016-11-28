package uk.me.paulriley.chucknorrisjokes.views.endless;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;
import uk.me.paulriley.chucknorrisjokes.ChuckNorrisJokesApplication;
import uk.me.paulriley.chucknorrisjokes.services.jokeResults.ICNDBResults;
import uk.me.paulriley.chucknorrisjokes.services.jokeResults.JokesFacade;
import uk.me.paulriley.chucknorrisjokes.services.model.IcndbJokes;
import uk.me.paulriley.chucknorrisjokes.services.model.Joke;

public class EndlessJokePresenterImpl implements EndlessJokePresenter {
    @Inject JokesFacade jokesFacade;

    private EndlessJokesView endlessJokesView;

    @Override
    public void initialise(EndlessJokesView endlessJokesView) {
        this.endlessJokesView = endlessJokesView;
        ChuckNorrisJokesApplication.get(endlessJokesView.getContext()).inject(this);
    }

    @Override
    public void destroy() {
        if (endlessJokesView != null) {
            endlessJokesView = null;
        }
    }

    @Override
    public void getAllTheJokes(boolean allowExplicitJokes) {
        Map<String, String> params = new Hashtable<>();

        if (!allowExplicitJokes) {
            params.put("exclude", "explicit");
        }

        if (jokesFacade != null) {
            ICNDBResults icmdbResults = jokesFacade.getIcndbResults();

            icmdbResults.getJokes(params)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<IcndbJokes>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onNext(IcndbJokes icndbJokes) {
                            List<String> jokes = new ArrayList<>();
                            for (Joke joke: icndbJokes.getValue()) {
                                jokes.add(joke.getJoke());
                            }

                            endlessJokesView.updateData(jokes);
                        }
                    });
        }
    }
}
