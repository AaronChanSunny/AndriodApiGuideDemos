package me.aaronchan.androidapiguidedemos;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class DeviceCompatibilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_compatibility);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean has = checkSensorCompass();
                Toast.makeText(DeviceCompatibilityActivity.this, has ? "Device has sensor " +
                        "compass" : "Device do not have sensor compass", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean checkSensorCompass() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS);
    }

}
