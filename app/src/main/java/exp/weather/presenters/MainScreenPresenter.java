package exp.weather.presenters;

import android.content.Context;
import android.support.v4.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.Toast;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import exp.weather.data.CurrentWeatherDBSource;
import exp.weather.network.CurrentPOJO.CurrentWeather;
import exp.weather.data.DBHelper;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.activities.MainActivity;
import exp.weather.network.OpenWeatherClient;
import exp.weather.util.Utility;
import retrofit2.Retrofit;

public class MainScreenPresenter implements LoaderManager.LoaderCallbacks<Cursor> {

    @Inject
    Retrofit retrofit;

    @Inject
    DBHelper dbHelper;

    MainActivity mainActivity;
    OpenWeatherClient client;
    CurrentWeatherDBSource currentWeatherDBSource;

    @Inject
    public MainScreenPresenter(MainActivity activity,
                               OpenWeatherClient client,
                               CurrentWeatherDBSource currentWeatherDBSource) {
        mainActivity = activity;
        this.client = client;
        this.currentWeatherDBSource = currentWeatherDBSource;
    }

    public void makeWeatherRequest(String cityName) {

        if(Utility.isOnline(mainActivity)) {

            currentWeatherDBSource.clearCurrentWeatherDB();

            ClientCallback clientCallback = new ClientCallback();

            client.registerCallback(clientCallback);
            client.getCurrentWeatherData(cityName);
            client.getForecastWeatherData(cityName);

        }
        else {
            Toast.makeText(mainActivity, "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            mainActivity.getSupportLoaderManager().initLoader(0, null, this);
            mainActivity.getSupportLoaderManager().getLoader(0).forceLoad();
            startLoadingAnimation();
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new DbLoader(mainActivity, currentWeatherDBSource);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        setCurrentWeatherFromDB(data);
        stopLoadingAnimation();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private class ClientCallback implements OpenWeatherClient.ClientCallback
    {

        @Override
        public void onWeatherResponse(CurrentWeather currentWeather) {
            mainActivity.setCurrentWeather(currentWeather);
        }

        @Override
        public void onWeatherResponse(ForecastWeather forecastWeather) {
            mainActivity.setForecastWeather(forecastWeather);
        }
    }

    static public class DbLoader extends CursorLoader {

        CurrentWeatherDBSource currentWeatherDBSource;

        public DbLoader(Context context, CurrentWeatherDBSource currentWeatherDBSource) {
            super(context);
            this.currentWeatherDBSource = currentWeatherDBSource;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = currentWeatherDBSource.getLastCurrentWeatherData();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
         }
            return cursor;
        }
    }

    private void setCurrentWeatherFromDB(Cursor cursor)
    {
        mainActivity.setCurrentWeatherFromDb(cursor);
    }

    public void startLoadingAnimation()
    {
      mainActivity.startLoadingAnimation();
    }

    public void stopLoadingAnimation()
    {
        mainActivity.hideLoadingAnimation();
    }
}
