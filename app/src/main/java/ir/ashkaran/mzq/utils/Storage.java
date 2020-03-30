package ir.ashkaran.mzq.utils;

import android.content.Context;
import android.content.SharedPreferences;



public class Storage {

    private static SharedPreferences sharedPreferences;


    public static SharedPreferences get() {

        if(sharedPreferences == null)
            sharedPreferences =     Application.getContext().getSharedPreferences("MzQPreferences", Context.MODE_PRIVATE);

        return  sharedPreferences;
    }

    public static String get(String key) {
        return get().getString(key, "");
    }
    public static String get(String key, String defValue) {
        return get().getString(key, defValue);
    }




}
