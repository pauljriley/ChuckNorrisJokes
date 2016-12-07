package uk.me.paulriley.chucknorrisjokes;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import dagger.ObjectGraph;
import io.fabric.sdk.android.Fabric;
import uk.me.paulriley.chucknorrisjokes.injection.Injector;
import uk.me.paulriley.chucknorrisjokes.injection.modules.ApplicationModule;

public class ChuckNorrisJokesApplication extends Application implements Injector {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        installLeakCanary();

        Fabric.with(this, new Crashlytics());

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

    protected RefWatcher installLeakCanary() {
        return !BuildConfig.BUILD_TYPE.equals("debug") ? RefWatcher.DISABLED : LeakCanary.install(this);
    }
}
