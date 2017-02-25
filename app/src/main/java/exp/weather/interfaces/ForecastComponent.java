package exp.weather.interfaces;

import dagger.Component;
import exp.weather.AppComponent;
import exp.weather.fragments.ForecastFragment;

/**
 * Created by User on 30.01.2017.
 */

@Component( modules = {ForecastModule.class}, dependencies = {AppComponent.class})
public interface ForecastComponent {

    void inject(ForecastFragment forecastFragment);
}
