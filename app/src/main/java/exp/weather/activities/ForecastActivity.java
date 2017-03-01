package exp.weather.activities;

import android.content.Intent;
import android.support.v4.app.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import exp.weather.App;
import exp.weather.AppComponent;
import exp.weather.R;
import exp.weather.common.BaseActivity;
import exp.weather.common.IHasComponent;
import exp.weather.interfaces.DaggerForecastComponent;
import exp.weather.interfaces.ForecastComponent;
import exp.weather.interfaces.ForecastModule;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.fragments.DayFragment;
import exp.weather.fragments.ForecastFragment;

import static exp.weather.App.get;

public class ForecastActivity extends BaseActivity implements IHasComponent<ForecastComponent> {

    public static final String FORECAST_WEATHER = "forecast weather";

    private ForecastComponent mComponent;
    private ForecastFragment mForecastFragment;
    private DayFragment mDayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.forecast_activity_start,
                R.anim.forecast_activity_close);

        setContentView(R.layout.activity_forecast);

        Intent intent = getIntent();
        if(intent.getExtras() != null)
        {
            FragmentManager fm = getSupportFragmentManager();

            Bundle bundle = intent.getExtras();
            ForecastWeather forecastWeather = bundle.getParcelable(FORECAST_WEATHER);

            if (fm.getFragments() != null && !fm.getFragments().isEmpty())
            {
                mDayFragment = (DayFragment) fm.getFragments().get(0);
                mForecastFragment = (ForecastFragment) fm.getFragments().get(1);
            }
            else {
                mDayFragment = new DayFragment();
                mForecastFragment = new ForecastFragment();

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.dayContainer, mDayFragment);
                ft.replace(R.id.listContainer, mForecastFragment);
                ft.commit();

                mDayFragment.setForecastWeather(forecastWeather);
                mForecastFragment.startFragment(forecastWeather);
            }
        }
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        mComponent = DaggerForecastComponent.builder()
                .appComponent(((App) get(this).getApplicationContext()).getAppComponent())
                .forecastModule(new ForecastModule())
                .build();

        mComponent.inject(this);
    }

    @Override
    public ForecastComponent getComponent() {
        return mComponent;
    }
}
