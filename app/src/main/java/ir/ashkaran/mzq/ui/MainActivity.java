package ir.ashkaran.mzq.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.ashkaran.mzq.R;
import ir.ashkaran.mzq.adapter.PeopleAdapter;
import ir.ashkaran.mzq.objects.PeopleObject;
import ir.ashkaran.mzq.utils.DBHelper;
import ir.ashkaran.mzq.utils.Storage;
import ir.ashkaran.mzq.utils.app;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.searchInput)
    AppCompatEditText searchInput;
    @BindView(R.id.searchHelp)
    LinearLayout searchHelp;
    @BindView(R.id.searchNoAccess)
    LinearLayout searchNoAccess;
    @BindView(R.id.search)
    AppCompatImageView search;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    DBHelper database;
    List<PeopleObject> peopleObjectList = new ArrayList<>();
    PeopleAdapter peopleAdapter;
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.e("1");
        super.onCreate(savedInstanceState);

        Logger.e("2");
        setContentView(R.layout.activity_main);

        Logger.e("3");
        ButterKnife.bind(this);

        Logger.e("4");
        database = new DBHelper(this);
        intiPanel();

        if(database.numberOfRows() == 0 )
            startActivity(new Intent(this, UpdateActivity.class));

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    peopleObjectList.clear();
                    searchHelp.setVisibility(View.VISIBLE);
                    searchNoAccess.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    search.setColorFilter(getResources().getColor(R.color.grayLevel1));
                } else {
                    searchHelp.setVisibility(View.GONE);
                    searchNoAccess.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    if(s.length()>2)
                    search.setColorFilter(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchInput.setOnEditorActionListener(
                (v, actionId, event) -> {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                            || actionId == EditorInfo.IME_ACTION_DONE
                            || event.getAction() == KeyEvent.ACTION_DOWN
                            && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        performSearch(searchInput.getText().toString());
                        return true;
                    }

                    return false;
                });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        peopleAdapter = new PeopleAdapter(peopleObjectList, this);
        recyclerView.setAdapter(peopleAdapter);


        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);



        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            }
        }
        else {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                    100, mLocationListener);
        }



                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults){
        switch (requestCode){
            case 1: {
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                       app.t(  "دسترسی دریافت شد", app.ToastType.SUCCESS);
                        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000,
                                100, mLocationListener);
                    }
                }else{
                    app.t(  "برای کارایی صحیح برنامه به دسترسی نیاز است", app.ToastType.ERROR);
                }
                return;
            }
        }
    }


    @OnClick({R.id.menu, R.id.updateFab, R.id.search, R.id.clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.menu: {
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            }
            case R.id.updateFab: {
                startActivity(new Intent(MainActivity.this, UpdateActivity.class));
                break;
            }
            case R.id.search: {
                performSearch(searchInput.getText().toString());
                break;
            }
            case R.id.clear: {
                searchInput.setText("");
                break;
            }
        }
    }

    private void intiPanel() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.about: {
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        break;
                    }
                    case R.id.contact: {
                        startActivity(new Intent(MainActivity.this, ContactActivity.class));
                        break;
                    }
                    case R.id.update: {
                        startActivity(new Intent(MainActivity.this, UpdateActivity.class));
                        break;
                    }
                    case R.id.logout: {
                        Storage.get().edit().remove("token").apply();
                        startActivity(new Intent(MainActivity.this , LoginActivity.class));
                        finish();
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void performSearch(String query) {


        search.setColorFilter(getResources().getColor(R.color.grayLevel1));
        app.closeKeyboard(searchInput);
        peopleObjectList.clear();
        Cursor people ;
        if(query.length()==0)
            people = database.getAllPeople();
        else  people = database.searchPeople(query);

        if(people != null && people.getCount()>0) {
            people.moveToFirst();
            while (!people.isAfterLast()) {
                PeopleObject object = new PeopleObject();
                object.setID(people.getString(people.getColumnIndex("ID")));
                object.setfName(people.getString(people.getColumnIndex("fName")));
                object.setlName(people.getString(people.getColumnIndex("lName")));
                object.setCity(people.getString(people.getColumnIndex("city")));
                object.setPlaque(people.getString(people.getColumnIndex("plaque")));
                object.setOffice(people.getString(people.getColumnIndex("office")));
                object.setFather(people.getString(people.getColumnIndex("father")));
                object.setComments(people.getString(people.getColumnIndex("comments")));
                peopleObjectList.add(object);
                people.moveToNext();
            }


            searchHelp.setVisibility(View.GONE);
            searchNoAccess.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            search.setColorFilter(getResources().getColor(R.color.grayLevel1));



        }
        else {
            searchNoAccess.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        Logger.e("i found " + peopleObjectList.size());
        peopleAdapter.notifyDataSetChanged();
    }


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
           Storage.get().edit().putString("latitude" , location.getLatitude() + "").apply();
           Storage.get().edit().putString("longitude" , location.getLongitude() + "").apply();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


}
