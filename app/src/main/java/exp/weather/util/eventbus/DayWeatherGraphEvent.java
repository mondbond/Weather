package exp.weather.util.eventbus;

import java.util.Date;

public class DayWeatherGraphEvent {

    private Date date;

    public DayWeatherGraphEvent(Date day)
    {
        this.date = day;
    }

    public Date getDate() {
        return date;
    }

    public void setForecastWeather(Date forecastWeather) {
        this.date = forecastWeather;
    }
}
