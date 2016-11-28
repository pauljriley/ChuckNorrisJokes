package uk.me.paulriley.chucknorrisjokes;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;
import uk.me.paulriley.chucknorrisjokes.injection.Injector;
import uk.me.paulriley.chucknorrisjokes.injection.modules.ApplicationModule;

public class ChuckNorrisJokesApplication extends Application implements Injector {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationModule applicationModule = new ApplicationModule();
        objectGraph = ObjectGraph.create(applicationModule);
    }

    public static ChuckNorrisJokesApplication get(Context context) {
        return (ChuckNorrisJokesApplication) context.getApplicationContext();
    }

    @Override
    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
