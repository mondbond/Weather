
package exp.weather.network.ForecastPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class ForecastWeather implements Parcelable
{

    private City city;
    private String cod;
    private double message;
    private int cnt;
    private java.util.List<exp.weather.network.ForecastPOJO.List> list = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public ForecastWeather()
    {

    }

    public ForecastWeather(ForecastWeather original)
    {
        city = original.getCity();
        cod = original.getCod();
        message = original.getMessage();
        cnt = original.getCnt();
        list = original.getList();
        additionalProperties = original.getAdditionalProperties();
    }

    public final static Creator<ForecastWeather> CREATOR = new Creator<ForecastWeather>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ForecastWeather createFromParcel(Parcel in) {
            ForecastWeather instance = new ForecastWeather();
            instance.city = ((City) in.readValue((City.class.getClassLoader())));
            instance.cod = ((String) in.readValue((String.class.getClassLoader())));
            instance.message = ((double) in.readValue((double.class.getClassLoader())));
            instance.cnt = ((int) in.readValue((int.class.getClassLoader())));
            instance.list = in.readArrayList((exp.weather.network.ForecastPOJO.List.class.getClassLoader()));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public ForecastWeather[] newArray(int size) {
            return (new ForecastWeather[size]);
        }

    }
    ;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<exp.weather.network.ForecastPOJO.List> getList() {
        return list;
    }

    public void setList(java.util.List<exp.weather.network.ForecastPOJO.List> list) {
        this.list = list;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(city);
        dest.writeValue(cod);
        dest.writeValue(message);
        dest.writeValue(cnt);
        dest.writeList(list);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
