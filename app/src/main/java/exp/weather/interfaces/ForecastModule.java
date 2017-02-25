package exp.weather.interfaces;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 30.01.2017.
 */
@Module
public class ForecastModule {
    private ForecastScreenContract.View view;


    public ForecastModule(ForecastScreenContract.View view)
    {
        this.view = view;
    }

    @Provides
    ForecastScreenContract.View providesForecastScreenContractView()
    {
        return view;
    }
}
