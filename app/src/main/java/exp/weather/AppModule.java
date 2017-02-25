package exp.weather;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import dagger.Module;
import dagger.Provides;
import exp.weather.data.DBHelper;
import exp.weather.network.OpenWeatherClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private App application;

    AppModule(App  application)
    {
        this.application = application;
    }

    @Provides
    public Context providesContext()
    {
        return application.getApplicationContext();
    }

    @Provides
    Retrofit providesRetrofit() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .build();
        return retrofit;
    }

    @Provides
    public DBHelper providesDBHelper()
    {
        return new DBHelper(application.getApplicationContext());
    }
}
