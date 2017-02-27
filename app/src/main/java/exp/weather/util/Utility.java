package exp.weather.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import exp.weather.R;
import exp.weather.network.ForecastPOJO.ForecastWeather;
import exp.weather.network.ForecastPOJO.List;

public class Utility {

    static final String SKY_STATE_CLEAR = "clear";
    static final String SKY_STATE_RAIN = "rain";
    static final String SKY_STATE_SNOW = "snow";
    static final String SKY_STATE_CLOUDS = "clouds";

    public static void changeTempColor(TextView temp, Context context) {
        if(temp.getText() != null) {
            double temperature = Double.parseDouble(temp.getText().toString());
            if (temperature <= -15) {
                temp.setTextColor(context.getResources().getColor(R.color.colorTempDarkBlue));
            }
            else if (temperature <= -3) {
                temp.setTextColor(context.getResources().getColor(R.color.colorTempBlue));
            }
            else if (temperature <= 5) {
                temp.setTextColor(context.getResources().getColor(R.color.colorTempZero));
            }
            else if (temperature <= 20) {
                temp.setTextColor(context.getResources().getColor(R.color.colorTempYellow));
            }
            else if (temperature <= 32) {
                temp.setTextColor(context.getResources().getColor(R.color.colorTempOrange));
            }
            else if (temperature > 32) {
                temp.setTextColor(context.getResources().getColor(R.color.colorTempRed));
            }
        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static void setSkyImage(ImageView skyIv, String sky) {
        switch (sky.toLowerCase()) {
            case SKY_STATE_RAIN:
                skyIv.setImageResource(R.drawable.rain);
                break;
            case SKY_STATE_CLEAR:
                skyIv.setImageResource(R.drawable.clear);
                break;
            case SKY_STATE_CLOUDS:
                skyIv.setImageResource(R.drawable.cloud);
                break;
            case SKY_STATE_SNOW:
                skyIv.setImageResource(R.drawable.snow);
                break;
            default:
        }
    }

    public static ForecastWeather getForecastPerDay(ForecastWeather forecastWeather) {
        ForecastWeather result = new ForecastWeather();
        java.util.ArrayList<List> totalList = new ArrayList<List>();

        Date currentDate = new Date(System.currentTimeMillis());
        DateFormat dateFormat = new SimpleDateFormat("dd");
        DateFormat curHf = new SimpleDateFormat("dd.hh:mm a");

        String currentDateFormatted = dateFormat.format(currentDate);
        String curHfFormatted = curHf.format(currentDate);

        java.util.List<exp.weather.network.ForecastPOJO.List> weatherList = forecastWeather.getList();

        for (List list: weatherList) {
            long timeSTamp = list.getDt();
            Date forecastDate = new Date(timeSTamp * 1000);
            DateFormat dateForecastFormat = new SimpleDateFormat("dd");
            String dateForecastFormatted = dateForecastFormat.format(forecastDate);
            DateFormat hourFormat = new SimpleDateFormat("hh:mm a");
            hourFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            String hourFormatted = hourFormat.format(forecastDate);

            if (!currentDateFormatted.equals(dateForecastFormatted) && hourFormatted.equals("03:00 PM")) {
                totalList.add(list);
            }
        }
        result.setList(totalList);

        return result;
    }
}
