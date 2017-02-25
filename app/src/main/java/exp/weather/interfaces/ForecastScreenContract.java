package exp.weather.interfaces;

import exp.weather.network.ForecastPOJO.ForecastWeather;

public interface ForecastScreenContract {

    public interface Presenter
    {

    }

    public interface View
    {
        void drawRecyclerView(ForecastWeather forecastWeather);
    }
}
