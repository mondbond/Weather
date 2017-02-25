package exp.weather;


import dagger.Component;
import exp.weather.data.DBHelper;
import retrofit2.Retrofit;

@Component(modules = {AppModule.class})
public interface AppComponent {
    public Retrofit getRetrofit();
    public DBHelper getDBHelper();
}
