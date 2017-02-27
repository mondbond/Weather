package exp.weather.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import exp.weather.App;
import exp.weather.AppComponent;

/**
 * Created by User on 27.02.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(App.get(this).getAppComponent());
    }

    protected abstract void setupComponent(AppComponent appComponent);
}
