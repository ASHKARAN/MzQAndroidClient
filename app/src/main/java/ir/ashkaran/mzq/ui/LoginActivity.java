package ir.ashkaran.mzq.ui;

import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.ashkaran.mzq.R;
import ir.ashkaran.mzq.api.MzQAPI;
import ir.ashkaran.mzq.objects.UserModel;
import ir.ashkaran.mzq.utils.Application;
import ir.ashkaran.mzq.utils.Storage;
import ir.ashkaran.mzq.utils.app;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.token)    AppCompatEditText token;
    @BindView(R.id.url)    AppCompatEditText url;
    @BindView(R.id.progressParent)    RelativeLayout progressParent;

    String myUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        token.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH
                                || actionId == EditorInfo.IME_ACTION_DONE
                                || event.getAction() == KeyEvent.ACTION_DOWN
                                && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            login();
                            return true;
                        }
                        // Return true if you have consumed the action, else false.
                        return false;
                    }
                });


        token.setText(Storage.get("token"));
        url.setText(Storage.get("url" , app.main.URL));



    }

    @OnClick(R.id.login)
    public void onClick(View view) {
        login();
        app.closeKeyboard(token);




    }


    private void login() {


        if(!app.isValidUrl(url.getText().toString())) {
            app.t("آدرس سرور صحیح نمی باشد", app.ToastType.ERROR);
            return;
        }

          myUrl = url.getText().toString();
        if(!myUrl.endsWith("/"))
            myUrl+= "/";

        Storage.get().edit().putString("url" , myUrl).apply();

        HashMap<String , String> body = new HashMap<>();
        body.put("token" , token.getText().toString());
        Call<UserModel> call = Application.newRetrofit().create(MzQAPI.class).Login(body);
        showProgress("در حال بررسی توکن");
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {


                if(response.isSuccessful()) {
                    Storage.get().edit().putString("UserModel", new Gson().toJson(response.body())).apply();
                    Storage.get().edit().putString("token" , token.getText().toString()).apply();
                    Storage.get().edit().putString("url" , myUrl).apply();
                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    finish();
                    app.t( response.body().getFNamelName() + " " +  "خوش آمدید" , app.ToastType.SUCCESS);
                }
                else {
                   errorHandling(response.errorBody());
                }

                HideProgress();
            }
            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                app.t(getString(R.string.error), app.ToastType.ERROR );
                HideProgress();
            }
        });
    }
}
