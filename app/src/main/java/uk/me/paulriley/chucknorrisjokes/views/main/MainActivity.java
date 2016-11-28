package uk.me.paulriley.chucknorrisjokes.views.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.devspark.robototextview.widget.RobotoCheckedTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.me.paulriley.chucknorrisjokes.ChuckNorrisJokesApplication;
import uk.me.paulriley.chucknorrisjokes.R;
import uk.me.paulriley.chucknorrisjokes.views.endless.EndlessJokesActivity;
import uk.me.paulriley.chucknorrisjokes.views.personalise.PersonalisedJokeActivity;

import static uk.me.paulriley.chucknorrisjokes.utility.StringFormating.fromHtml;

public class MainActivity extends AppCompatActivity implements MainView {
    @Inject MainPresenter mainPresenter;

    @BindView(R.id.allow_explicit_jokes)
    RobotoCheckedTextView allowExplicitJokes;

    @OnClick(R.id.random_joke) void getRandomJoke() {
        mainPresenter.getRandomJoke(allowExplicitJokes.isChecked());
    }

    @OnClick(R.id.personalised_joke) void getPersonalisedJoke() {
        startActivity(PersonalisedJokeActivity.buildIntent(this, allowExplicitJokes.isChecked()));
    }

    @OnClick(R.id.all_the_jokes) void getAllTheJokes() {
        startActivity(EndlessJokesActivity.buildIntent(this, allowExplicitJokes.isChecked()));
        //mainPresenter.getAllTheJokes(allowExplicitJokes.isChecked());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChuckNorrisJokesApplication.get(this).inject(this);

        ButterKnife.bind(this);

        if (allowExplicitJokes != null) {
            allowExplicitJokes.setOnClickListener(view
                    -> allowExplicitJokes.setChecked(!allowExplicitJokes.isChecked()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.initialise(this);
    }

    @Override
    protected void onDestroy() {
        if (mainPresenter != null) {
            mainPresenter.destroy();
            mainPresenter = null;
        }

        super.onDestroy();
    }


    @Override
    public void showRandomJoke(String joke) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        this.runOnUiThread(() -> {
            builder.setMessage(fromHtml(joke))
                    .setNegativeButton("Dismiss", (dialogInterface, i) -> dialogInterface.dismiss());

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    @Override
    public void showPersonalisedJoke() {
        Toast.makeText(this, "Personal joke", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAllTheJokes() {
        Toast.makeText(this, "All the jokes", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
