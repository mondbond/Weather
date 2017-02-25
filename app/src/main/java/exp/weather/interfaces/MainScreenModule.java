package exp.weather.interfaces;


import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import exp.weather.presenters.MainScreenPresenter;
import exp.weather.activities.MainActivity;
import exp.weather.util.MainScope;

@MainScope
@Module
public class MainScreenModule {

    private MainActivity activity;

    private MainScreenContract.WeatherView weatherView;

    @Inject
    MainScreenPresenter presenter;

    public MainScreenModule (MainActivity activity, MainScreenContract.WeatherView weatherView)
    {
        this.activity = activity;
        this.weatherView = weatherView;
    }

    @Provides
    MainActivity providesMainActivity() {
        return activity;
    }

    @Provides
    MainScreenContract.WeatherView providesMainScreenContractView()
    {
        return weatherView;
    }
//
//    @Provides
//    Retrofit providesRetrofit()
//    {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.openweathermap.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        return retrofit;
//    }

}
