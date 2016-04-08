package me.aaronchan.androidapiguidedemos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.aaronchan.androidapiguidedemos.model.Item;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER = 0;
    private static final int CONTENT = 1;

    private List<Item> mItems;

    public ListAdapter(List<Item> items) {
        mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case HEADER:
                View viewHeader = LayoutInflater.from(parent.getContext()).inflate(R.layout.item1_list, parent, false);
                viewHolder = new ViewHolder1(viewHeader);
                break;
            default:
                View viewContent = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2_list, parent, false);
                viewHolder = new ViewHolder2(viewContent);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).isHeader() ? HEADER : CONTENT;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = mItems.get(position);

        switch (holder.getItemViewType()) {
            case HEADER:
                ViewHolder1 holder1 = (ViewHolder1) holder;
                holder1.mDivider.setVisibility(position > 0 ? View.VISIBLE : View.GONE);
                holder1.mHeader.setText(item.getTitle());
                break;
            default:
                ViewHolder2 holder2 = (ViewHolder2) holder;
                holder2.mTitle.setText(item.getTitle());
                holder2.mDesc.setText(item.getDesc());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        private View mDivider;
        private TextView mHeader;

        public ViewHolder1(View itemView) {
            super(itemView);

            mDivider = itemView.findViewById(R.id.divider);
            mHeader = (TextView) itemView.findViewById(R.id.txt_header);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDesc;

        public ViewHolder2(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.txt_title);
            mDesc = (TextView) itemView.findViewById(R.id.txt_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    context.startActivity(new Intent(context, mItems.get(getLayoutPosition()).getActivity()));
                }
            });
        }
    }
}