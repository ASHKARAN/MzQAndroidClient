package ir.ashkaran.mzq.ui;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;

import ir.ashkaran.mzq.R;
import ir.ashkaran.mzq.objects.ErrorModel;
import ir.ashkaran.mzq.utils.app;
import okhttp3.ResponseBody;

public class BaseActivity extends AppCompatActivity {





    public void errorHandling(ResponseBody err){
        try {
            ErrorModel errorModel = new Gson().fromJson(err.string() , ErrorModel.class);
            app.t(errorModel.getMessage(), app.ToastType.ERROR );
        }
        catch(IOException | JsonParseException ex) {
            app.t(getString(R.string.error), app.ToastType.ERROR );
        }
    }

    public void showProgress(String msg) {
        findViewById(R.id.progressParent).setVisibility(View.VISIBLE);
        ((AppCompatTextView)findViewById(R.id.progressText)).setText(msg);

    }

    public void HideProgress() {
        findViewById(R.id.progressParent).setVisibility(View.GONE);
    }

}
