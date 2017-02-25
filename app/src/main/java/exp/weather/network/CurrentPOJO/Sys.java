
package exp.weather.network.CurrentPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Sys implements Parcelable
{

    private int type;
    private int id;
    private double message;
    private String country;
    private int sunrise;
    private int sunset;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Sys> CREATOR = new Creator<Sys>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Sys createFromParcel(Parcel in) {
            Sys instance = new Sys();
            instance.type = ((int) in.readValue((int.class.getClassLoader())));
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.message = ((double) in.readValue((double.class.getClassLoader())));
            instance.country = ((String) in.readValue((String.class.getClassLoader())));
            instance.sunrise = ((int) in.readValue((int.class.getClassLoader())));
            instance.sunset = ((int) in.readValue((int.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Sys[] newArray(int size) {
            return (new Sys[size]);
        }

    }
    ;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public void setSunrise(int sunrise) {
        this.sunrise = sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public void setSunset(int sunset) {
        this.sunset = sunset;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(id);
        dest.writeValue(message);
        dest.writeValue(country);
        dest.writeValue(sunrise);
        dest.writeValue(sunset);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
