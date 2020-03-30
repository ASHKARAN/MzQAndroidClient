package ir.ashkaran.mzq.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.ashkaran.mzq.R;
import ir.ashkaran.mzq.adapter.CommutingAdapter;
import ir.ashkaran.mzq.api.MzQAPI;
import ir.ashkaran.mzq.objects.CommutingObject;
import ir.ashkaran.mzq.objects.PeopleObject;
import ir.ashkaran.mzq.objects.UserModel;
import ir.ashkaran.mzq.utils.Application;
import ir.ashkaran.mzq.utils.Cache;
import ir.ashkaran.mzq.utils.Storage;
import ir.ashkaran.mzq.utils.app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class CommutingActivity extends BaseActivity {

    PeopleObject peopleObject;


    @BindView(R.id.title)    AppCompatTextView  title;
    @BindView(R.id.fNamelName)    AppCompatTextView fNamelName ;
    @BindView(R.id.ID)    AppCompatTextView  ID;
    @BindView(R.id.father)    AppCompatTextView  father;
    @BindView(R.id.office)    AppCompatTextView office ;
    @BindView(R.id.comments)    AppCompatTextView  comments;
    @BindView(R.id.city)    AppCompatTextView  city;
    @BindView(R.id.plaque)    AppCompatTextView plaque ;
    @BindView(R.id.recyclerView)    RecyclerView recyclerView ;
    @BindView(R.id.progressBarInline)    ProgressBar progressBarInline ;

    CommutingAdapter commutingAdapter ;
    List<CommutingObject> commutingObjectList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commuting);
        ButterKnife.bind(this);
        peopleObject = (PeopleObject)getIntent().getSerializableExtra("PeopleObject");

        assert peopleObject != null;
        title.setText(peopleObject.getFNamelName());
        fNamelName.setText(peopleObject.getFNamelName());
        ID.setText(peopleObject.getID());
        father.setText("فرزند " + peopleObject.getFather());
        office.setText(peopleObject.getOffice());
        comments.setText(peopleObject.getComments());
        city.setText(peopleObject.getCity());
        plaque.setText(peopleObject.getPlaque());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        commutingAdapter = new CommutingAdapter(commutingObjectList, this);
        recyclerView.setAdapter(commutingAdapter);

        fetchData();

    }


    @OnClick({R.id.in, R.id.out, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.in: {
                CacheCommuting("in");
                return;
            }
            case R.id.out: {
                CacheCommuting("out");
                return;
            }
            case R.id.back: {
               finish();
                return;
            }
        }
    }

    private void fetchData() {

        progressBarInline.setVisibility(View.GONE);
        HashMap<String , String> body = new HashMap<>();
        body.put("token" , Storage.get("token"));
        body.put("ID" , peopleObject.getID());
        Call<List<CommutingObject>> call = Application.getRetrofit().create(MzQAPI.class).GetCommuting(body);
        call.enqueue(new Callback<List<CommutingObject>>() {
            @Override
            public void onResponse(Call<List<CommutingObject>> call, Response<List<CommutingObject>> response) {
                if(response.isSuccessful()) {
                    commutingObjectList.addAll(response.body());
                    commutingAdapter.notifyDataSetChanged();

                    if(response.body().size() == 0) {
                        //app.t("ترددی یافت نشد" , app.ToastType.INFO);
                    }
                }
                else {
                    CommutingActivity.super.errorHandling(response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<List<CommutingObject>> call, Throwable t) {
                app.t(getString(R.string.error), app.ToastType.ERROR );
            }
        });
    }


    private void CacheCommuting(String type) {

        PersianDate pdate = new PersianDate();
        PersianDateFormat pdformater = new PersianDateFormat("Y-m-d H:i:s");
        String date = pdformater.format(pdate);

        Logger.e(date);

        UserModel userModel = new Gson().fromJson(Storage.get("UserModel") , UserModel.class);

        CommutingObject commutingObject = new CommutingObject();
        commutingObject.setCity(userModel.getCity());
        commutingObject.setID(peopleObject.getID());
        commutingObject.setType(type);
        commutingObject.setUsername(userModel.getUsername());
        commutingObject.setLatitude(Storage.get("latitude"));
        commutingObject.setLongitude(Storage.get("longitude"));
        commutingObject.setDateTime(date);

        Cache.CacheCommuting(commutingObject);
        finish();
    }
}
