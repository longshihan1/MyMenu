package com.longshihan.mymenu.droplistview;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by longshihan on 2017/7/9.
 */

public class MessageItemTouchCallBack extends ItemTouchHelper.Callback {
    public MessageItemTouchCallBack(ItemTouchHelperAdapterCallback itemTouchHelperAdapterCallback) {
        mItemTouchHelperAdapterCallback = itemTouchHelperAdapterCallback;
    }

    private ItemTouchHelperAdapterCallback mItemTouchHelperAdapterCallback;

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //判断是否需要监听哪个动作:侧滑，拖拽
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipFlags = ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        //刷新数据

        //刷新adapter
        mItemTouchHelperAdapterCallback.onItemMove(viewHolder.getAdapterPosition(),
                target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //刷新数据

        //刷新adapter

        mItemTouchHelperAdapterCallback.onItemSwiped(viewHolder.getAdapterPosition());

    }

    /**
     * 润宇长按退拽
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder
            viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //0-1,0-width
            float alpha = 1 - Math.abs(dX) / viewHolder.itemView.getWidth();
            viewHolder.itemView.setAlpha(alpha);
            viewHolder.itemView.setScaleX(alpha);
            viewHolder.itemView.setScaleY(alpha);
            if (alpha<=0){
                viewHolder.itemView.setAlpha(1);
                viewHolder.itemView.setScaleX(1);
                viewHolder.itemView.setScaleY(1);
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
