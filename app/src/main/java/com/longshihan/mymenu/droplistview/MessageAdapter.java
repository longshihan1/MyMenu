package com.longshihan.mymenu.droplistview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.longshihan.mymenu.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by longshihan on 2017/7/9.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyAdapter> implements
        ItemTouchHelperAdapterCallback {
    private List<String> mTitles;
    private StartDradListener mStartDradListener;

    public MessageAdapter(StartDradListener context, List<String> mTitles) {
        mStartDradListener = context;
        this.mTitles = mTitles;
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dropview,
                parent, false);
        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(final MyAdapter holder, int position) {
        holder.mTextView.setText(mTitles.get(position));
        holder.mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mStartDradListener.onStartDrag(holder);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mTitles,fromPosition,toPosition);
      /*
        notifyDataSetChanged();*/
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemSwiped(int position) {
        mTitles.remove(position);
        notifyItemRemoved(position);
    }

    class MyAdapter extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public MyAdapter(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.item_dropviewtxt);
        }
    }
}
