package exp.weather.network;

import javax.inject.Inject;
import exp.weather.network.CurrentPOJO.CurrentWeather;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OpenWeatherClient {

    private Retrofit retrofit;

    private final String APPI_KAY = "315f733102bed490d78d6547c08d2b88";
    private final String UNITS = "metric";

    private CurrentWeather mCurrentWeather;
    private ForecastWeather mForecastWeather;

    private OpenWeatherClient.ClientCallback mClientCallback;

    @Inject
    OpenWeatherClient(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void getCurrentWeatherData(String cityName) {
        OpenWeatherAPI weatherAPI = retrofit.create(OpenWeatherAPI.class);
        weatherAPI.getWeatherByCityName(cityName, UNITS, APPI_KAY)
        .enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response.isSuccessful()) {
                    mCurrentWeather = response.body();
                    if(mClientCallback != null)
                    {
                        mClientCallback.onWeatherResponse(mCurrentWeather);
                    }
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getForecastWeatherData(String cityName) {
        OpenWeatherAPI api = retrofit.create(OpenWeatherAPI.class);
        api.getForecastByCityName(cityName, UNITS, APPI_KAY)
        .enqueue(new Callback<ForecastWeather>() {
            @Override
            public void onResponse(Call<ForecastWeather> call, Response<ForecastWeather> response) {
                if (response.isSuccessful()) {
                    mForecastWeather = response.body();
                    if(mClientCallback != null)
                    {
                        mClientCallback.onWeatherResponse(mForecastWeather);
                    }
                }
            }

            @Override
            public void onFailure(Call<ForecastWeather> call, Throwable t) {
                t.printStackTrace();
            }
        });

        if(mClientCallback != null)
        {
            mClientCallback.onWeatherResponse(mForecastWeather);
        }
    }

    public  void registerCallback(OpenWeatherClient.ClientCallback clientCallback) {
        this.mClientCallback = clientCallback;
    }

    public interface ClientCallback {
        void onWeatherResponse(CurrentWeather currentWeather);
        void onWeatherResponse(ForecastWeather forecastWeather);
    }

}
