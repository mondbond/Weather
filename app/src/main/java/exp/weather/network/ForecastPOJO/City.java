
package exp.weather.network.ForecastPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable
{

    private int id;
    private String name;
    private Coord coord;
    private String country;
    private int population;
    private Sys sys;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<City> CREATOR = new Creator<City>() {


        @SuppressWarnings({
            "unchecked"
        })
        public City createFromParcel(Parcel in) {
            City instance = new City();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.coord = ((Coord) in.readValue((Coord.class.getClassLoader())));
            instance.country = ((String) in.readValue((String.class.getClassLoader())));
            instance.population = ((int) in.readValue((int.class.getClassLoader())));
            instance.sys = ((Sys) in.readValue((Sys.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public City[] newArray(int size) {
            return (new City[size]);
        }

    }
    ;

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

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(coord);
        dest.writeValue(country);
        dest.writeValue(population);
        dest.writeValue(sys);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
