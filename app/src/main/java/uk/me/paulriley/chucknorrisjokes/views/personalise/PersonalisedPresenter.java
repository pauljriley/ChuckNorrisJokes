package uk.me.paulriley.chucknorrisjokes.views.personalise;

public interface PersonalisedPresenter {
    void initialise(PersonalisedJokeView personalisedJokeView);
    void destroy();

    void getPersonalisedJoke(String firstName, String lastName, boolean allowExplicitJokes);
}
