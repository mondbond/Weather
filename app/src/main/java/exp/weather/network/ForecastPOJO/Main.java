
package exp.weather.network.ForecastPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Main implements Parcelable
{

    private double temp;
    private double tempMin;
    private double tempMax;
    private double pressure;
    private double seaLevel;
    private double grndLevel;
    private int humidity;
    private int tempKf;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Main> CREATOR = new Creator<Main>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Main createFromParcel(Parcel in) {
            Main instance = new Main();
            instance.temp = ((double) in.readValue((double.class.getClassLoader())));
            instance.tempMin = ((double) in.readValue((double.class.getClassLoader())));
            instance.tempMax = ((double) in.readValue((double.class.getClassLoader())));
            instance.pressure = ((double) in.readValue((double.class.getClassLoader())));
            instance.seaLevel = ((double) in.readValue((double.class.getClassLoader())));
            instance.grndLevel = ((double) in.readValue((double.class.getClassLoader())));
            instance.humidity = ((int) in.readValue((int.class.getClassLoader())));
            instance.tempKf = ((int) in.readValue((int.class.getClassLoader())));
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

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(double grndLevel) {
        this.grndLevel = grndLevel;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTempKf() {
        return tempKf;
    }

    public void setTempKf(int tempKf) {
        this.tempKf = tempKf;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(temp);
        dest.writeValue(tempMin);
        dest.writeValue(tempMax);
        dest.writeValue(pressure);
        dest.writeValue(seaLevel);
        dest.writeValue(grndLevel);
        dest.writeValue(humidity);
        dest.writeValue(tempKf);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
