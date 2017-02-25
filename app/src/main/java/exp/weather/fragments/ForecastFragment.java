package exp.weather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import javax.inject.Inject;
import exp.weather.App;
import exp.weather.R;
import exp.weather.interfaces.DaggerForecastComponent;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.interfaces.ForecastComponent;
import exp.weather.interfaces.ForecastModule;
import exp.weather.interfaces.ForecastScreenContract;
import exp.weather.presenters.ForecastPresenter;
import exp.weather.adapters.ForecastAdapter;
import exp.weather.util.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment implements ForecastScreenContract.View {

    private final String FORECAST_WEATHER = "forecastWeather";
    private ForecastComponent mComponent;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ForecastWeather mForecastWeather;
    private boolean isLarge;

    @Inject
    ForecastPresenter presenter;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponent = DaggerForecastComponent.builder()
                .appComponent(((App) getActivity().getApplicationContext()).getAppComponent())
                .forecastModule(new ForecastModule(this))
                .build();

        mComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_forecast, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.forecastRv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        if(savedInstanceState != null) {
            mRecyclerView.setAdapter(new ForecastAdapter(
                    (ForecastWeather) savedInstanceState.getParcelable(FORECAST_WEATHER), getActivity()));
        }else
        {
            if(mForecastWeather != null) {
                drawRecyclerView(mForecastWeather);
            }
        }

        return v;
    }

        public void drawRecyclerView(ForecastWeather forecastWeather)
        {
        ForecastWeather forecastWeatherPerDay = Utility.getForecastPerDay(forecastWeather);
        mAdapter = new ForecastAdapter(forecastWeatherPerDay, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        presenter.writeForecastWeatherToDB(forecastWeatherPerDay);
    }

    public void startFragment(ForecastWeather forecastWeather) {
        mForecastWeather = forecastWeather;

        if(isLarge) {
            drawRecyclerView(mForecastWeather);
        }
    }

    public void setLarge(boolean large) {
        isLarge = large;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(FORECAST_WEATHER, mForecastWeather);
    }
}