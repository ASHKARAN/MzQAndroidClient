package ir.ashkaran.mzq.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.core.content.ContextCompat;
import es.dmoral.toasty.Toasty;
import ir.ashkaran.mzq.R;

public class app {


    /** Im here :
     * StudentGheybatSelectMahFragment
     */
    public enum ToastType {
        ERROR, NORMAL, SUCCESS, INFO, WARNING
    }

    public final static boolean DEBUG = true;
    public final static boolean CAFEBAZAR = true;

    public static class main {
        public static final int APP = 1;
        public static final String TAG = "MzQ";
        public static final String DATABASE_NAME = "MzQ";
        public static final int VERSION = 1;
        public static final String URL = "";
        public static final String Version = "v1";
        public static final String CLIENT = "android";

    }


    public static void t(String message, ToastType type) {

/*        StackTraceElement[] trace = new Exception().getStackTrace();
        for(StackTraceElement e : trace){
            l( "1: " + e.toString());
        }*/

        switch (type) {

            case NORMAL: {
                Toasty.normal(Application.getContext(), message, Toast.LENGTH_LONG).show();

                break;
            }
            case SUCCESS: {
                Toasty.success(Application.getContext(), message, Toast.LENGTH_LONG, true).show();

                break;
            }
            case INFO: {
                Toasty.info(Application.getContext(), message, Toast.LENGTH_LONG, true).show();

                break;
            }
            case WARNING: {
                Toasty.warning(Application.getContext(), message, Toast.LENGTH_LONG, true).show();

                break;
            }
            case ERROR: {
                Toasty.error(Application.getContext(), message, Toast.LENGTH_LONG, true).show();
                break;
            }


        }

    }

    public static void l(String message) {
        Log.e(main.TAG , message);

    }


    public static boolean isConnect() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)  Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    public static void closeKeyboard(View view){

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) Application.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }




    public static String GenerateRandomString() {
        byte[] array = new byte[30]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));

    }

    public static boolean isProbablyArabic(String s) {
        for (int i = 0; i < s.length();) {
            int c = s.codePointAt(i);
            if (c >= 0x0600 && c <= 0x06E0)
                return true;
            i += Character.charCount(c);
        }
        return false;
    }




    public static void  changeStatusBarColor(Activity activity , Boolean white) {

  /*      View decor = activity.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity,
                    R.color.colorStatusBarFooter
            )); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,
                   R.color.colorStatusBarHeader
            )); //status bar or the time bar at the top
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
*/


        View decor = activity.getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity,
                    white? R.color.white:R.color.white
            )); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, R.color.greenLevel2
            )); //status bar or the time bar at the top
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            decor.setSystemUiVisibility(white?View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR:0);



    }



    public static void changeLanguage(String language , Context context) {
        //String locale = Storage.get().getString("locale", "fa_AF");//ps_AF
        Storage.get().edit().putString("locale", language).apply();
//        Locale locale = new Locale(language);
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        activity.getResources().updateConfiguration(config,activity.getResources().getDisplayMetrics());

        Logger.e("changing language to " + language);

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 19) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            res.updateConfiguration(config, res.getDisplayMetrics());
        }



    }
    public static boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        return m.matches();
    }

    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";

    public static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for(int i=0;i<number.length();i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }

}



