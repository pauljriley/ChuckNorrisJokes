package uk.me.paulriley.chucknorrisjokes.views.main;

public interface MainPresenter {
    void initialise(MainView mainView);
    void destroy();

    void getRandomJoke(boolean allowExplicitJokes);
}
