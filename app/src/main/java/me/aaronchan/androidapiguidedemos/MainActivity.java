package me.aaronchan.androidapiguidedemos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import me.aaronchan.androidapiguidedemos.model.ActivityDemoActivity;
import me.aaronchan.androidapiguidedemos.model.Item;

public class MainActivity extends AppCompatActivity {

    private ListAdapter mAdapter;
    private RecyclerView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();
        initView();
    }

    private void initView() {
        mList = (RecyclerView) findViewById(R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(mAdapter);
    }

    private void initData() {
        mAdapter = new ListAdapter(getListItems());
    }

    private List<Item> getListItems() {
        List<Item> items = new ArrayList<>();

        items.add(new Item(true, "Introduction"));
        items.add(new Item(false, "Device Compatibility", "Device features", DeviceCompatibilityActivity.class));
        items.add(new Item(true, "App Components"));
        items.add(new Item(false, "Intents And Intent Filters", "Implicit intents demo.", IntentsAndIntentFilterActivity.class));
        items.add(new Item(false, "Activities", "Activity demo.", ActivityDemoActivity.class));
        items.add(new Item(false, "Activities", "Fragment.", FragmentPlayActivity.class));

        return items;
    }
}
