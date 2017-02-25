package exp.weather.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import javax.inject.Inject;
import exp.weather.App;
import exp.weather.R;
import exp.weather.interfaces.DaggerMainScreenComponent;
import exp.weather.network.CurrentPOJO.CurrentWeather;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.fragments.DayFragment;
import exp.weather.fragments.ForecastFragment;
import exp.weather.interfaces.MainScreenComponent;
import exp.weather.interfaces.MainScreenModule;
import exp.weather.presenters.MainScreenPresenter;
import exp.weather.fragments.SearchFragment;
import exp.weather.fragments.WeatherFragment;

public class MainActivity extends AppCompatActivity {

    public static MainScreenComponent sComponent;

    private SearchFragment mSearchFragment;
    private WeatherFragment mWeatherFragment;
    private ForecastFragment mForecastFragment;
    private DayFragment mDayFragment;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    protected boolean isLargeDevice;

    private ForecastWeather mForecastWeather;
    private CurrentWeather mCurrentWeather;

    @Inject
    MainScreenPresenter presenter;

    View loadingCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.main_activity_animation_start,
                R.anim.main_activity_animation_close);

        setContentView(R.layout.activity_main);

        FrameLayout forecastFragmentContainer = (FrameLayout) findViewById(R.id.forecastFragmentContainer);
        if(forecastFragmentContainer != null) {
            isLargeDevice = true;
        }

        loadingCircle = findViewById(R.id.shape);
        loadingCircle.setVisibility(View.GONE);

        FragmentManager fm = getSupportFragmentManager();

        if (getSupportFragmentManager().getFragments() != null && !getSupportFragmentManager().getFragments().isEmpty()) {
            mSearchFragment = (SearchFragment) fm.getFragments().get(0);
            mWeatherFragment = (WeatherFragment) fm.getFragments().get(1);
        }else {
            mSearchFragment = new SearchFragment();
            mWeatherFragment = new WeatherFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.searchContainer, mSearchFragment);
            ft.replace(R.id.weatherContainer, mWeatherFragment);
            ft.commit();
        }



        if (isLargeDevice) {
                mDayFragment = new DayFragment();
                mForecastFragment = new ForecastFragment();

                FragmentTransaction forecastFragmentTransaction = fm.beginTransaction();
                forecastFragmentTransaction.replace(R.id.forecastFragmentContainer, mForecastFragment);
                forecastFragmentTransaction.replace(R.id.forecastDayContainer, mDayFragment);
                forecastFragmentTransaction.commit();
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.makeWeatherRequest(mSearchFragment.getCityName());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        sComponent = DaggerMainScreenComponent.builder()
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .mainScreenModule(new MainScreenModule(this, mSearchFragment))
                .build();

        sComponent.inject(this);
        mSearchFragment.setComponent(sComponent);
        mWeatherFragment.setComponent(sComponent);
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.mCurrentWeather = currentWeather;
        fragmentsTrigger();
    }

    public void setForecastWeather(ForecastWeather forecastWeather) {
        this.mForecastWeather = forecastWeather;
        fragmentsTrigger();
    }

    public void fragmentsTrigger()
    {
        if(mForecastWeather != null && mCurrentWeather != null)
        {
            mWeatherFragment.setLarge(isLargeDevice);
            mWeatherFragment.startFragment(mCurrentWeather);
            if(isLargeDevice) {
                mDayFragment.setForecastWeather(mForecastWeather);
                mForecastFragment.setLarge(true);
                mForecastFragment.startFragment(mForecastWeather);
            }
        }
    }

    public void openSecondActivity()
    {
        if(mForecastWeather != null) {
            Intent listIntent = new Intent(this, ForecastActivity.class);
            listIntent.putExtra(ForecastActivity.FORECAST_WEATHER, mForecastWeather);
            startActivity(listIntent);
        }
    }

    public void setCurrentWeatherFromDb(Cursor cursor)
    {
        mWeatherFragment.setCurrentWeatherDataFromDb(cursor);
    }

    public void startLoadingAnimation()
    {
        loadingCircle.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotation);
        loadingCircle.setAnimation(animation);
    }

    public void hideLoadingAnimation()
    {
        loadingCircle.setAnimation(null);
        loadingCircle.setVisibility(View.GONE);
    }
}
