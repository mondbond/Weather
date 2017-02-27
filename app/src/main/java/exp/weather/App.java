package exp.weather;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private  static AppComponent appComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        buildGraphAndInject();
    }

    public static AppComponent getAppComponent()
    {
        return appComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public void buildGraphAndInject() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}
