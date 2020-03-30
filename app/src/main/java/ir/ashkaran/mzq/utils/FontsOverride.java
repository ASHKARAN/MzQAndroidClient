package ir.ashkaran.mzq.utils;

/**
 * Created by ali on 8/22/16.
 */

import android.content.Context;
import android.graphics.Typeface;

import java.lang.reflect.Field;

public final class FontsOverride {

    public static Typeface fontlight;
    public static Typeface fontbold ;
    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        if(staticTypefaceFieldName.equals("MONOSPACE"))
            fontlight = regular ;
        else fontbold = regular ;

        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}