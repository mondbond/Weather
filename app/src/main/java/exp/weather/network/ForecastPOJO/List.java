
package exp.weather.network.ForecastPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class List implements Parcelable
{

    private int dt;
    private Main main;
    private java.util.List<Weather> weather = null;
    private Clouds clouds;
    private Wind wind;
    private Rain rain;
    private Sys_ sys;
    private String dtTxt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public List(Parcel in)
    {
            dt = ((int) in.readValue((int.class.getClassLoader())));
            main = ((Main) in.readValue((Main.class.getClassLoader())));
            weather = in.readArrayList((exp.weather.network.ForecastPOJO.Weather.class.getClassLoader()));
            clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
            wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
            rain = ((Rain) in.readValue((Rain.class.getClassLoader())));
            sys = ((Sys_) in.readValue((Sys_.class.getClassLoader())));
            dtTxt = ((String) in.readValue((String.class.getClassLoader())));
            additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }


    public final static Creator<List> CREATOR = new Creator<List>() {


        @SuppressWarnings({
            "unchecked"
        })
        public List createFromParcel(Parcel in) {
//            List instance = new List();
//            instance.dt = ((int) in.readValue((int.class.getClassLoader())));
//            instance.main = ((Main) in.readValue((Main.class.getClassLoader())));
//            in.readList(instance.weather, (exp.weather.network.ForecastPOJO.Weather.class.getClassLoader()));
//            instance.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
//            instance.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
//            instance.rain = ((Rain) in.readValue((Rain.class.getClassLoader())));
//            instance.sys = ((Sys_) in.readValue((Sys_.class.getClassLoader())));
//            instance.dtTxt = ((String) in.readValue((String.class.getClassLoader())));
//            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
//            return instance;

            List list = new List(in);

            return list;
        }

        public List[] newArray(int size) {
            return (new List[size]);
        }

    }
    ;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Sys_ getSys() {
        return sys;
    }

    public void setSys(Sys_ sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(dt);
        dest.writeValue(main);
        dest.writeList(weather);
        dest.writeValue(clouds);
        dest.writeValue(wind);
        dest.writeValue(rain);
        dest.writeValue(sys);
        dest.writeValue(dtTxt);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
