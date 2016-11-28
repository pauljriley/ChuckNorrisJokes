package uk.me.paulriley.chucknorrisjokes.views.endless;

public interface EndlessJokePresenter {
    void initialise(EndlessJokesView endlessJokesView);
    void destroy();

    void getAllTheJokes(boolean allowExplicitJokes);
}
