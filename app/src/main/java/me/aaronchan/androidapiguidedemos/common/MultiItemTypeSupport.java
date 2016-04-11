package me.aaronchan.androidapiguidedemos.common;

/**
 * Created by aaronchan on 16/4/11.
 */
public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int positon, T item);
}
