
package exp.weather.network.ForecastPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Sys implements Parcelable
{

    private int population;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Sys> CREATOR = new Creator<Sys>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Sys createFromParcel(Parcel in) {
            Sys instance = new Sys();
            instance.population = ((int) in.readValue((int.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Sys[] newArray(int size) {
            return (new Sys[size]);
        }

    }
    ;

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(population);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
