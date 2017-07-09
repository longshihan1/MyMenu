package com.longshihan.mymenu.droplistview;

/**
 * Created by longshihan on 2017/7/9.
 */

public interface ItemTouchHelperAdapterCallback {


    boolean onItemMove(int fromPosition, int toPosition);

    void onItemSwiped(int position);
}
