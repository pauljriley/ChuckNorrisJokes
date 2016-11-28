package uk.me.paulriley.chucknorrisjokes.views.endless;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.me.paulriley.chucknorrisjokes.ChuckNorrisJokesApplication;
import uk.me.paulriley.chucknorrisjokes.R;
import uk.me.paulriley.chucknorrisjokes.services.jokeResults.JokesFacade;

public class EndlessJokesActivity extends AppCompatActivity implements EndlessJokesView {
    private static final String ALLOW_EXPLICIT_JOKES = EndlessJokesActivity.class.getSimpleName()
            + "Allow_Explicit_Jokes";

    @Inject EndlessJokePresenter presenter;
    @Inject JokesFacade jokesFacade;

    @BindView(R.id.all_the_jokes_list) RecyclerView allTheJokesList;

    private boolean allowExplicitJokes;
    private JokesAdapter allTheJokesListAdapter;
    private List<String> jokes = new ArrayList<>();
    private LinearLayoutManager allTheJokesListLayoutManager;

    public static Intent buildIntent(Context activityContext, boolean allowExplicitJokes) {
        Intent intent = new Intent(activityContext, EndlessJokesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(EndlessJokesActivity.ALLOW_EXPLICIT_JOKES, allowExplicitJokes);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.endless_jokes);

        ChuckNorrisJokesApplication.get(this).inject(this);

        ButterKnife.bind(this);

        allTheJokesListLayoutManager = new LinearLayoutManager(this);
        allTheJokesList.setLayoutManager(allTheJokesListLayoutManager);
        allTheJokesListAdapter = new JokesAdapter();
        allTheJokesList.setAdapter(allTheJokesListAdapter);

        allTheJokesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int visibleItemCount = allTheJokesListLayoutManager.getItemCount();
                    int totalItemCount = allTheJokesListLayoutManager.getChildCount();
                    int pastVisibleItems = allTheJokesListLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        presenter.getAllTheJokes(allowExplicitJokes);
                    }
                }
            }
        });

        if (savedInstanceState == null) {
            if (getIntent().getExtras() != null) {
                extractAndProcessIntentExtras(getIntent());
            } else {
                this.finish();
            }
        }
    }

    public void updateData(List<String> newJokes) {
        jokes.addAll(newJokes);
        allTheJokesListAdapter.setData(jokes);
        this.runOnUiThread(() -> allTheJokesListAdapter.notifyDataSetChanged());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.initialise(this);
        presenter.getAllTheJokes(allowExplicitJokes);
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.destroy();
            presenter = null;
        }

        super.onDestroy();
    }


    private void extractAndProcessIntentExtras(Intent intent) {
        allowExplicitJokes = intent.getBooleanExtra(EndlessJokesActivity.ALLOW_EXPLICIT_JOKES, false);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
