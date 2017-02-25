
package exp.weather.network.CurrentPOJO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class CurrentWeather implements Parcelable
{

    private Coord coord;
    private List<Weather> weather = null;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int id;
    private String name;
    private int cod;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<CurrentWeather> CREATOR = new Creator<CurrentWeather>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentWeather createFromParcel(Parcel in) {
            CurrentWeather instance = new CurrentWeather();
            instance.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
            in.readList(instance.weather, (exp.weather.network.CurrentPOJO.Weather.class.getClassLoader()));
            instance.base = ((String) in.readValue((String.class.getClassLoader())));
            instance.main = ((Main) in.readValue((Main.class.getClassLoader())));
            instance.visibility = ((int) in.readValue((int.class.getClassLoader())));
            instance.wind = ((Wind) in.readValue((Wind.class.getClassLoader())));
            instance.clouds = ((Clouds) in.readValue((Clouds.class.getClassLoader())));
            instance.dt = ((int) in.readValue((int.class.getClassLoader())));
            instance.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.cod = ((int) in.readValue((int.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public CurrentWeather[] newArray(int size) {
            return (new CurrentWeather[size]);
        }

    }
    ;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(coord);
        dest.writeList(weather);
        dest.writeValue(base);
        dest.writeValue(main);
        dest.writeValue(visibility);
        dest.writeValue(wind);
        dest.writeValue(clouds);
        dest.writeValue(dt);
        dest.writeValue(sys);
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(cod);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
