package ir.ashkaran.mzq.objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class CommutingObject{
  @SerializedName("dateTime")
  @Expose
  private String dateTime;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("latitude")
  @Expose
  private String latitude;
  @SerializedName("ID")
  @Expose
  private String ID;
  @SerializedName("type")
  @Expose
  private String type;
  @SerializedName("username")
  @Expose
  private String username;
  @SerializedName("longitude")
  @Expose
  private String longitude;
  public void setDateTime(String dateTime){
   this.dateTime=dateTime;
  }
  public String getDateTime(){
   return dateTime;
  }
  public void setCity(String city){
   this.city=city;
  }
  public String getCity(){
   return city;
  }
  public void setLatitude(String latitude){
   this.latitude=latitude;
  }
  public String getLatitude(){
   return latitude;
  }
  public void setID(String ID){
   this.ID=ID;
  }
  public String getID(){
   return ID;
  }
  public void setType(String type){
   this.type=type;
  }
  public String getType(){
   return type;
  }
  public void setUsername(String username){
   this.username=username;
  }
  public String getUsername(){
   return username;
  }
  public void setLongitude(String longitude){
   this.longitude=longitude;
  }
  public String getLongitude(){
   return longitude;
  }
}