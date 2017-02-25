package exp.weather.presenters;

import javax.inject.Inject;
import exp.weather.data.CurrentWeatherDBSource;
import exp.weather.network.CurrentPOJO.CurrentWeather;
import exp.weather.interfaces.MainScreenContract;
import exp.weather.activities.MainActivity;

public class WeatherPresenter {

    MainScreenContract.WeatherView weatherView;
    MainActivity mainActivity;
    CurrentWeatherDBSource currentWeatherDBSource;

    @Inject
    public WeatherPresenter(MainActivity activity,
                            CurrentWeatherDBSource currentWeatherDBSource) {
        mainActivity = activity;
        this.currentWeatherDBSource = currentWeatherDBSource;
    }

    public void attachView(MainScreenContract.WeatherView weatherView) {
        this.weatherView = weatherView;
    }

    public void writeCurrentWeatherToDB(CurrentWeather currentWeather) {
        currentWeatherDBSource.writeCurrentWeatherData(currentWeather);
    }

    public void openSecondActivity() {
        mainActivity.openSecondActivity();
    }
}
