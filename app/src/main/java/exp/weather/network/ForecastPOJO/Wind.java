
package exp.weather.network.ForecastPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Wind implements Parcelable
{

    private double speed;
    private double deg;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Wind> CREATOR = new Creator<Wind>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Wind createFromParcel(Parcel in) {
            Wind instance = new Wind();
            instance.speed = ((double) in.readValue((double.class.getClassLoader())));
            instance.deg = ((double) in.readValue((double.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Wind[] newArray(int size) {
            return (new Wind[size]);
        }

    }
    ;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(speed);
        dest.writeValue(deg);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
