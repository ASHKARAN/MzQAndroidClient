package ir.ashkaran.mzq.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.ashkaran.mzq.R;
import ir.ashkaran.mzq.api.MzQAPI;
import ir.ashkaran.mzq.objects.CachedCommutingObject;
import ir.ashkaran.mzq.objects.CommutingObject;
import ir.ashkaran.mzq.objects.ErrorModel;
import ir.ashkaran.mzq.objects.MainResponseModel;
import ir.ashkaran.mzq.objects.PeopleObject;
import ir.ashkaran.mzq.objects.UserModel;
import ir.ashkaran.mzq.utils.Application;
import ir.ashkaran.mzq.utils.Cache;
import ir.ashkaran.mzq.utils.DBHelper;
import ir.ashkaran.mzq.utils.Storage;
import ir.ashkaran.mzq.utils.app;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;

public class UpdateActivity extends BaseActivity {
    DBHelper database;

    @BindView(R.id.log)     AppCompatTextView log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ButterKnife.bind(this);

        database = new DBHelper(this);

        List<CommutingObject> cached = ir.ashkaran.mzq.utils.Cache.ReadCachedCommuting();
        if(cached != null && cached.size() > 0 ){
            log.append("\n");
            log.append(cached.size() + " رکورد در دستگاه شما ذخیره شده است");
        }





    }

    @OnClick({R.id.update, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back: finish();return;
            case R.id.update:    uploadCachedCommuting();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
    private void getData() {

        showProgress("در حال دریافت اطلاعات اشخاص");
        UserModel userModel = new Gson().fromJson(Storage.get("UserModel") , UserModel.class);
       HashMap<String , String> body = new HashMap<>();
       body.put("token" , Storage.get("token"));
       body.put("city" , userModel.getCity());
        Call<List<PeopleObject>> call = Application.getRetrofit().create(MzQAPI.class).GetData(body);
        call.enqueue(new Callback<List<PeopleObject>>() {
                 @Override
                 public void onResponse(Call<List<PeopleObject>> call, Response<List<PeopleObject>> response) {
                     if(response.isSuccessful()) {
                         database.clearAllPeople();
                         database.insertPeople(response.body());
                         showProgress("اطلاعات با موفقیت دریافت شد");
                         app.t( "اطلاعات با موفقیت دریافت شد", app.ToastType.SUCCESS);
                         finish();
                     }
                     else {
                         UpdateActivity.super.errorHandling(response.errorBody());
                         HideProgress();
                     }
                 }
                 @Override
                 public void onFailure(Call<List<PeopleObject>> call, Throwable t) {
                     app.t(getString(R.string.error), app.ToastType.ERROR );
                     HideProgress();
                 }
        });
    }

    private void uploadCachedCommuting() {

        List<CommutingObject> cached = ir.ashkaran.mzq.utils.Cache.ReadCachedCommuting();
        if(cached == null || cached.size() ==0 ){
            getData();
            return;
        }
        CachedCommutingObject object = new CachedCommutingObject(Storage.get("token") , cached);
        Call<MainResponseModel> call = Application.getRetrofit().create(MzQAPI.class).InsertCommuting(object);

        showProgress("در حال ارسال اطلاعات ذخیره شده در دستگاه");
        call.enqueue(new Callback<MainResponseModel>() {
                 @Override
                 public void onResponse(Call<MainResponseModel> call, Response<MainResponseModel> response) {
                     if(response.isSuccessful()) {
                         Cache.ClearCache();
                         showProgress("اطلاعات از دستگاه شما پاک شد");
                         log.setText("این عملیات ممکن است کمی زمان بر باشد\nلطفا شکیبا باشید");
                     }
                     else {
                         UpdateActivity.super.errorHandling(response.errorBody());
                         showProgress("مشکلی در ارسال اطلاعات به وجود آمده است");
                     }
                     getData();
                 }
                 @Override
                 public void onFailure(Call<MainResponseModel> call, Throwable t) {
                     getData();
                     showProgress("مشکلی در ارسال اطلاعات به وجود آمده است");
                     app.t(getString(R.string.error), app.ToastType.ERROR );
                 }
        });
    }




    



}
