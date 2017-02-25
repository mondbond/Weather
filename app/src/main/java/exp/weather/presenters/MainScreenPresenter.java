package exp.weather.presenters;

import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.Toast;
import javax.inject.Inject;
import exp.weather.data.CurrentWeatherDBSource;
import exp.weather.network.CurrentPOJO.CurrentWeather;
import exp.weather.data.DBHelper;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.activities.MainActivity;
import exp.weather.network.OpenWeatherClient;
import exp.weather.util.Utility;
import retrofit2.Retrofit;

public class MainScreenPresenter {

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
            AsyncDBRequest asyncDBRequest = new AsyncDBRequest();
            asyncDBRequest.execute();
        }
    }

    public Cursor getLastResults()
    {
        Cursor cursor = currentWeatherDBSource.getLastCurrentWeatherData();
        return cursor;
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

    public class AsyncDBRequest extends AsyncTask
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            startLoadingAnimation();
        }

        @Override
        protected Cursor doInBackground(Object[] objects) {

        // imitate work for showing animation
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){}


            Cursor cursor = getLastResults();
            return cursor;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            setCurrentWeatherFromDB((Cursor) o);
            stopLoadingAnimation();
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
