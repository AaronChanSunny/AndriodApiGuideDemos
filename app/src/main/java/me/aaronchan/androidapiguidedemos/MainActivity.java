package me.aaronchan.androidapiguidedemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import me.aaronchan.androidapiguidedemos.common.MultiItemCommonAdapter;
import me.aaronchan.androidapiguidedemos.common.MultiItemTypeSupport;
import me.aaronchan.androidapiguidedemos.common.OnItemClickListener;
import me.aaronchan.androidapiguidedemos.common.ViewHolder;
import me.aaronchan.androidapiguidedemos.model.ActivityDemoActivity;
import me.aaronchan.androidapiguidedemos.model.Item;

public class MainActivity extends AppCompatActivity {

    // private ListAdapter mAdapter;
    private MultiItemCommonAdapter<Item> mAdapter;
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
        // mAdapter = new ListAdapter(getListItems());
        mAdapter = new MultiItemCommonAdapter<Item>(getListItems(), new MultiItemTypeSupport<Item>() {
            @Override
            public int getLayoutId(int itemType) {
                return itemType == 0 ? R.layout.item1_list : R.layout.item2_list;
            }

            @Override
            public int getItemViewType(int positon, Item item) {
                return item.isHeader() ? 0 : 1;
            }
        }) {
            @Override
            public void convert(ViewHolder holder, Item item) {
                switch (holder.getLayoutId()) {
                    case R.layout.item1_list:
                        TextView txtHeader = holder.getView(R.id.txt_header);
                        txtHeader.setText(item.getTitle());
                        break;
                    case R.layout.item2_list:
                        TextView txtTitle = holder.getView(R.id.txt_title);
                        txtTitle.setText(item.getTitle());
                        TextView txtDesc = holder.getView(R.id.txt_desc);
                        txtDesc.setText(item.getDesc());
                        break;
                }
            }
        };

        mAdapter.setOnItemClickListener(new OnItemClickListener<Item>() {

            @Override
            public void onItemClick(ViewGroup parent, View view, Item item, int position) {
                if (!item.isHeader()) {
                    startActivity(new Intent(MainActivity.this, item.getActivity()));
                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Item item, int position) {
                Toast.makeText(MainActivity.this, item.getTitle() + " long clicked.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private List<Item> getListItems() {
        List<Item> items = new ArrayList<>();

        items.add(new Item(true, "Introduction"));
        items.add(new Item(false, "Device Compatibility", "Device features", DeviceCompatibilityActivity.class));
        items.add(new Item(true, "App Components"));
        items.add(new Item(false, "Intents And Intent Filters", "Implicit intents demo.", IntentsAndIntentFilterActivity.class));
        items.add(new Item(false, "Activities", "Activity demo.", ActivityDemoActivity.class));
        items.add(new Item(false, "Activities", "Fragment.", FragmentPlayActivity.class));
        items.add(new Item(false, "Activities", "Fragment demo.", FragmentDemoActivity.class));

        return items;
    }
}
