package uk.me.paulriley.chucknorrisjokes.injection.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.me.paulriley.chucknorrisjokes.services.jokeResults.JokesFacade;
import uk.me.paulriley.chucknorrisjokes.views.endless.EndlessJokePresenter;
import uk.me.paulriley.chucknorrisjokes.views.endless.EndlessJokePresenterImpl;
import uk.me.paulriley.chucknorrisjokes.views.endless.EndlessJokesActivity;
import uk.me.paulriley.chucknorrisjokes.views.main.MainActivity;
import uk.me.paulriley.chucknorrisjokes.views.main.MainPresenter;
import uk.me.paulriley.chucknorrisjokes.views.main.MainPresenterImpl;
import uk.me.paulriley.chucknorrisjokes.views.personalise.PersonalisedJokeActivity;
import uk.me.paulriley.chucknorrisjokes.views.personalise.PersonalisedPresenter;
import uk.me.paulriley.chucknorrisjokes.views.personalise.PersonalisedPresenterImpl;

@Module(
        injects = {
            MainPresenterImpl.class,
            PersonalisedPresenterImpl.class,
            EndlessJokePresenterImpl.class,
            MainActivity.class,
            PersonalisedJokeActivity.class,
            EndlessJokesActivity.class
        },
        library = true,
        complete = false
)
public class ApplicationModule {

    private JokesFacade mJokesFacade;

    public ApplicationModule() {

    }

    @Provides @Singleton
    public JokesFacade provideJokesFacade() {
        if (mJokesFacade == null) {
            mJokesFacade = new JokesFacade();
        }

        return mJokesFacade;
    }

    @Provides @Singleton
    public MainPresenter providesMainPresenter() {
        return new MainPresenterImpl();
    }

    @Provides @Singleton
    public PersonalisedPresenter providesPersonalisedPresenter() {
        return new PersonalisedPresenterImpl();
    }

    @Provides @Singleton
    public EndlessJokePresenter providesEndlessJokePresenter() {
        return new EndlessJokePresenterImpl();
    }
}
