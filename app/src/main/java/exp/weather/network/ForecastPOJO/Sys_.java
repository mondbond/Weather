
package exp.weather.network.ForecastPOJO;

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;

public class Sys_ implements Parcelable
{

    private String pod;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Creator<Sys_> CREATOR = new Creator<Sys_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Sys_ createFromParcel(Parcel in) {
            Sys_ instance = new Sys_();
            instance.pod = ((String) in.readValue((String.class.getClassLoader())));
            instance.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
            return instance;
        }

        public Sys_[] newArray(int size) {
            return (new Sys_[size]);
        }

    }
    ;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pod);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return  0;
    }

}
