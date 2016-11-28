package uk.me.paulriley.chucknorrisjokes.views.main;

import android.app.Application;
import android.content.Context;

public interface MainView {
    void showRandomJoke(String joke);
    void showPersonalisedJoke();
    void showAllTheJokes();
    Application getApplication();
    Context getContext();
}
