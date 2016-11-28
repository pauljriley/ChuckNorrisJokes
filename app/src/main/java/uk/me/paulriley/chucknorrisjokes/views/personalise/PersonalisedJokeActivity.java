package uk.me.paulriley.chucknorrisjokes.views.personalise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import com.devspark.robototextview.widget.RobotoEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.me.paulriley.chucknorrisjokes.ChuckNorrisJokesApplication;
import uk.me.paulriley.chucknorrisjokes.R;

import static uk.me.paulriley.chucknorrisjokes.utility.StringFormating.fromHtml;

public class PersonalisedJokeActivity extends AppCompatActivity implements PersonalisedJokeView {
    private static final String ALLOW_EXPLICIT_JOKES = PersonalisedJokeActivity.class.getSimpleName()
            + "Allow_Explicit_Jokes";

    @Inject PersonalisedPresenter presenter;

    @BindView(R.id.personalised_name)
    RobotoEditText personalised_name;
    private String[] names;

    @OnClick(R.id.submit_name)
    public void submitName() {
        if (validFormat) {
            presenter.getPersonalisedJoke(names[0], names[1], allowExplicitJokes);
        }
    }

    private boolean allowExplicitJokes;
    private boolean validFormat;

    public static Intent buildIntent(Context activityContext, boolean allowExplicitJokes) {
        Intent intent = new Intent(activityContext, PersonalisedJokeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(PersonalisedJokeActivity.ALLOW_EXPLICIT_JOKES, allowExplicitJokes);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalised_joke);

        ChuckNorrisJokesApplication.get(this).inject(this);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            if (getIntent().getExtras() != null) {
                extractAndProcessIntentExtras(getIntent());
            } else {
                this.finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.initialise(this);
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
        allowExplicitJokes = intent.getBooleanExtra(PersonalisedJokeActivity.ALLOW_EXPLICIT_JOKES, false);
        personalised_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                names = editable.toString().split(" ");
                if (names.length != 2) {
                    personalised_name.setError("Name should be in the form first name last name with a space separating the names");
                    validFormat = false;
                } else {
                    validFormat = true;
                }
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showRandomJoke(String joke) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        this.runOnUiThread(() -> {
            builder.setMessage(fromHtml(joke))
                    .setNegativeButton("Dismiss", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        finish();
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}
