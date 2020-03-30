package ir.ashkaran.mzq.objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class SimpleCommutingObject {
  @SerializedName("dateTime")
  @Expose
  private String dateTime;
  @SerializedName("type")
  @Expose
  private String type;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}