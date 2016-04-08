package me.aaronchan.androidapiguidedemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TitlesFragment extends ListFragment {

    private ArrayAdapter<String> mAdapter;
    private boolean mIsPannels;

    public TitlesFragment() {
    }

    public static TitlesFragment newInstance() {
        TitlesFragment fragment = new TitlesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(mAdapter);
        mIsPannels = getActivity().findViewById(R.id.container_detail) != null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String title = mAdapter.getItem(position);
        showDetail(title);
    }

    private void showDetail(String title) {
        if (mIsPannels) {
            Toast.makeText(getActivity(), title + " selected.", Toast.LENGTH_SHORT).show();
            getFragmentManager().beginTransaction()
                    .replace(R.id.container_detail, DetailFragment.newInstance(title))
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        } else {
            Toast.makeText(getActivity(), "start a new activity.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(DetailFragment.ARG_TITLE, title);
            startActivity(intent);
        }
    }

    private void initData() {
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, getTitles());
    }

    private List<String> getTitles() {
        List<String> titles = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            String title = "Title " + i;
            titles.add(title);
        }

        return titles;
    }

}
