package exp.weather.interfaces;

import android.database.Cursor;


/**
 * Created by User on 28.01.2017.
 */

public interface MainScreenContract {

    interface Presenter
    {
    }

    interface WeatherView
    {
        public  void setCurrentWeatherDataFromDb(Cursor cursor);
    }

}
