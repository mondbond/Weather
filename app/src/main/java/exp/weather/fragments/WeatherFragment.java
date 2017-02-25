package exp.weather.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import javax.inject.Inject;
import exp.weather.R;
import exp.weather.network.CurrentPOJO.CurrentWeather;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.interfaces.MainScreenComponent;
import exp.weather.interfaces.MainScreenContract;
import exp.weather.presenters.WeatherPresenter;
import exp.weather.util.Utility;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements MainScreenContract.WeatherView {

    ImageView skyIv;
    TextView tempTv;
    TextView cityNameTv;
    TextView windTv;
    TextView pressureTv;
    TextView humidityTv;
    Button getDaysBtn;

    @Inject
    WeatherPresenter presenter;

    MainScreenComponent component;

    private boolean isLarge;

    private final String CURRENT_WEATHER = "currentWeather";
    private final String FORECAST_WEATHER = "forecastWeather";

    private CurrentWeather mCurrentWeather;
    private ForecastWeather mForecastWeather;

    Bundle mSavedInstantState;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);

        skyIv = (ImageView) v.findViewById(R.id.scyImg);
        tempTv = (TextView) v.findViewById(R.id.tempTv);
        cityNameTv = (TextView) v.findViewById(R.id.cityNameTv);

        windTv = (TextView) v.findViewById(R.id.windTv);
        pressureTv = (TextView) v.findViewById(R.id.pressureTv);
        humidityTv = (TextView) v.findViewById(R.id.humidityTv);

        getDaysBtn = (Button) v.findViewById(R.id.getDaysBtn);

        getDaysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openSecondActivity();
            }
        });

        if(savedInstanceState != null) {
            mSavedInstantState = savedInstanceState;
            mCurrentWeather = savedInstanceState.getParcelable(CURRENT_WEATHER);
            setCurrentWeather(mCurrentWeather);
            mForecastWeather = savedInstanceState.getParcelable(FORECAST_WEATHER);
        }

        presenter.attachView(this);
        return  v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mSavedInstantState == null)
        {
            getView().setVisibility(View.INVISIBLE);
        }
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {

        String city = currentWeather.getName();
        double temp = currentWeather.getMain().getTemp();
        double wind = currentWeather.getWind().getSpeed();
        double pressure = currentWeather.getMain().getPressure();
        int humidity = currentWeather.getMain().getHumidity();
        String sky = currentWeather.getWeather().get(0).getMain();
        Utility.setSkyImage(skyIv, sky);

        tempTv.setText(Double.toString(temp));
        Utility.changeTempColor(tempTv, getActivity());
        cityNameTv.setText(city);

        windTv.setText(Double.toString(wind));
        pressureTv.setText(Double.toString(pressure));
        humidityTv.setText(Integer.toString(humidity));

        presenter.writeCurrentWeatherToDB(currentWeather);
    }

    public void startFragment(CurrentWeather currentWeather) {

        if(isLarge) {
            getDaysBtn.setVisibility(View.INVISIBLE);
        }

        this.mCurrentWeather = currentWeather;
        getView().setVisibility(View.VISIBLE);
        setCurrentWeather(mCurrentWeather);
    }

    public void setLarge(boolean large) {
        isLarge = large;
    }

    public void setCurrentWeatherDataFromDb(Cursor cursor)
    {
        getView().setVisibility(View.VISIBLE);

        if(cursor.moveToFirst()) {
            cityNameTv.setText(cursor.getString(0));
            tempTv.setText(Double.toString(cursor.getDouble(1)));
            Utility.changeTempColor(tempTv, getActivity());

            windTv.setText(Double.toString(cursor.getDouble(2)));
            pressureTv.setText(Double.toString(cursor.getDouble(3)));
            humidityTv.setText(Double.toString(cursor.getDouble(4)));
            String sky = cursor.getString(5);

            Utility.setSkyImage(skyIv, sky);
        }
    }

    public void setComponent(MainScreenComponent mS) {
        component = mS;
        component.inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_WEATHER, mCurrentWeather);
        outState.putParcelable(FORECAST_WEATHER, mForecastWeather);
    }
}
