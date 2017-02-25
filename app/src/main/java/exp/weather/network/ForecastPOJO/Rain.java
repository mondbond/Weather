
package exp.weather.network.ForecastPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Rain implements Parcelable
{

    private double _3h;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Rain> CREATOR = new Creator<Rain>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Rain createFromParcel(Parcel in) {
            Rain instance = new Rain();
            instance._3h = ((double) in.readValue((double.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Rain[] newArray(int size) {
            return (new Rain[size]);
        }

    }
    ;

    public double get3h() {
        return _3h;
    }

    public void set3h(double _3h) {
        this._3h = _3h;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_3h);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
