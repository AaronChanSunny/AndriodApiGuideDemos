package me.aaronchan.androidapiguidedemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class IntentsAndIntentFilterActivity extends AppCompatActivity {

    private static final String TAG = IntentsAndIntentFilterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents_and_intent_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.btn_implicit_intents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                implicitIntents();
            }
        });

        findViewById(R.id.btn_chooser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createChooser();
            }
        });
    }

    private void implicitIntents() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hello, implicit intents.");
        intent.setType("text/plain");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.i(TAG, "intent filter failed.");
        }
    }

    private void createChooser() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hello, app chooser.");
        intent.setType("text/plain");

        if (intent.resolveActivity(getPackageManager()) != null) {
            Intent chooser = Intent.createChooser(intent, "Chooser");
            startActivity(chooser);
        } else {
            Log.i(TAG, "intent filter failed.");
        }
    }

}
