package exp.weather.presenters;

import javax.inject.Inject;

import exp.weather.common.BaseFragmentPresenter;
import exp.weather.data.CurrentWeatherDBSource;
import exp.weather.interfaces.IWeatherView;
import exp.weather.network.CurrentPOJO.CurrentWeather;
import exp.weather.activities.MainActivity;

public class WeatherPresenter implements BaseFragmentPresenter<IWeatherView>{

    IWeatherView weatherView;
    MainActivity mainActivity;
    CurrentWeatherDBSource currentWeatherDBSource;

    @Inject
    public WeatherPresenter(MainActivity activity,
                            CurrentWeatherDBSource currentWeatherDBSource) {
        mainActivity = activity;
        this.currentWeatherDBSource = currentWeatherDBSource;
    }

    public void writeCurrentWeatherToDB(CurrentWeather currentWeather) {
        currentWeatherDBSource.writeCurrentWeatherData(currentWeather);
    }

    public void openSecondActivity() {
        mainActivity.openSecondActivity();
    }

    @Override
    public void init(IWeatherView view) {
        weatherView = view;
    }
}
