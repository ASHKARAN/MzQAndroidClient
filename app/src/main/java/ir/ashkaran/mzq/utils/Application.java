package ir.ashkaran.mzq.utils;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Application extends android.app.Application {


    private static Context context;
    private static  Retrofit retrofit ;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;


        Logger.addLogAdapter(new AndroidLogAdapter());


       setFont();





        Toasty.Config.getInstance()
                /*.setErrorColor(@ColorInt int errorColor) // optional
                .setInfoColor(@ColorInt int infoColor) // optional
                .setSuccessColor(@ColorInt int successColor) // optional
                .setWarningColor(@ColorInt int warningColor) // optional
                .setTextColor(@ColorInt int textColor) // optional
                .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)*/

                .setTextSize(12) // optional
                .apply(); // required
        String locale = Storage.get().getString("locale", "fa");//ps
        LocaleHelper.setLocale(this, locale);

    }



    public static void setFont() {

        String lightfontName = "fonts/Vazir.ttf";
        String boldfontName  = "fonts/Vazir-Bold.ttf";

        FontsOverride.setDefaultFont(context, "SERIF", boldfontName);
        FontsOverride.setDefaultFont(context, "MONOSPACE"   , lightfontName);


    }



    public static Context getContext() {
        return context;
    }

    public static Retrofit getRetrofit() {

        if(retrofit == null ) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            final Headers headers = new Headers() ;
            httpClient.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .build();
                return chain.proceed(request);
            });

            httpClient .readTimeout(10, TimeUnit.MINUTES)
                       .connectTimeout(10, TimeUnit.MINUTES);

            httpClient.addInterceptor(logging);  // <-- this is the important line!

          retrofit =   new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            //.addConverterFactory(ScalarsConverterFactory.create())
                            .baseUrl(Storage.get("url"))
                            .client(httpClient.build())
                            .build();
        }
        return  retrofit;
    }
    public static Retrofit newRetrofit() {



            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            final Headers headers = new Headers() ;
            httpClient.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .build();
                return chain.proceed(request);
            });

            httpClient .readTimeout(10, TimeUnit.MINUTES)
                       .connectTimeout(10, TimeUnit.MINUTES);

            httpClient.addInterceptor(logging);  // <-- this is the important line!

          retrofit =   new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            //.addConverterFactory(ScalarsConverterFactory.create())
                            .baseUrl(Storage.get("url"))
                            .client(httpClient.build())
                            .build();

        return  retrofit;
    }





}
