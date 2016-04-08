package me.aaronchan.androidapiguidedemos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class FragmentPlayActivity extends AppCompatActivity {

    private static final String TAG = FragmentPlayActivity.class.getSimpleName();

    private int mIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_play);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });

        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d(TAG, "onBackStackChanged");
            }
        });
    }

    private void removeFragment() {
        getSupportFragmentManager().popBackStack();
    }

    private void addFragment() {
        Fragment fragment;
        fragment = mIdx % 2 == 0 ? new BlankFragment1() : new BlankFragment2();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(mIdx + "")
                .commit();

        mIdx++;
    }

}
