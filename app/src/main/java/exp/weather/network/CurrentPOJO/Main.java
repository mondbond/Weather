
package exp.weather.network.CurrentPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Main implements Parcelable
{

    private double temp;
    private double pressure;
    private int humidity;
    private int tempMin;
    private int tempMax;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Main> CREATOR = new Creator<Main>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Main createFromParcel(Parcel in) {
            Main instance = new Main();
            instance.temp = ((double) in.readValue((double.class.getClassLoader())));
            instance.pressure = ((int) in.readValue((int.class.getClassLoader())));
            instance.humidity = ((int) in.readValue((int.class.getClassLoader())));
            instance.tempMin = ((int) in.readValue((int.class.getClassLoader())));
            instance.tempMax = ((int) in.readValue((int.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Main[] newArray(int size) {
            return (new Main[size]);
        }

    }
    ;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(temp);
        dest.writeValue(pressure);
        dest.writeValue(humidity);
        dest.writeValue(tempMin);
        dest.writeValue(tempMax);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
