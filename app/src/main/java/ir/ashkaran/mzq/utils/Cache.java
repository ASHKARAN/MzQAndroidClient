package ir.ashkaran.mzq.utils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ir.ashkaran.mzq.objects.CommutingObject;

public class Cache {

    /**
     * here we store the offline data
     * @param commutingObject
     */
    public static void CacheCommuting(CommutingObject commutingObject) {
        CommutingObject []  cache = new Gson().fromJson(Storage.get().getString("Cache", "") , CommutingObject[].class);
        List<CommutingObject> list =  new ArrayList<>() ;
        if(cache != null) {
            Collections.addAll(list, cache);
        }
        list.add(commutingObject);
        Storage.get().edit().putString("Cache", new Gson().toJson(list)).apply();
    }

    /**
     * here we restore the offline data
     */
    public static List<CommutingObject> ReadCachedCommuting() {
        CommutingObject []  cache = new Gson().fromJson(Storage.get().getString("Cache", "") , CommutingObject[].class);
        if(cache == null) return null;
        return Arrays.asList(cache);
    }

    /**
     * after uploading the cached data, we will remove them all
     */
    public static void ClearCache() {
        Storage.get().edit().remove("Cache").apply();
    }



}
