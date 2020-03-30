package ir.ashkaran.mzq.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.ashkaran.mzq.BuildConfig;
import ir.ashkaran.mzq.R;
import ir.ashkaran.mzq.utils.Storage;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.login)
    AppCompatTextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String versionName = BuildConfig.VERSION_NAME;
        login.append("\n");
        login.append("نسخه ");
        login.append(versionName);
        new Handler().postDelayed(() -> {

            startActivity(new Intent(SplashActivity.this ,
                    Storage.get("token").equals("")?
                    LoginActivity.class:
                    MainActivity.class
            ));
            finish();
        },1000);
    }
}
