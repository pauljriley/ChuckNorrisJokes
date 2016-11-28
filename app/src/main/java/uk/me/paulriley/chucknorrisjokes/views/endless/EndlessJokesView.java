package uk.me.paulriley.chucknorrisjokes.views.endless;

import android.content.Context;

import java.util.List;

public interface EndlessJokesView {
    Context getContext();
    void updateData(List<String> jokes);
}
