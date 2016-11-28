package uk.me.paulriley.chucknorrisjokes.views.personalise;

import android.content.Context;

public interface PersonalisedJokeView {
    Context getContext();
    void showRandomJoke(String joke);
}
