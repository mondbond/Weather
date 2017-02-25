
package exp.weather.network.ForecastPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Clouds implements Parcelable
{

    private int all;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Clouds> CREATOR = new Creator<Clouds>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Clouds createFromParcel(Parcel in) {
            Clouds instance = new Clouds();
            instance.all = ((int) in.readValue((int.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Clouds[] newArray(int size) {
            return (new Clouds[size]);
        }

    }
    ;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(all);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
