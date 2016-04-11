package me.aaronchan.androidapiguidedemos.common;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aaronchan on 16/4/11.
 */
public interface OnItemClickListener<T>
{
    void onItemClick(ViewGroup parent, View view, T t, int position);
    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}
