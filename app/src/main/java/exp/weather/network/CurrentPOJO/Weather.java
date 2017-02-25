
package exp.weather.network.CurrentPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Weather implements Parcelable
{

    private int id;
    private String main;
    private String description;
    private String icon;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Weather> CREATOR = new Creator<Weather>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Weather createFromParcel(Parcel in) {
            Weather instance = new Weather();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.main = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.icon = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Weather[] newArray(int size) {
            return (new Weather[size]);
        }

    }
    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(main);
        dest.writeValue(description);
        dest.writeValue(icon);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
