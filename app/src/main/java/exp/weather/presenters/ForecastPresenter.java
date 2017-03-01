package exp.weather.presenters;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;

import exp.weather.common.BaseFragmentPresenter;
import exp.weather.data.DBHelper;
import exp.weather.interfaces.IForecastView;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.network.ForecastPOJO.List;
import exp.weather.interfaces.ForecastScreenContract;

public class ForecastPresenter implements BaseFragmentPresenter<IForecastView> {

    private IForecastView view;

    @Inject
    DBHelper dbHelper;

    @Inject
    public ForecastPresenter() {}

    public void writeForecastWeatherToDB(ForecastWeather forecastWeather)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL("delete from " + DBHelper.TABLE_NAME_FORECAST);

        java.util.List<exp.weather.network.ForecastPOJO.List> weatherList = forecastWeather.getList();

        for(List list : weatherList)
        {
            long timeSTamp = list.getDt();
            Date forecastDate = new Date(timeSTamp * 1000);
            DateFormat dateForecastFormat = new SimpleDateFormat("MMMM.dd hh:mm a");

            String dateForecastFormatted = dateForecastFormat.format(forecastDate);
            String temp =  Double.toString(list.getMain().getTemp());
            String sky = list.getWeather().get(0).getMain();

            ContentValues cv = new ContentValues();

            cv.put(DBHelper.F_TABLE_DATE, dateForecastFormatted);
            cv.put(DBHelper.F_TEMP, temp);
            cv.put(DBHelper.F_SKY, sky);

            db.insert(DBHelper.TABLE_NAME_FORECAST, null, cv);
        }
    }

    @Override
    public void init(IForecastView view) {
        this.view = view;
    }
}
