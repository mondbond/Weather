package exp.weather.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import javax.inject.Inject;
import exp.weather.network.CurrentPOJO.CurrentWeather;

public class CurrentWeatherDBSource {

    DBHelper dbHelper;

    @Inject
    CurrentWeatherDBSource(DBHelper dbHelper)
    {
        this.dbHelper = dbHelper;
    }

    public void clearCurrentWeatherDB()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL("delete from " + DBHelper.TABLE_NAME_CURRENT);
        db.execSQL("delete from " + DBHelper.TABLE_NAME_FORECAST);
        db.close();
    }

    public void writeCurrentWeatherData(CurrentWeather currentWeather)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from " + DBHelper.TABLE_NAME_CURRENT);
        ContentValues cv = new ContentValues();

        String city = currentWeather.getName();
        double temp = currentWeather.getMain().getTemp();
        double wind = currentWeather.getWind().getSpeed();
        double pressure = currentWeather.getMain().getPressure();
        int humidity = currentWeather.getMain().getHumidity();
        String sky = currentWeather.getWeather().get(0).getMain();

        cv.put(DBHelper.CITY, city);
        cv.put(DBHelper.TEMP, Double.toString(temp));
        cv.put(DBHelper.WIND, Double.toString(wind));
        cv.put(DBHelper.A_PRESS, Double.toString(pressure));
        cv.put(DBHelper.HUMIDITY, Integer.toString(humidity));
        cv.put(DBHelper.SKY, sky);

        db.insertOrThrow(DBHelper.TABLE_NAME_CURRENT, null, cv);
        db.close();
    }

    public Cursor getLastCurrentWeatherData()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String columns[] = {
                DBHelper.CITY,
                DBHelper.TEMP,
                DBHelper.WIND,
                DBHelper.A_PRESS,
                DBHelper.HUMIDITY,
                DBHelper.SKY,
        };
        Cursor cursor = db.query(DBHelper.TABLE_NAME_CURRENT, columns, null, null, null, null, null);

        return cursor;
    }
}
