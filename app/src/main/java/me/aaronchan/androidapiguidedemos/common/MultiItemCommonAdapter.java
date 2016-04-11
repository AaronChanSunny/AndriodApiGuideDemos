package me.aaronchan.androidapiguidedemos.common;

import android.view.ViewGroup;

import java.util.List;

/**
 * Created by aaronchan on 16/4/11.
 */
public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {
    private List<T> mItems;
    private MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(List<T> items, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(items, -1);
        mItems = items;
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mItems.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.get(parent.getContext(), null, parent, layoutId, -1);
        setListener(parent, holder, viewType);
        return holder;
    }
}
