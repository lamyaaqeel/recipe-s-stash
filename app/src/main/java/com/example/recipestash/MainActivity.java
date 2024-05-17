package com.example.recipestash;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


public class MainActivity extends AppCompatActivity {

    Button sug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        sug = findViewById(R.id.SUG);
        TextView textView = findViewById(R.id.ADD);
        TextView view = findViewById(R.id.VIEW);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecipesActivity.class));
            }
        });

        registerForContextMenu(sug);
        sug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop(v);
            }
        });
        TextView t = findViewById(R.id.LIST);
        TextView ti = findViewById(R.id.TIMER);
        ti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, timer_activity.class);
                startActivity(intent);
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void pop(View v) {
        PopupMenu p = new PopupMenu(this, v);
        p.getMenuInflater().inflate(R.menu.menu, p.getMenu());
        p.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.meals) {
                    Intent intent = new Intent(MainActivity.this, mealActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "here you go", Toast.LENGTH_LONG).show();

                } else if (item.getItemId() == R.id.snak) {
                    Intent i = new Intent(MainActivity.this, snackActivity.class);
                    startActivity(i);
                    Toast.makeText(MainActivity.this, "here you go", Toast.LENGTH_LONG).show();

                } else if (item.getItemId() == R.id.dessert) {
                    Intent ii = new Intent(MainActivity.this, desActivity.class);
                    startActivity(ii);
                    Toast.makeText(MainActivity.this, "here you go", Toast.LENGTH_LONG).show();

                } else if (item.getItemId() == R.id.loc) {
                    getNearestRestraint();
                }
                return true;
            }
        });
        p.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 12:
                getNearestRestraint();
                break;
        }

    }

    LocationManager locationManager;
    LocationRequest locationRequest;
    public void getNearestRestraint() {
        if(locationManager==null){
            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        }
        if(locationRequest==null){
            locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(5000);
            locationRequest.setFastestInterval(2000);
        }


        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    12);

        } else {

            LocationServices.getFusedLocationProviderClient(MainActivity.this)
                    .requestLocationUpdates(locationRequest,
                            new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                            .removeLocationUpdates(this);

                                    if (!locationResult.getLocations().isEmpty()){



                                        Location location = new Location("chilis");
                                        location.setLatitude(25.1248889);
                                        location.setLongitude(46.1082222);
                                        float distance=locationResult.getLocations().get(0).distanceTo(location);
                                        Toast.makeText(MainActivity.this,
                                                "chilis is Nearest resturant :"+distance/1000 +" KM", Toast.LENGTH_SHORT).show();


                                    }

                                }
                            }, Looper.getMainLooper());



        }
    }
}


