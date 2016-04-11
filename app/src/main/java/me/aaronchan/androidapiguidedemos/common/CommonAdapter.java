package me.aaronchan.androidapiguidedemos.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by aaronchan on 16/4/11.
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private List<T> mItems;
    private int mLayoutId;
    private OnItemClickListener<T> mOnItemClickListener;

    public CommonAdapter(List<T> items, int layoutId) {
        mItems = items;
        mLayoutId = layoutId;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.get(parent.getContext(), null, parent, mLayoutId, -1);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mItems.get(position));
    }

    public abstract void convert(ViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    protected void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType)
    {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    int position = getPosition(viewHolder);
                    mOnItemClickListener.onItemClick(parent, v, mItems.get(position), position);
                }
            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                if (mOnItemClickListener != null)
                {
                    int position = getPosition(viewHolder);
                    return mOnItemClickListener.onItemLongClick(parent, v, mItems.get(position), position);
                }
                return false;
            }
        });
    }

    private int getPosition(ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }
}
