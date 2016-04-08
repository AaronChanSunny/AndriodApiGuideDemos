package me.aaronchan.androidapiguidedemos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class TitlesFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;
    private ArrayAdapter<String> mAdapter;

    public TitlesFragment() {
    }

    public static TitlesFragment newInstance() {
        TitlesFragment fragment = new TitlesFragment();
        return fragment;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(mAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
