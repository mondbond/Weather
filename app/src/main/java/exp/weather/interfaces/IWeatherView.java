package exp.weather.interfaces;

import android.database.Cursor;

/**
 * Created by User on 27.02.2017.
 */

public interface IWeatherView {
    void setCurrentWeatherDataFromDb(Cursor cursor);
}
