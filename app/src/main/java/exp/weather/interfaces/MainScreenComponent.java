package exp.weather.interfaces;

import dagger.Component;
import exp.weather.AppComponent;
import exp.weather.activities.MainActivity;
import exp.weather.fragments.SearchFragment;
import exp.weather.fragments.WeatherFragment;

@Component(dependencies = {AppComponent.class}, modules = {MainScreenModule.class})
public interface MainScreenComponent {


    public void inject(SearchFragment searchFragment);
    public void inject(WeatherFragment weatherFragment);
    public void inject(MainActivity mainActivity);
}
