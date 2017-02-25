package exp.weather.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.greenrobot.eventbus.EventBus;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import exp.weather.R;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.network.ForecastPOJO.List;
import exp.weather.util.Utility;
import exp.weather.util.eventbus.DayWeatherGraphEvent;

public class ForecastAdapter extends RecyclerView.Adapter {

    private ForecastWeather mForecastWeather;
    Context context;


    public ForecastAdapter(ForecastWeather data, Context context)
    {
        this.mForecastWeather = data;
        this.context = context;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View view;

        ViewHolder(View view)
        {
            super(view);
            this.view = view;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_day, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        View view = holder.itemView;

        TextView dateTv = (TextView) view.findViewById(R.id.forecastDayTv);
        TextView tempTv = (TextView) view.findViewById(R.id.forecastDayTempTv);
        ImageView skyIv = (ImageView) view.findViewById(R.id.forecastDayScyImg);

        List forecastWeather = mForecastWeather.getList().get(position);

        long timeSTamp = forecastWeather.getDt();

        final Date date = new Date(timeSTamp * 1000);
        DateFormat dateFormat = new SimpleDateFormat("MMMM.dd hh:mm a", Locale.getDefault());
        String dateFormatted = dateFormat.format(date);
        dateTv.setText(dateFormatted);

        double temp = forecastWeather.getMain().getTemp();
        tempTv.setText(Double.toString(temp));
        Utility.changeTempColor(tempTv, context);
        String sky = forecastWeather.getWeather().get(0).getMain();
        Utility.setSkyImage(skyIv, sky);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            EventBus.getDefault().post(new DayWeatherGraphEvent(date));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mForecastWeather.getList().size();
    }
}
