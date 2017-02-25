package exp.weather.network;


//import exp.weather.data.CurrentWeatherPOJO.CurrentWeatherPOJO;
import exp.weather.network.CurrentPOJO.CurrentWeather;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherAPI {

    @GET("data/2.5/weather?")
    public Call<CurrentWeather> getWeatherByCityName(@Query("q") String city,
                                                     @Query("units") String units,
                                                     @Query("APPID") String key);


    @GET("data/2.5/forecast?")
    public Call<ForecastWeather> getForecastByCityName(@Query("q") String city,
                                                       @Query("units") String units,
                                                       @Query("APPID") String key);
}
