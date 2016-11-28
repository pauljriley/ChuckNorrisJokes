package uk.me.paulriley.chucknorrisjokes.views.personalise;

import java.util.Hashtable;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;
import rx.schedulers.Schedulers;
import uk.me.paulriley.chucknorrisjokes.ChuckNorrisJokesApplication;
import uk.me.paulriley.chucknorrisjokes.services.jokeResults.ICNDBResults;
import uk.me.paulriley.chucknorrisjokes.services.jokeResults.JokesFacade;
import uk.me.paulriley.chucknorrisjokes.services.model.IcndbJoke;

public class PersonalisedPresenterImpl implements PersonalisedPresenter {
    @Inject JokesFacade jokesFacade;

    private PersonalisedJokeView personalisedJokeView;

    @Override
    public void initialise(PersonalisedJokeView personalisedJokeView) {
        this.personalisedJokeView = personalisedJokeView;
        ChuckNorrisJokesApplication.get(personalisedJokeView.getContext()).inject(this);
    }

    @Override
    public void destroy() {
        if (personalisedJokeView != null) {
            personalisedJokeView = null;
        }
    }

    @Override
    public void getPersonalisedJoke(String firstName, String lastName, boolean allowExplicitJokes) {
        Map<String, String> params = new Hashtable<>();

        if (!allowExplicitJokes) {
            params.put("exclude", "explicit");
        }

        params.put("firstName", firstName);
        params.put("lastName", lastName);

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
                            personalisedJokeView.showRandomJoke(joke);
                        }
                    });
        }
    }
}
