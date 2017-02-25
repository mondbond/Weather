
package exp.weather.network.CurrentPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Coord implements Parcelable
{

    private double lon;
    private double lat;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Coord> CREATOR = new Creator<Coord>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Coord createFromParcel(Parcel in) {
            Coord instance = new Coord();
            instance.lon = ((double) in.readValue((double.class.getClassLoader())));
            instance.lat = ((double) in.readValue((double.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Coord[] newArray(int size) {
            return (new Coord[size]);
        }

    }
    ;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lon);
        dest.writeValue(lat);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
