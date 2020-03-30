package ir.ashkaran.mzq.api;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import ir.ashkaran.mzq.objects.CachedCommutingObject;
import ir.ashkaran.mzq.objects.CommutingObject;
import ir.ashkaran.mzq.objects.MainResponseModel;
import ir.ashkaran.mzq.objects.PeopleObject;
import ir.ashkaran.mzq.objects.UserModel;
import ir.ashkaran.mzq.utils.app;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;

public interface MzQAPI {



    @POST(app.main.Version+"/Data/Login")
    Call<UserModel> Login(@Body  HashMap<String , String> body);


    @POST(app.main.Version+"/Commuting/Insert")
    Call<MainResponseModel> InsertCommuting(@Body CachedCommutingObject object);


    @POST(app.main.Version+"/Commuting/Get")
    Call<List<CommutingObject>> GetCommuting(@Body HashMap<String , String> body);


//    @POST(app.main.Version+"/Data/Get")
//    Call<List<PeopleObject>> GetData(@Body HashMap<String , String> body);


    @POST(app.main.Version+"/Data/Get")
    @Streaming
    public Call<List<PeopleObject>> GetData(@Body HashMap<String , String> body);

}
