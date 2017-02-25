package exp.weather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import exp.weather.R;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.network.ForecastPOJO.List;
import exp.weather.util.eventbus.DayWeatherGraphEvent;
import exp.weather.custom_views.DayGraph;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayFragment extends Fragment {

    private ForecastWeather mForecastWeather;
    private DayGraph mDayGraph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        EventBus.getDefault().register(this);
        View v = inflater.inflate(R.layout.fragment_day, container, false);

        mDayGraph = (DayGraph) v.findViewById(R.id.dayGraph);

        return v;
    }

    @Subscribe
    public void getForecastDay(DayWeatherGraphEvent event) {
        ArrayList<Float> tempList = getChoosenDayTemp(event.getDate());
        redrawGraph(tempList);
    }

    public ArrayList<Float> getChoosenDayTemp(Date chosenDate)
    {
        ArrayList<Float> tempList = new ArrayList<Float>();

        DateFormat dateFormat = new SimpleDateFormat("MMMM.dd", Locale.getDefault());
        String chosenDateFormatted = dateFormat.format(chosenDate);

        java.util.List<exp.weather.network.ForecastPOJO.List> weatherList = mForecastWeather.getList();
        Iterator<List> iterator = weatherList.iterator();

        while (iterator.hasNext()) {

            List list = iterator.next();
            long timeSTamp = list.getDt();
            Date forecastDate = new Date(timeSTamp * 1000);
            DateFormat dateForecastFormat = new SimpleDateFormat("MMMM.dd", Locale.getDefault());
            String dateForecastFormatted = dateForecastFormat.format(forecastDate);

            if (chosenDateFormatted.equals(dateForecastFormatted)) {
                tempList.add(Float.parseFloat(Double.toString(list.getMain().getTemp())));
            }
        }

        return tempList;
    }

    public void setForecastWeather(ForecastWeather forecastWeather) {
        this.mForecastWeather = forecastWeather;
    }

    public void redrawGraph(ArrayList<Float> tempList)
    {
        mDayGraph.setNewTemp(tempList);
    }
}
