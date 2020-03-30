package ir.ashkaran.mzq.objects;
import android.database.Cursor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Awesome Pojo Generator
 * */
public class PeopleObject implements Serializable {
  @SerializedName("lName")
  @Expose
  private String lName;
  @SerializedName("plaque")
  @Expose
  private String plaque;
  @SerializedName("fName")
  @Expose
  private String fName;
  @SerializedName("comments")
  @Expose
  private String comments;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("father")
  @Expose
  private String father;
  @SerializedName("ID")
  @Expose
  private String ID;
  @SerializedName("office")
  @Expose
  private String office;


  public void setLName(String lName){
   this.lName=lName;
  }
  public String getLName(){
   return lName;
  }
  public void setPlaque(String plaque){
   this.plaque=plaque;
  }
  public String getPlaque(){
   return plaque;
  }
  public void setFName(String fName){
   this.fName=fName;
  }
  public String getFName(){
   return fName;
  }
  public void setComments(String comments){
   this.comments=comments;
  }
  public String getComments(){
   return comments;
  }
  public void setCity(String city){
   this.city=city;
  }
  public String getCity(){
   return city;
  }
  public void setFather(String father){
   this.father=father;
  }
  public String getFather(){
   return father;
  }
  public void setID(String ID){
   this.ID=ID;
  }
  public String getID(){
   return ID;
  }
  public void setOffice(String office){
   this.office=office;
  }
  public String getOffice(){
   return office;
  }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }



    public String getFNamelName() {
        return fName + " " + lName;
    }
}