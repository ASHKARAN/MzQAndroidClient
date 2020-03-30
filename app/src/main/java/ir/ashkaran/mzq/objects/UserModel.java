package ir.ashkaran.mzq.objects;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class UserModel{
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("admin")
  @Expose
  private Integer admin;
  @SerializedName("username")
  @Expose
  private String username;
  @SerializedName("token")
  @Expose
  private String token;
  @SerializedName("fName")
  @Expose
  private String fName;
  @SerializedName("lName")
  @Expose
  private String lName;
  public void setCity(String city){
   this.city=city;
  }
  public String getCity(){
   return city;
  }
  public void setAdmin(Integer admin){
   this.admin=admin;
  }
  public Integer getAdmin(){
   return admin;
  }
  public void setUsername(String username){
   this.username=username;
  }
  public String getUsername(){
   return username;
  }
  public void setToken(String token){
   this.token=token;
  }
  public String getToken(){
   return token;
  }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }



    public String getFNamelName() {
        return fName + " " + lName;
    }
}