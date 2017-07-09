package com.longshihan.mymenu.droplistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.longshihan.mymenu.R;

import java.util.ArrayList;
import java.util.List;

public class DropActivity extends AppCompatActivity implements StartDradListener {

    RecyclerView mRecyclerView;
    List<String> mStringList;
    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop);
        mRecyclerView = findViewById(R.id.recycler);
        mStringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mStringList.add("测试aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + i);
        }
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL));
        MessageAdapter adapter = new MessageAdapter(this, mStringList);
        mRecyclerView.setAdapter(adapter);

        MessageItemTouchCallBack callBack = new MessageItemTouchCallBack(adapter);
        itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
